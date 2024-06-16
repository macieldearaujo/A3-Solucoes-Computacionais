package login;

import database.CreateConnection;
import entities.User;
import home.HomeAdmin;
import home.HomePatient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import register.RegisterPatient;

public class Login extends javax.swing.JFrame {

    private Connection connection;
    private PreparedStatement preparedStatement = null;
    private CreateConnection createConnection = new CreateConnection();
    private ResultSet resultSet = null;
    private boolean adminScreen;

    private javax.swing.JPanel form;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loginButton1;
    private javax.swing.JButton loginRegisterButton;
    private javax.swing.JLabel loginSubtitle;
    private javax.swing.JLabel loginTitle;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordTitle;
    private javax.swing.JSeparator separator;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameTitle;

    public Login(Connection connection, boolean adminScreen) {
        this.connection = connection;
        this.adminScreen = adminScreen;
        initComponents();
    }

    private void initComponents() {
        form = new javax.swing.JPanel();
        passwordField = new javax.swing.JPasswordField();
        passwordTitle = new javax.swing.JLabel();
        loginRegisterButton = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        loginSubtitle = new javax.swing.JLabel();
        loginTitle = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        usernameTitle = new javax.swing.JLabel();
        showPassword = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        loginButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 700, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(700, 800));
        getContentPane().setLayout(null);

        form.setBackground(new java.awt.Color(255, 255, 255));
        form.setLayout(null);

        passwordField.addActionListener(evt -> passwordFieldActionPerformed(evt));
        form.add(passwordField);
        passwordField.setBounds(108, 412, 464, 40);

        passwordTitle.setFont(new java.awt.Font("Arial", 1, 17));
        passwordTitle.setText("Senha");
        form.add(passwordTitle);
        passwordTitle.setBounds(108, 362, 140, 20);

        loginRegisterButton.setBackground(new java.awt.Color(255, 255, 255));
        loginRegisterButton.setFont(new java.awt.Font("Arial", 1, 16));
        loginRegisterButton.setForeground(new java.awt.Color(109, 180, 227));
        loginRegisterButton.setText("Cadastre-se");
        loginRegisterButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        loginRegisterButton.addActionListener(evt -> loginRegisterButtonActionPerformed(evt));
        form.add(loginRegisterButton);
        loginRegisterButton.setBounds(460, 620, 130, 40);
        form.add(separator);
        separator.setBounds(108, 524, 464, 20);

        loginSubtitle.setFont(new java.awt.Font("Arial", 0, 14));
        loginSubtitle.setForeground(new java.awt.Color(153, 153, 153));
        loginSubtitle.setText("<html><p>Acesse seu plano de saúde. Explore seu gerenciador de consultas e exames.</p><html>");
        form.add(loginSubtitle);
        loginSubtitle.setBounds(110, 180, 464, 45);

        loginTitle.setFont(new java.awt.Font("Arial", 1, 48));
        loginTitle.setText("<html><p>Faça seu login</p></html>");
        loginTitle.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        form.add(loginTitle);
        loginTitle.setBounds(110, 110, 340, 60);

        usernameField.addActionListener(evt -> usernameFieldActionPerformed(evt));
        form.add(usernameField);
        usernameField.setBounds(108, 295, 464, 40);

        usernameTitle.setFont(new java.awt.Font("Arial", 1, 17));
        usernameTitle.setText("Usuário");
        form.add(usernameTitle);
        usernameTitle.setBounds(108, 257, 140, 20);

        showPassword.setFont(new java.awt.Font("Arial", 1, 14));
        showPassword.setText("Mostrar senha");
        showPassword.addActionListener(evt -> showPasswordActionPerformed(evt));
        form.add(showPassword);
        showPassword.setBounds(447, 470, 127, 21);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 15));
        jLabel1.setText("Ainda não tem conta?");
        form.add(jLabel1);
        jLabel1.setBounds(310, 630, 150, 22);

        loginButton1.setBackground(new java.awt.Color(60, 66, 108));
        loginButton1.setFont(new java.awt.Font("Arial", 1, 18));
        loginButton1.setForeground(new java.awt.Color(255, 255, 255));
        loginButton1.setText("Sign in");
        loginButton1.addActionListener(evt -> loginButton1ActionPerformed(evt));
        form.add(loginButton1);
        loginButton1.setBounds(110, 560, 460, 41);

        getContentPane().add(form);
        form.setBounds(0, 0, 680, 740);

        setSize(new java.awt.Dimension(697, 747));
        setLocationRelativeTo(null);
    }

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public String getUsername() {
        return usernameField.getText();
    }

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        if (showPassword.isSelected()) {
            passwordField.setEchoChar('\0');
        } else {
            passwordField.setEchoChar('*');
        }
    }

    private void loginRegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        RegisterPatient register = new RegisterPatient(connection, adminScreen);
        register.setLocationRelativeTo(null);
        register.setVisible(true);
    }

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void loginButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        User user = new User();
        user.setEmail(usernameField.getText());
        user.setPassword(passwordField.getText());

        boolean existUser = verifyUserAndPassword(user);

        if (existUser) {
            System.out.println("Success Login");
        } else {
            System.out.println("Incorrect user or password");
            JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean verifyUserAndPassword(User patient) {
        try {
            preparedStatement = connection.prepareStatement("Select email,senha, admin,tipo from tb_usuarios where email = ? and senha = ?");
            preparedStatement.setString(1, patient.getEmail());
            preparedStatement.setString(2, patient.getPassword());
            preparedStatement.execute();

            resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                if (resultSet.getBoolean("admin")) {
                    dispose();
                    HomeAdmin homeAdmin = new HomeAdmin(connection, true);
                    homeAdmin.setLocationRelativeTo(null);
                    homeAdmin.setVisible(true);
                } else if (!resultSet.getBoolean("admin") && "paciente".equals(resultSet.getString("tipo"))) {
                    dispose();
                    HomePatient homePatient = new HomePatient(connection, patient.getEmail(), false);
                    homePatient.setLocationRelativeTo(null);
                    homePatient.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Sem acesso", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro, entre em contato com o administrador", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            CreateConnection criarConexao = new CreateConnection();
            Connection connection = criarConexao.connect();
            Login login = new Login(connection, false);
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });
    }
}
