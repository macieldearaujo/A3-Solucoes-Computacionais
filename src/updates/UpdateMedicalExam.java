package updates;

import application.Utils;
import register.PatientRegisterMedicalScheduling;
import com.toedter.calendar.JDateChooser;
import database.CreateConnection;
import home.HomeAdmin;
import home.HomePatient;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UpdateMedicalExam extends javax.swing.JFrame {

    private final Connection connection;
    private PreparedStatement preparedStatement = null;
    private static Statement statement = null;
    private static ResultSet resultSet;
    private Map<String, String> doctorsMap;
    private final int medSchedulingId;
    private final String specialty;
    private final String doctorId;
    private final boolean adminScreen;
    private String username;

    public UpdateMedicalExam(Connection connection, String username, int medSchedulingId, String specialty, String doctorId, boolean adminScreen) {
        this.connection = connection;
        this.medSchedulingId = medSchedulingId;
        this.specialty = specialty;
        this.username = username;
        this.doctorId = doctorId;
        this.adminScreen = adminScreen;

        initComponents();
        loadDoctors(specialty);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        form = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        doctorLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        doctorComboBox = new javax.swing.JComboBox<>();
        dateChooser = new JDateChooser();
        timeComboBox = new javax.swing.JComboBox<>();
        registerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 700, 600));
        setSize(new java.awt.Dimension(700, 600));
        getContentPane().setLayout(null);

        form.setBackground(new java.awt.Color(255, 255, 255));
        form.setLayout(null);

        titleLabel.setFont(new java.awt.Font("Arial", 1, 24));
        titleLabel.setText("Atualizar Exames");
        form.add(titleLabel);
        titleLabel.setBounds(220, 30, 260, 30);

        doctorLabel.setFont(new java.awt.Font("Arial", 1, 16));
        doctorLabel.setText("Médico:");
        form.add(doctorLabel);
        doctorLabel.setBounds(50, 180, 130, 20);

        dateLabel.setFont(new java.awt.Font("Arial", 1, 16));
        dateLabel.setText("Data:");
        form.add(dateLabel);
        dateLabel.setBounds(50, 260, 130, 20);

        timeLabel.setFont(new java.awt.Font("Arial", 1, 16));
        timeLabel.setText("Hora:");
        form.add(timeLabel);
        timeLabel.setBounds(50, 340, 130, 20);

        form.add(doctorComboBox);
        doctorComboBox.setBounds(200, 180, 400, 30);

        form.add(dateChooser);
        dateChooser.setBounds(200, 260, 400, 30);

        timeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00",
            "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
            "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00"}));
        form.add(timeComboBox);
        timeComboBox.setBounds(200, 340, 400, 30);

        registerButton.setBackground(new java.awt.Color(60, 66, 108));
        registerButton.setFont(new java.awt.Font("Arial", 1, 18));
        registerButton.setForeground(new java.awt.Color(255, 255, 255));
        registerButton.setText("Atualizar");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        form.add(registerButton);
        registerButton.setBounds(250, 420, 200, 40);

        getContentPane().add(form);
        form.setBounds(10, 0, 680, 520);

        setSize(new java.awt.Dimension(697, 530));
        setLocationRelativeTo(null);
        
        JPanel buttonPanel = new JPanel();
        homeButton = new JButton("Voltar");
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(homeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        if (adminScreen == true) {
            HomeAdmin homeAdmin = new HomeAdmin(connection, true);
            homeAdmin.setLocationRelativeTo(null);
            homeAdmin.setVisible(true);
        } else {
            HomePatient homePatient = new HomePatient(connection, username, false);
            homePatient.setLocationRelativeTo(null);
            homePatient.setVisible(true);
        }
    }

    private void loadDoctors(String specialty) {
        doctorComboBox.removeAllItems();
        try {
            String queryDoctorBySpecialty = "Select cpf, crm from tb_medicos where especialidade = ? and exam_medico = true";
            doctorsMap = Utils.getDoctorsBySpecialty(connection, specialty, queryDoctorBySpecialty);
            for (String doctor : doctorsMap.keySet()) {
                doctorComboBox.addItem(doctor);
            }
        } catch (SQLException e) {
            System.err.println("Error to load doctors");
        }
    }

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String doctorName = (String) doctorComboBox.getSelectedItem();
        String cpf = doctorsMap.get(doctorName);
        Date date = dateChooser.getDate();
        String time = (String) timeComboBox.getSelectedItem();
        String timeFromated = time + ":00";

        if (doctorName == null || date == null || time == null) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);

        try {
            String doctorCPF = doctorsMap.get(doctorName);
            String doctorId = Utils.getDoctorIDByCPF(connection, cpf);

            if (doctorCPF == null) {
                JOptionPane.showMessageDialog(null, "Médico não encontrado para essa especialidade", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean exist = findScheduling(doctorId, dateString, timeFromated);

            if (exist != true) {
                createScheduling(doctorId, dateString, timeFromated, "");
                JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar consulta, o médico não esta disponível, tente agendar para outro dia ou horário", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro entre em contato com o Administrador", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean findScheduling(String doctorCPF, String dateString, String timeFromated) throws SQLException {
        preparedStatement = connection.prepareStatement("Select exame_id from tb_exames where medico_id = ? and data = ? and hora = ?");
        preparedStatement.setString(1, doctorId);
        preparedStatement.setString(2, dateString);
        preparedStatement.setString(3, timeFromated);
        resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    public void createScheduling(String doctorId, String dateString, String timeFormated, String schedulingId) throws SQLException {
        preparedStatement = connection.prepareStatement("Update tb_exames set medico_id = ?,  data = ?, hora = ? WHERE exame_id =?");
        preparedStatement.setString(1, doctorId);
        preparedStatement.setString(2, dateString);
        preparedStatement.setString(3, timeFormated);
        preparedStatement.setInt(4, medSchedulingId);
        preparedStatement.executeUpdate();
    }

    public static List<String> getSpecialties(Connection connection) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("Select distinct especialidade from tb_medicos");

        List<String> specialties = new ArrayList<>();
        while (resultSet.next()) {
            specialties.add(resultSet.getString("especialidade"));
        }
        return specialties;
    }

    public void OpenScreen() {
        CreateConnection criarConexao = new CreateConnection();
        Connection connection = criarConexao.connect();

        UpdateMedicalExam patientUpdateMedicalExam = new UpdateMedicalExam(connection, username, 0, "", "", adminScreen);
        patientUpdateMedicalExam.setLocationRelativeTo(null);
        patientUpdateMedicalExam.setVisible(true);

    }
    // Variables declaration
    private javax.swing.JButton registerButton;
    private javax.swing.JComboBox<String> doctorComboBox;
    private JDateChooser dateChooser;
    private javax.swing.JComboBox<String> timeComboBox;
    private javax.swing.JPanel form;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel doctorLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JButton homeButton;

    // End of variables declaration
}
