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
import login.Login;
import updates.UpdateMedicalScheduling;

public class AdminViewMedicalScheduling extends JFrame {

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

    public AdminViewMedicalScheduling(Connection connection, String username, boolean adminScreen) {
        this.connection = connection;
        this.username = username;
        this.adminScreen = adminScreen;
        initComponents();
        loadScheduling(connection);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle("Todas Consultas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Consultas ");
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

        tableModel = new DefaultTableModel(new String[]{"ID", "Data", "Hora", "Especialidade", "Médico", "Paciente"}, 0) {
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
                OpenScreenUpdateMedicalScheduling();
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
            HomeAdmin homeAdmin = new HomeAdmin(connection, true);
            homeAdmin.setLocationRelativeTo(null);
            homeAdmin.setVisible(true);
        }
    }

    private void loadScheduling(Connection connection) {
        try {
            preparedStatement = connection.prepareStatement("SELECT consulta_id, data, hora, especialidade, medico_id, paciente_id FROM tb_consultas");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("consulta_id");
                String date = resultSet.getString("data");
                String time = resultSet.getString("hora");
                String specialty = resultSet.getString("especialidade");
                String doctorId = resultSet.getString("medico_id");
                String pacienteId = resultSet.getString("paciente_id");
                tableModel.addRow(new Object[]{id, date, time, specialty, doctorId, pacienteId});
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

    private void OpenScreenUpdateMedicalScheduling() {
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

    public void OpenScreen() {
        CreateConnection criarConexao = new CreateConnection();
        Connection connection = criarConexao.connect();

        Login login = new Login(connection, adminScreen);
        String username = login.getUsername();

        AdminViewMedicalScheduling adminViewMedicalScheduling = new AdminViewMedicalScheduling(connection, username, adminScreen);
        adminViewMedicalScheduling.setLocationRelativeTo(null);
        adminViewMedicalScheduling.setVisible(true);

    }

}
