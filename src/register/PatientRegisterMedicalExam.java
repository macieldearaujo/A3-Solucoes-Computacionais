package register;

import com.toedter.calendar.JDateChooser;
import database.CreateConnection;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import login.Login;
import application.Utils;
import home.HomePatient;

public class PatientRegisterMedicalExam extends javax.swing.JFrame {

    private final Connection connection;
    private PreparedStatement preparedStatement = null;
    private static Statement statement = null;
    private static ResultSet resultSet;
    private final String username;
    private Map<String, String> doctorsMap;
    private Map<String, String> examsMap;
    private boolean adminScreen;

    public PatientRegisterMedicalExam(Connection connection, String username, boolean adminScreen) {
        this.connection = connection;
        this.username = username;
        this.adminScreen = adminScreen;
        initComponents();
        loadExams();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        form = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        specialtyLabel = new javax.swing.JLabel();
        doctorLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        examComboBox = new javax.swing.JComboBox<>();
        doctorComboBox = new javax.swing.JComboBox<>();
        dateChooser = new JDateChooser();
        timeComboBox = new javax.swing.JComboBox<>();
        registerButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 700, 600));
        setSize(new java.awt.Dimension(700, 600));
        getContentPane().setLayout(null);

        form.setBackground(new java.awt.Color(255, 255, 255));
        form.setLayout(null);

        titleLabel.setFont(new java.awt.Font("Arial", 1, 24));
        titleLabel.setText("Cadastro de Exames");
        form.add(titleLabel);
        titleLabel.setBounds(220, 30, 260, 30);

        specialtyLabel.setFont(new java.awt.Font("Arial", 1, 16));
        specialtyLabel.setText("Exame:");
        form.add(specialtyLabel);
        specialtyLabel.setBounds(50, 100, 130, 20);

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

        examComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examComboBoxActionPerformed(evt);
            }
        });
        form.add(examComboBox);
        examComboBox.setBounds(200, 100, 400, 30);

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
        registerButton.setText("Cadastrar");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        form.add(registerButton);
        registerButton.setBounds(250, 420, 200, 40);

        homeButton.setBackground(new java.awt.Color(255, 255, 255));
        homeButton.setFont(new java.awt.Font("Arial", 1, 16));
        homeButton.setForeground(new java.awt.Color(109, 180, 227));
        homeButton.setText("Voltar");
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        form.add(homeButton);
        homeButton.setBounds(30, 30, 100, 30);

        getContentPane().add(form);
        form.setBounds(10, 0, 680, 520);

        setSize(new java.awt.Dimension(697, 530));
        setLocationRelativeTo(null);
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        if (adminScreen == false) {
            HomePatient homePatient = new HomePatient(connection, username, false);
            homePatient.setLocationRelativeTo(null);
            homePatient.setVisible(true);
        }
    }

    private void loadExams() {
        try {
            examsMap = getExams(connection);
            for (String doctor : examsMap.keySet()) {
                examComboBox.addItem(doctor);
            }
        } catch (SQLException e) {
            System.err.println("Error to load specialty");
        }
    }

    private void examComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedExam = (String) examComboBox.getSelectedItem();
        String cpf = examsMap.get(selectedExam);

        loadDoctors(cpf);
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
            System.out.println("" + e);
        }
    }

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String doctorName = (String) doctorComboBox.getSelectedItem();
        String cpf = doctorsMap.get(doctorName);
        Date date = dateChooser.getDate();
        String time = (String) timeComboBox.getSelectedItem();
        String timeFromated = time + ":00";
        String exam = (String) examComboBox.getSelectedItem();

        if (doctorName == null || date == null || time == null) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);

        try {
            String patientId = Utils.getPatientId(connection, username);
            String doctorCPF = doctorsMap.get(doctorName);
            String doctorId = Utils.getDoctorIDByCPF(connection, cpf);
            String specialty = examsMap.get(exam);

            if (doctorCPF == null) {
                JOptionPane.showMessageDialog(null, "Médico não encontrado para realizar esse exame", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean exist = findScheduling(doctorId, dateString, timeFromated);

            if (exist != true) {
                createScheduling(exam, doctorId, patientId, dateString, timeFromated, specialty);

                JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar consulta, o médico não está disponível, tente agendar para outro dia ou horário", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro entre em contato com o Administrador", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean findScheduling(String doctorCPF, String dateString, String timeFromated) throws SQLException {
        preparedStatement = connection.prepareStatement("Select exame_id from tb_exames where medico_id = ? and data = ? and hora = ?");
        preparedStatement.setString(1, doctorCPF);
        preparedStatement.setString(2, dateString);
        preparedStatement.setString(3, timeFromated);
        resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    public void createScheduling(String exam, String doctorId, String patientId, String dateString, String timeFormated, String specialty) throws SQLException {
        preparedStatement = connection.prepareStatement("Insert into tb_exames (descricao, medico_id, paciente_id, data, hora, especialidade) values (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, exam);
        preparedStatement.setString(2, doctorId);
        preparedStatement.setString(3, patientId);
        preparedStatement.setString(4, dateString);
        preparedStatement.setString(5, timeFormated);
        preparedStatement.setString(6, specialty);
        preparedStatement.executeUpdate();
    }

    public Map<String, String> getExams(Connection connection) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("Select nome, especialidade from tb_tipo_exames");

        Map<String, String> exams = new HashMap<>();

        while (resultSet.next()) {
            exams.put(resultSet.getString("nome"), resultSet.getString("especialidade"));
        }

        return exams;
    }

    public void OpenScreen() {
        CreateConnection criarConexao = new CreateConnection();
        Connection connection = criarConexao.connect();
        Login login = new Login(connection, adminScreen);
        String username = login.getUsername();

        PatientRegisterMedicalExam medicalExam = new PatientRegisterMedicalExam(connection, username, adminScreen);
        medicalExam.setLocationRelativeTo(null);
        medicalExam.setVisible(true);

    }

    // Variables declaration
    private javax.swing.JButton registerButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JComboBox<String> examComboBox;
    private javax.swing.JComboBox<String> doctorComboBox;
    private JDateChooser dateChooser;
    private javax.swing.JComboBox<String> timeComboBox;
    private javax.swing.JPanel form;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel specialtyLabel;
    private javax.swing.JLabel doctorLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel timeLabel;

    // End of variables declaration
}
