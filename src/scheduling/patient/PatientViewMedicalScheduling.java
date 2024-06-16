package scheduling.patient;

import application.Utils;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import database.CreateConnection;
import home.HomePatient;
import login.Login;
import updates.UpdateMedicalScheduling;

public class PatientViewMedicalScheduling extends JFrame {

    private Connection connection;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton deleteButton;
    private JButton updateButton;
    private String username;
    private boolean adminScreen;
    private javax.swing.JButton homeButton;
    private javax.swing.JPanel form;

    public PatientViewMedicalScheduling(Connection connection, String username, boolean adminScreen) {
        this.connection = connection;
        this.username = username;
        this.adminScreen = adminScreen;
        initComponents();
        loadScheduling(connection);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle("Minhas Consultas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Minhas Consultas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new TitledBorder("Consultas Agendadas"));
        tablePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        tableModel = new DefaultTableModel(new String[]{"ID", "Data", "Hora", "Médico", "Especialidade", "MédicoID"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.removeColumn(table.getColumnModel().getColumn(5));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        tablePanel.add(scrollPane, gbc);
        add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        deleteButton = new JButton("Excluir");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excludeMedicalScheduling(connection);
            }
        });
        buttonPanel.add(deleteButton);

        updateButton = new JButton("Atualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenUpdateMedicalScheduling();
            }
        });
        buttonPanel.add(updateButton);

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

    private void loadScheduling(Connection connection) {
        try {
            String patientId = Utils.getPatientId(connection, username);
            preparedStatement = connection.prepareStatement("SELECT consulta_id, data, hora, especialidade, medico_id FROM tb_consultas WHERE paciente_id = ?");
            preparedStatement.setString(1, patientId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("consulta_id");
                String date = resultSet.getString("data");
                String time = resultSet.getString("hora");
                String specialty = resultSet.getString("especialidade");
                String doctorId = resultSet.getString("medico_id");
                String doctorName = getDoctorName(doctorId);
                tableModel.addRow(new Object[]{id, date, time, specialty, doctorName, doctorId});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        if (adminScreen == false) {
            HomePatient homePatient = new HomePatient(connection, username, false);
            homePatient.setLocationRelativeTo(null);
            homePatient.setVisible(true);
        }
    }

    private void excludeMedicalScheduling(Connection connection) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int consultaId = (int) tableModel.getValueAt(selectedRow, 0);

            try {
                String query = "DELETE FROM tb_consultas WHERE consulta_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, consultaId);
                preparedStatement.executeUpdate();

                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Consulta excluída com sucesso.");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao excluir consulta.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para excluir.");
        }
    }

    private void ScreenUpdateMedicalScheduling() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int medSchedulingId = (int) tableModel.getValueAt(selectedRow, 0);
            String specialty = (String) tableModel.getValueAt(selectedRow, 3);

            UpdateMedicalScheduling atualizarConsultaDialog = new UpdateMedicalScheduling(connection, username, medSchedulingId, specialty, adminScreen);
            dispose();
            atualizarConsultaDialog.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para atualizar.");
        }
    }

    public String getDoctorName(String doctorId) throws SQLException {
        String user_id = "";
        String doctor_name = "";

        PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT usuario_id from tb_medicos WHERE medico_id = ?");
        preparedStatement1.setString(1, doctorId);
        try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
            if (resultSet1.next()) {
                user_id = resultSet1.getString("usuario_id");
            }
        }
        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT nome from tb_usuarios WHERE usuario_id = ?");
        preparedStatement2.setString(1, user_id);

        try (ResultSet resultSet2 = preparedStatement2.executeQuery()) {
            if (resultSet2.next()) {
                doctor_name = resultSet2.getString("nome");
            }
        }
        return doctor_name;

    }
    
    public void OpenScreen() {
        CreateConnection criarConexao = new CreateConnection();
                Connection connection = criarConexao.connect();

                Login login = new Login(connection, adminScreen);
                String username = login.getUsername();

                PatientViewMedicalScheduling patientMedicalAppointments = new PatientViewMedicalScheduling(connection, username, adminScreen);
                patientMedicalAppointments.setLocationRelativeTo(null);
                patientMedicalAppointments.setVisible(true);

    }
}
