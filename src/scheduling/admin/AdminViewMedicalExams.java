package scheduling.admin;

import updates.UpdateMedicalExam;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import database.CreateConnection;
import home.HomeAdmin;
import login.Login;

public class AdminViewMedicalExams extends JFrame {

    private Connection connection;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton deleteButton;
    private JButton updateButton;
    private boolean adminScreen;
    private javax.swing.JButton homeButton;
    private String username;

    public AdminViewMedicalExams(Connection connection, String username, boolean adminScreen) {
        this.connection = connection;
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

        tableModel = new DefaultTableModel(new String[]{"ID", "Descrição", "Data", "Hora", "Especialidade", "Médico", "Paciente"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
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

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
        
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
            HomeAdmin homeAdmin = new HomeAdmin(connection, false);
            homeAdmin.setLocationRelativeTo(null);
            homeAdmin.setVisible(true);
        }
    }

    private void loadScheduling(Connection connection) {
        try {
            preparedStatement = connection.prepareStatement("SELECT exame_id, descricao, data, hora, especialidade, medico_id, paciente_id FROM tb_exames");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("exame_id");
                String date = resultSet.getString("data");
                String description = resultSet.getString("descricao");
                String time = resultSet.getString("hora");
                String doctorId = resultSet.getString("medico_id");
                String specialty = resultSet.getString("especialidade");
                String patient = resultSet.getString("paciente_id");
                tableModel.addRow(new Object[]{id, description, date, time, specialty, doctorId, patient});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void excludeMedicalScheduling(Connection connection) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int examId = (int) tableModel.getValueAt(selectedRow, 0);

            try {
                String query = "DELETE FROM tb_exames WHERE exame_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, examId);
                preparedStatement.executeUpdate();
                //preparedStatement.close();

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
            String specialty = (String) tableModel.getValueAt(selectedRow, 4);
            String doctorId = (String) tableModel.getValueAt(selectedRow, 5);

            UpdateMedicalExam patientUpdateMedicalExam = new UpdateMedicalExam(connection, username, medSchedulingId, specialty, doctorId, adminScreen);//this, connection, consultaId, data, hora, medico, especialidade
            dispose();
            patientUpdateMedicalExam.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para atualizar.");
        }
    }

    public String getDoctorName(String doctorId) throws SQLException {
        String user_id = "";

        PreparedStatement preparedStatement1 = connection.prepareStatement("Select usuario_id from tb_medicos where medico_id = ?");
        preparedStatement1.setString(1, doctorId);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        if (resultSet1.next()) {
            user_id = resultSet1.getString("usuario_id");
        }
        PreparedStatement preparedStatement2 = connection.prepareStatement("Select nome from tb_usuarios where usuario_id = ?");
        preparedStatement2.setString(1, user_id);

        ResultSet resultSet2 = preparedStatement2.executeQuery();
        if (resultSet2.next()) {
            String doctorName = resultSet2.getString("nome");
            return doctorName;
        } else {
            return "";
        }
    }

    public void OpenScreen() {
        CreateConnection criarConexao = new CreateConnection();
        Connection connection = criarConexao.connect();

        Login login = new Login(connection, adminScreen);
        String username = login.getUsername();

        AdminViewMedicalExams adminViewMedicalExams = new AdminViewMedicalExams(connection, username, adminScreen);
        adminViewMedicalExams.setLocationRelativeTo(null);
        adminViewMedicalExams.setVisible(true);

    }
}
