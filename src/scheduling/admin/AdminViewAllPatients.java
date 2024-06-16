package scheduling.admin;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import database.CreateConnection;
import home.HomeAdmin;
import updates.UpdatePatientRegister;

public class AdminViewAllPatients extends JFrame {

    private final Connection connection;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton deleteButton;
    private JButton updateButton;
    private final boolean adminScreen;
    private String username;
    private javax.swing.JButton homeButton;

    public AdminViewAllPatients(Connection connection, String username, boolean adminScreen) {
        this.connection = connection;
        this.username = username;
        this.adminScreen = adminScreen;
        initComponents();
        loadScheduling(connection);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle("Todos Pacientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Todos Pacientes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new TitledBorder("Pacientes existentes"));
        tablePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        tableModel = new DefaultTableModel(new String[]{"ID Usuario", "ID Paciente", "CPF", "Nome", "E-mail", "Data Nascimento", "Gênero", "Telefone", "CEP"}, 0) {
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
                excludePatient(connection);
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
            preparedStatement = connection.prepareStatement("SELECT usuario_id, cpf, nome, email, dataNascimento, genero, telefone, cep FROM tb_usuarios WHERE tipo = ?");
            preparedStatement.setString(1, "paciente");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id_user = resultSet.getInt("usuario_id");
                String cpf = resultSet.getString("cpf");
                String name = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String birthDate = resultSet.getString("dataNascimento");
                String gender = resultSet.getString("genero");
                String phone = resultSet.getString("telefone");
                String cep = resultSet.getString("cep");
                int id_patient = getIdUser(id_user);
                tableModel.addRow(new Object[]{id_user, id_patient, cpf, name, email, birthDate, gender, phone, cep});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void excludePatient(Connection connection) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int usuarioId = (int) tableModel.getValueAt(selectedRow, 0);

            try {
                String query_tb_user = "DELETE FROM tb_usuarios WHERE usuario_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query_tb_user);
                preparedStatement.setInt(1, usuarioId);
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

            UpdatePatientRegister atualizarConsultaDialog = new UpdatePatientRegister(connection, username, medSchedulingId, adminScreen);//this, connection, consultaId, data, hora, medico, especialidade
            dispose();
            atualizarConsultaDialog.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para atualizar.");
        }
    }

    public int getIdUser(int usuarioId) throws SQLException {
        int patientId = -1;
        PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT pacientes_id, usuario_id from tb_pacientes WHERE usuario_id = ?");
        preparedStatement1.setInt(1, usuarioId);
        try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
            if (resultSet1.next()) {
                patientId = resultSet1.getInt("usuario_id");
            }
        }
        return patientId;
    }

    public void OpenScreen() {
        CreateConnection criarConexao = new CreateConnection();
        Connection connection = criarConexao.connect();

        AdminViewAllPatients adminViewAllPatients = new AdminViewAllPatients(connection, username, adminScreen);
        adminViewAllPatients.setLocationRelativeTo(null);
        adminViewAllPatients.setVisible(true);
    }
}
