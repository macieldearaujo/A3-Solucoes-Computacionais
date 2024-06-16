package updates;

import application.Utils;
import database.CreateConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import entities.User;
import home.HomeAdmin;
import home.HomePatient;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UpdatePatientRegister extends javax.swing.JFrame {

    private Connection connection;
    public PreparedStatement praparedStatement = null;
    public CreateConnection createConnection = new CreateConnection();
    public ResultSet resultSet = null;
    public int id;
    private int userId;
    private boolean adminScreen;
    private String username;

    public UpdatePatientRegister(Connection connection, String username, int userId, boolean adminScreen) {
        this.connection = connection;
        this.userId = userId;
        this.username = username;
        this.adminScreen = adminScreen;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        form = new javax.swing.JPanel();
        passwordTitle = new javax.swing.JLabel();
        loginScreenButton = new javax.swing.JButton();
        loginTitle = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        usernameTitle = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        birthDateField = new javax.swing.JTextField();
        usernameTitle2 = new javax.swing.JLabel();
        passwordTitle2 = new javax.swing.JLabel();
        loginButton1 = new javax.swing.JButton();
        passwordTitle3 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        cepField = new javax.swing.JTextField();
        genderComboBox = new javax.swing.JComboBox<>();
        genderTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 700, 800));
        setSize(new java.awt.Dimension(700, 800));
        getContentPane().setLayout(null);

        form.setBackground(new java.awt.Color(255, 255, 255));
        form.setLayout(null);

        passwordTitle.setFont(new java.awt.Font("Arial", 1, 16));
        passwordTitle.setForeground(new java.awt.Color(51, 51, 51));
        passwordTitle.setText("Data de nascimento:*");
        form.add(passwordTitle);
        passwordTitle.setBounds(110, 670, 190, 20);

        birthDateField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthDateFieldActionPerformed(evt);
            }
        });
        form.add(birthDateField);
        birthDateField.setBounds(110, 690, 464, 40);

        genderTitle.setFont(new java.awt.Font("Arial", 1, 16));
        genderTitle.setForeground(new java.awt.Color(51, 51, 51));
        genderTitle.setText("Gênero:");
        form.add(genderTitle);
        genderTitle.setBounds(110, 740, 140, 20);

        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Homem", "Mulher", "Outro"}));
        form.add(genderComboBox);
        genderComboBox.setBounds(110, 760, 464, 40);

        loginScreenButton.setBackground(new java.awt.Color(255, 255, 255));
        loginScreenButton.setFont(new java.awt.Font("Arial", 1, 16));
        loginScreenButton.setForeground(new java.awt.Color(109, 180, 227));
        loginScreenButton.setText("Voltar para Home");
        loginScreenButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginScreenButtonActionPerformed(evt);
            }
        });
        form.add(loginScreenButton);
        loginScreenButton.setBounds(120, 820, 250, 41);

        loginTitle.setFont(new java.awt.Font("Arial", 1, 32));
        loginTitle.setText("<html><p>Atualizar Cadastro</p></html>");
        loginTitle.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        form.add(loginTitle);
        loginTitle.setBounds(180, 70, 340, 40);

        nameField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });
        form.add(nameField);
        nameField.setBounds(110, 190, 464, 40);

        usernameTitle.setFont(new java.awt.Font("Arial", 1, 16));
        usernameTitle.setForeground(new java.awt.Color(51, 51, 51));
        usernameTitle.setText("Nome:*");
        form.add(usernameTitle);
        usernameTitle.setBounds(110, 160, 140, 20);

        passwordField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        form.add(passwordField);
        passwordField.setBounds(110, 430, 464, 40);

        usernameTitle2.setFont(new java.awt.Font("Arial", 1, 16));
        usernameTitle2.setForeground(new java.awt.Color(51, 51, 51));
        usernameTitle2.setText("Telefone:*");
        form.add(usernameTitle2);
        usernameTitle2.setBounds(110, 320, 140, 20);

        passwordTitle2.setFont(new java.awt.Font("Arial", 1, 16));
        passwordTitle2.setForeground(new java.awt.Color(51, 51, 51));
        passwordTitle2.setText("Senha:*");
        form.add(passwordTitle2);
        passwordTitle2.setBounds(110, 400, 140, 20);

        loginButton1.setBackground(new java.awt.Color(60, 66, 108));
        loginButton1.setFont(new java.awt.Font("Arial", 1, 18));
        loginButton1.setForeground(new java.awt.Color(255, 255, 255));
        loginButton1.setText("Inscrever-se");
        loginButton1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButton1ActionPerformed(evt);
            }
        });
        form.add(loginButton1);
        loginButton1.setBounds(390, 820, 210, 41);

        passwordTitle3.setFont(new java.awt.Font("Arial", 1, 16));
        passwordTitle3.setForeground(new java.awt.Color(51, 51, 51));
        passwordTitle3.setText("CEP:");
        form.add(passwordTitle3);
        passwordTitle3.setBounds(110, 580, 150, 20);

        phoneField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneFieldActionPerformed(evt);
            }
        });
        form.add(phoneField);
        phoneField.setBounds(110, 350, 464, 40);

        cepField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cepFieldActionPerformed(evt);
            }
        });
        form.add(cepField);
        cepField.setBounds(110, 600, 464, 40);

        getContentPane().add(form);
        form.setBounds(10, 0, 680, 900);

        setSize(new java.awt.Dimension(700, 950));
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

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void birthDateFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void loginScreenButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        HomeAdmin home = new HomeAdmin(connection, adminScreen);
        home.setLocationRelativeTo(null);
        home.setVisible(true);
    }

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void loginButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        User user = new User();
        boolean allOk = true;
        String bithDateFormated = Utils.convertDate(birthDateField.getText());
        user.setName(nameField.getText());
        user.setPhone(phoneField.getText());
        user.setPassword(passwordField.getText());
        user.setCEP(cepField.getText());
        user.setBirthDate(bithDateFormated);
        user.setGender((String) genderComboBox.getSelectedItem());

        if (nameField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || birthDateField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos marcados como obrigatorios não podem estar vazios");
            allOk = false;
        }
        if (allOk) {
            addPacient(user, userId, connection);
        }
    }

    private void phoneFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cepFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public void OpenScreen() {
        CreateConnection criarConexao = new CreateConnection();
        Connection connection = criarConexao.connect();

        UpdatePatientRegister updatePatientRegister = new UpdatePatientRegister(connection, username, -1, adminScreen);
        updatePatientRegister.setLocationRelativeTo(null);
        updatePatientRegister.setVisible(true);

    }

    public void addPacient(User patient, int userId, Connection connector) {
        try {
            praparedStatement = connector.prepareStatement("UPDATE tb_usuarios set nome = ? ,telefone = ?, senha = ?, cep = ?,dataNascimento = ?, genero = ? WHERE usuario_id = ?");
            praparedStatement.setString(1, patient.getName());
            praparedStatement.setString(2, patient.getPhone());
            praparedStatement.setString(3, patient.getPassword());
            praparedStatement.setString(4, patient.getCEP());
            praparedStatement.setString(5, patient.getBirthDate());
            praparedStatement.setString(6, patient.getGender());
            praparedStatement.setInt(7, userId);
            praparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso!!", "Paciente Validado", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar médico: " + e.getMessage());
        }
    }

    // Variables declaration
    private javax.swing.JTextField birthDateField;
    private javax.swing.JTextField cepField;
    private javax.swing.JPanel form;
    private javax.swing.JButton loginButton1;
    private javax.swing.JButton loginScreenButton;
    private javax.swing.JLabel loginTitle;
    private javax.swing.JTextField nameField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordTitle;
    private javax.swing.JLabel passwordTitle2;
    private javax.swing.JLabel passwordTitle3;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel usernameTitle;
    private javax.swing.JLabel usernameTitle2;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JLabel genderTitle;
    private javax.swing.JButton homeButton;
    // End of variables declaration

}
