package home;

import dashboard.ScreenDashboard;
import database.CreateConnection;
import java.sql.Connection;
import register.RegisterDoctor;
import scheduling.admin.AdminViewAllPatients;
import scheduling.admin.AdminViewMedicalExams;
import scheduling.admin.AdminViewMedicalScheduling;

public class HomeAdmin extends javax.swing.JFrame {

    private Connection connection;
    private boolean adminScreen;

    public HomeAdmin(Connection connection, boolean adminScreen) {
        initComponents();
        this.connection = connection;
        this.adminScreen = adminScreen;
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButtonVerifyScheduling = new javax.swing.JButton();
        jButtonVerifyExams = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jButtonVerifyDashboard = new javax.swing.JButton();
        jButtonRegisterDoctor = new javax.swing.JButton();
        jButtonVerifyPatients = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48));
        jLabel1.setText("Seja bem vindo!");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(139, 110, 366, 53);

        jButtonVerifyScheduling.setBackground(new java.awt.Color(60, 66, 108));
        jButtonVerifyScheduling.setFont(new java.awt.Font("Arial", 0, 18));
        jButtonVerifyScheduling.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVerifyScheduling.setText("Consultas");
        jButtonVerifyScheduling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerifySchedulingActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonVerifyScheduling);
        jButtonVerifyScheduling.setBounds(139, 317, 366, 50);

        jButtonVerifyExams.setBackground(new java.awt.Color(60, 66, 108));
        jButtonVerifyExams.setFont(new java.awt.Font("Arial", 0, 18));
        jButtonVerifyExams.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVerifyExams.setText("Exames");
        jButtonVerifyExams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerifyExamsActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonVerifyExams);
        jButtonVerifyExams.setBounds(139, 232, 366, 50);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Tela de Admin");

        jButtonVerifyDashboard.setBackground(new java.awt.Color(60, 66, 108));
        jButtonVerifyDashboard.setFont(new java.awt.Font("Arial", 0, 18));
        jButtonVerifyDashboard.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVerifyDashboard.setText("Dashboard");
        jButtonVerifyDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerifyDashboardActionPerformed(evt);
            }
        });

        jButtonRegisterDoctor.setBackground(new java.awt.Color(60, 66, 108));
        jButtonRegisterDoctor.setFont(new java.awt.Font("Arial", 0, 18));
        jButtonRegisterDoctor.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRegisterDoctor.setText("Cadastrar medico");
        jButtonRegisterDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegisterDoctorActionPerformed(evt);
            }
        });

        jButtonVerifyPatients.setBackground(new java.awt.Color(60, 66, 108));
        jButtonVerifyPatients.setFont(new java.awt.Font("Arial", 0, 18));
        jButtonVerifyPatients.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVerifyPatients.setText("Pacientes");
        jButtonVerifyPatients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerifyPatientsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonVerifyDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonVerifyPatients, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRegisterDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel2)
                .addGap(90, 90, 90)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(jButtonVerifyPatients, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButtonRegisterDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(jButtonVerifyDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 650, 740);

        setSize(new java.awt.Dimension(632, 747));
        setLocationRelativeTo(null);
    }

    private void jButtonRegisterDoctorActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        RegisterDoctor doctor = new RegisterDoctor(connection, adminScreen);
        doctor.setLocationRelativeTo(null);
        doctor.setVisible(true);
    }

    private void jButtonVerifyPatientsActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        AdminViewAllPatients allPatients = new AdminViewAllPatients(connection, "", adminScreen);
        allPatients.setLocationRelativeTo(null);
        allPatients.setVisible(true);
    }

    private void jButtonVerifySchedulingActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        AdminViewMedicalScheduling allMedicalScheduling = new AdminViewMedicalScheduling(connection, "", adminScreen);
        allMedicalScheduling.setLocationRelativeTo(null);
        allMedicalScheduling.setVisible(true);
    }

    private void jButtonVerifyExamsActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        AdminViewMedicalExams allMedicalExams = new AdminViewMedicalExams(connection, "", adminScreen);
        allMedicalExams.setLocationRelativeTo(null);
        allMedicalExams.setVisible(true);
    }

    private void jButtonVerifyDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        ScreenDashboard screenDashboard = new ScreenDashboard();
        screenDashboard.setLocationRelativeTo(null);
        screenDashboard.setVisible(true);
    }

    public void openScreen() {
        CreateConnection criarConexao = new CreateConnection();
        Connection connection = criarConexao.connect();
        HomeAdmin homeAdmin = new HomeAdmin(connection, adminScreen);
        homeAdmin.setLocationRelativeTo(null);
        homeAdmin.setVisible(true);
    }

    private javax.swing.JButton jButtonRegisterDoctor;
    private javax.swing.JButton jButtonVerifyDashboard;
    private javax.swing.JButton jButtonVerifyExams;
    private javax.swing.JButton jButtonVerifyPatients;
    private javax.swing.JButton jButtonVerifyScheduling;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
}
