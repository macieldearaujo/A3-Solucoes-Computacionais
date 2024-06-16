package register;

import database.CreateConnection;
import entities.Doctor;
import home.HomeAdmin;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RegisterDoctor extends javax.swing.JFrame {

    private final Connection connection;
    public PreparedStatement praparedStatement = null;
    public CreateConnection createConnection = new CreateConnection();
    public ResultSet resultSet = null;
    public int id;
    public boolean adminScreen;
    public String username;

    public RegisterDoctor(Connection connection, boolean adminScreen) {
        this.connection = connection;
        this.username = username;
        this.adminScreen = adminScreen;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        JScrollPane scrollPane = new JScrollPane();
        form = new javax.swing.JPanel();
        passwordTitle = new javax.swing.JLabel();
        passwordTitle5 = new javax.swing.JLabel();
        loginTitle = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        usernameTitle = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        usernameTitle1 = new javax.swing.JLabel();
        crmField = new javax.swing.JTextField();
        SpecialtyField = new javax.swing.JTextField();
        usernameTitle2 = new javax.swing.JLabel();
        passwordTitle2 = new javax.swing.JLabel();
        loginButton1 = new javax.swing.JButton();
        passwordTitle1 = new javax.swing.JLabel();
        passwordTitle3 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        cpfField = new javax.swing.JTextField();
        cepField = new javax.swing.JTextField();
        passwordTitle4 = new javax.swing.JLabel();
        birthDateField = new javax.swing.JTextField();
        consultaCheckBox = new javax.swing.JCheckBox();
        exameCheckBox = new javax.swing.JCheckBox();
        genderComboBox = new javax.swing.JComboBox<>();
        genderTitle = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        passwordTitle6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        form.setBackground(new java.awt.Color(255, 255, 255));
        form.setLayout(null);

        loginTitle.setFont(new java.awt.Font("Arial", 1, 40));
        loginTitle.setText("<html><p>Cadastro Médico</p></html>");
        loginTitle.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        form.add(loginTitle);
        loginTitle.setBounds(180, 20, 340, 40);

        usernameTitle.setFont(new java.awt.Font("Arial", 1, 16));
        usernameTitle.setForeground(new java.awt.Color(51, 51, 51));
        usernameTitle.setText("Nome:*");
        form.add(usernameTitle);
        usernameTitle.setBounds(110, 80, 140, 20);

        nameField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });
        form.add(nameField);
        nameField.setBounds(110, 110, 464, 40);

        usernameTitle1.setFont(new java.awt.Font("Arial", 1, 16));
        usernameTitle1.setForeground(new java.awt.Color(51, 51, 51));
        usernameTitle1.setText("E-mail:*");
        form.add(usernameTitle1);
        usernameTitle1.setBounds(110, 160, 140, 20);

        emailField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });
        form.add(emailField);
        emailField.setBounds(110, 190, 464, 40);

        usernameTitle2.setFont(new java.awt.Font("Arial", 1, 16));
        usernameTitle2.setForeground(new java.awt.Color(51, 51, 51));
        usernameTitle2.setText("Telefone:*");
        form.add(usernameTitle2);
        usernameTitle2.setBounds(110, 240, 140, 20);

        phoneField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneFieldActionPerformed(evt);
            }
        });
        form.add(phoneField);
        phoneField.setBounds(110, 270, 464, 40);

        passwordTitle6.setFont(new java.awt.Font("Arial", 1, 16));
        passwordTitle6.setForeground(new java.awt.Color(51, 51, 51));
        passwordTitle6.setText("Senha:*");
        form.add(passwordTitle6);
        passwordTitle6.setBounds(110, 320, 140, 20);

        passwordField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });
        form.add(passwordField);
        passwordField.setBounds(110, 350, 464, 40);

        passwordTitle2.setFont(new java.awt.Font("Arial", 1, 16));
        passwordTitle2.setForeground(new java.awt.Color(51, 51, 51));
        passwordTitle2.setText("CRM:*");
        form.add(passwordTitle2);
        passwordTitle2.setBounds(110, 400, 140, 20);

        crmField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crmFieldActionPerformed(evt);
            }
        });
        form.add(crmField);
        crmField.setBounds(110, 430, 464, 40);

        passwordTitle1.setFont(new java.awt.Font("Arial", 1, 16));
        passwordTitle1.setForeground(new java.awt.Color(51, 51, 51));
        passwordTitle1.setText("CPF:*");
        form.add(passwordTitle1);
        passwordTitle1.setBounds(110, 480, 150, 20);

        cpfField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpfFieldActionPerformed(evt);
            }
        });
        form.add(cpfField);
        cpfField.setBounds(110, 510, 464, 40);

        passwordTitle3.setFont(new java.awt.Font("Arial", 1, 16));
        passwordTitle3.setForeground(new java.awt.Color(51, 51, 51));
        passwordTitle3.setText("CEP:");
        form.add(passwordTitle3);
        passwordTitle3.setBounds(110, 560, 150, 20);

        cepField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cepFieldActionPerformed(evt);
            }
        });
        form.add(cepField);
        cepField.setBounds(110, 590, 464, 40);

        passwordTitle4.setFont(new java.awt.Font("Arial", 1, 16));
        passwordTitle4.setForeground(new java.awt.Color(51, 51, 51));
        passwordTitle4.setText("Data de nascimento:*");
        form.add(passwordTitle4);
        passwordTitle4.setBounds(110, 640, 190, 20);

        birthDateField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthDateFieldActionPerformed(evt);
            }
        });
        form.add(birthDateField);
        birthDateField.setBounds(110, 670, 464, 40);

        passwordTitle.setFont(new java.awt.Font("Arial", 1, 16));
        passwordTitle.setForeground(new java.awt.Color(51, 51, 51));
        passwordTitle.setText("Especialidade:*");
        form.add(passwordTitle);
        passwordTitle.setBounds(110, 720, 190, 20);

        SpecialtyField.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpecialtyFieldActionPerformed(evt);
            }
        });
        form.add(SpecialtyField);
        SpecialtyField.setBounds(110, 750, 464, 40);

        genderTitle.setFont(new java.awt.Font("Arial", 1, 16));
        genderTitle.setForeground(new java.awt.Color(51, 51, 51));
        genderTitle.setText("Gênero:");
        form.add(genderTitle);
        genderTitle.setBounds(110, 800, 140, 20);

        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Homem", "Mulher", "Outro"}));
        form.add(genderComboBox);
        genderComboBox.setBounds(110, 830, 464, 40);

        passwordTitle5.setFont(new java.awt.Font("Arial", 1, 16));
        passwordTitle5.setForeground(new java.awt.Color(51, 51, 51));
        passwordTitle5.setText("Irá realizar?");
        form.add(passwordTitle5);
        passwordTitle5.setBounds(110, 880, 190, 20);

        consultaCheckBox.setFont(new java.awt.Font("Arial", 1, 14));
        consultaCheckBox.setText("Consulta");
        form.add(consultaCheckBox);
        consultaCheckBox.setBounds(110, 910, 120, 23);

        exameCheckBox.setFont(new java.awt.Font("Arial", 1, 14));
        exameCheckBox.setText("Exame");
        form.add(exameCheckBox);
        exameCheckBox.setBounds(250, 910, 120, 23);

        loginButton1.setBackground(new java.awt.Color(60, 66, 108));
        loginButton1.setFont(new java.awt.Font("Arial", 1, 18));
        loginButton1.setForeground(new java.awt.Color(255, 255, 255));
        loginButton1.setText("Cadastrar");
        loginButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButton1ActionPerformed(evt);
            }
        });
        form.add(loginButton1);
        loginButton1.setBounds(380, 940, 210, 41);

        form.setPreferredSize(new java.awt.Dimension(700, 1000));

        scrollPane.setViewportView(form);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1134, Short.MAX_VALUE)
        );

        pack();

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
            HomeAdmin homeAdmin = new HomeAdmin(connection, false);
            homeAdmin.setLocationRelativeTo(null);
            homeAdmin.setVisible(true);
        }
    }

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        if (adminScreen == true) {
            HomeAdmin home = new HomeAdmin(connection, adminScreen);
            home.setLocationRelativeTo(null);
            home.setVisible(true);
        }
    }

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // Placeholder for event handling code
    }

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // Placeholder for event handling code
    }

    private void crmFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // Placeholder for event handling code
    }

    private void SpecialtyFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // Placeholder for event handling code
    }

    private void phoneFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // Placeholder for event handling code
    }

    private void cpfFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // Placeholder for event handling code
    }

    private void cepFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // Placeholder for event handling code
    }

    private void birthDateFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // Placeholder for event handling code
    }

    private void loginButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        boolean medScheduling;
        boolean medExam;
        boolean allOk = true;
        Doctor doctor = new Doctor();

        if (consultaCheckBox.isSelected() == true) {
            medScheduling = true;
            medExam = false;
        } else {
            medScheduling = false;
            medExam = true;
        }

        doctor.setName(nameField.getText());
        doctor.setEmail(emailField.getText());
        doctor.setCRM(crmField.getText());
        doctor.setSpecialty(SpecialtyField.getText());
        doctor.setPassword(passwordField.getText());
        doctor.setPhone(phoneField.getText());
        doctor.setCPF(cpfField.getText());
        doctor.setCEP(cepField.getText());
        doctor.setBirthDate(birthDateField.getText());
        doctor.setMedScheduling(medScheduling);
        doctor.setExam(medExam);

        if (doctor.getName().trim().isEmpty() || doctor.getEmail().trim().isEmpty() || doctor.getCRM().trim().isEmpty()
                || doctor.getSpecialty().trim().isEmpty() || doctor.getCPF().trim().isEmpty() || doctor.getBirthDate().trim().isEmpty()) {
            allOk = false;
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.");
        }
        if (doctor.getMedScheduling() == true && doctor.getExam() == true) {
            allOk = false;
            JOptionPane.showMessageDialog(this, "Você só pode selecionar um dos tipos");
        }
        if (allOk) {
            addDoctor(doctor, connection);
        }

    }

    public void addDoctor(Doctor doctor, Connection connector) {
        try {
            praparedStatement = connection.prepareStatement("Insert into tb_usuarios(nome,email,telefone,senha,cpf,cep,dataNascimento,genero,tipo,fl_medico) values(?,?,?,?,?,?,?,?,?,?)");
            praparedStatement.setString(1, doctor.getName());
            praparedStatement.setString(2, doctor.getEmail());
            praparedStatement.setString(3, doctor.getPhone());
            praparedStatement.setString(4, doctor.getPassword());
            praparedStatement.setString(5, doctor.getCPF());
            praparedStatement.setString(6, doctor.getCEP());
            praparedStatement.setString(7, doctor.getBirthDate());
            praparedStatement.setString(8, doctor.getGender());
            praparedStatement.setString(9, "medico");
            praparedStatement.setBoolean(10, true);
            praparedStatement.execute();

            praparedStatement = connection.prepareStatement("Update tb_medicos set crm = ?, especialidade = ?, consulta_medica = ?, exam_medico = ? WHERE cpf = ?");
            praparedStatement.setString(1, doctor.getCRM());
            praparedStatement.setString(2, doctor.getSpecialty());
            praparedStatement.setBoolean(3, doctor.getMedScheduling());
            praparedStatement.setBoolean(4, doctor.getExam());
            praparedStatement.setString(5, doctor.getCPF());
            praparedStatement.execute();

            boolean existe = getId(doctor.getCRM());
            if (existe) {
                JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso!!", "Médico Validado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Médico não cadastrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar médico: " + e.getMessage());
        }
    }

    public boolean getId(String email) {
        try {
            praparedStatement = connection.prepareStatement("Select usuario_id from tb_medicos where crm=?");
            praparedStatement.setString(1, email);
            praparedStatement.execute();
            resultSet = praparedStatement.getResultSet();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.print("Error to find user id");
        }
        return false;
    }

    public void OpenScreen() {
        CreateConnection criarConexao = new CreateConnection();
        Connection connection = criarConexao.connect();

        RegisterDoctor doctor = new RegisterDoctor(connection, adminScreen);
        doctor.setLocationRelativeTo(null);
        doctor.setVisible(true);

    }

    // Variables declaration - do not modify
    private javax.swing.JPanel form;
    private javax.swing.JLabel passwordTitle;
    private javax.swing.JLabel passwordTitle5;
    private javax.swing.JLabel loginTitle;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel usernameTitle;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel usernameTitle1;
    private javax.swing.JTextField crmField;
    private javax.swing.JTextField SpecialtyField;
    private javax.swing.JLabel usernameTitle2;
    private javax.swing.JLabel passwordTitle2;
    private javax.swing.JButton loginButton1;
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel passwordTitle1;
    private javax.swing.JLabel passwordTitle3;
    private javax.swing.JTextField phoneField;
    private javax.swing.JTextField cpfField;
    private javax.swing.JTextField cepField;
    private javax.swing.JLabel passwordTitle4;
    private javax.swing.JTextField birthDateField;
    private javax.swing.JCheckBox consultaCheckBox;
    private javax.swing.JCheckBox exameCheckBox;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JLabel genderTitle;
    private javax.swing.JLabel passwordTitle6;
    private javax.swing.JPasswordField passwordField;
    // End of variables declaration
}
