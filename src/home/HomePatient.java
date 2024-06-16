package home;

import database.CreateConnection;
import java.sql.Connection;
import javax.swing.JFrame;
import login.Login;
import scheduling.patient.PatientViewMedicalScheduling;
import register.PatientRegisterMedicalExam;
import register.PatientRegisterMedicalScheduling;
import scheduling.patient.PatientViewMedicalExams;

public class HomePatient extends JFrame {

    private Connection connection;
    private String user;
    private boolean adminScreen;

    public HomePatient(Connection connection, String username, boolean adminScreen) {
        initComponents();
        this.connection = connection;
        this.user = username;
        this.adminScreen = adminScreen;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jButtonMedicalScheduling = new javax.swing.JButton();
        jButtonMedicalExam = new javax.swing.JButton();
        jButtonPatientMedicalScheduling = new javax.swing.JButton();
        jButtonPatientsMedicalExams = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();

        jMenuItem1.setText("jMenuItem1");
        jMenuItem2.setText("jMenuItem2");
        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");
        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("jCheckBoxMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 48));
        jLabel1.setText("Seja bem vindo!");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(140, 140, 346, 56);

        jButtonMedicalScheduling.setBackground(new java.awt.Color(60, 66, 108));
        jButtonMedicalScheduling.setFont(new java.awt.Font("Arial", 0, 18));
        jButtonMedicalScheduling.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMedicalScheduling.setText("Agendar consulta");
        jButtonMedicalScheduling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMedicalSchedulingActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonMedicalScheduling);
        jButtonMedicalScheduling.setBounds(190, 300, 250, 50);

        jButtonMedicalExam.setBackground(new java.awt.Color(60, 66, 108));
        jButtonMedicalExam.setFont(new java.awt.Font("Arial", 0, 18));
        jButtonMedicalExam.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMedicalExam.setText("Agendar exame");
        jButtonMedicalExam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMedicalExamActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonMedicalExam);
        jButtonMedicalExam.setBounds(190, 520, 250, 50);

        jButtonPatientMedicalScheduling.setBackground(new java.awt.Color(60, 66, 108));
        jButtonPatientMedicalScheduling.setFont(new java.awt.Font("Arial", 0, 18));
        jButtonPatientMedicalScheduling.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPatientMedicalScheduling.setText("Minhas consultas");
        jButtonPatientMedicalScheduling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPatientMedicalSchedulingActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPatientMedicalScheduling);
        jButtonPatientMedicalScheduling.setBounds(190, 360, 250, 50);

        jButtonPatientsMedicalExams.setBackground(new java.awt.Color(60, 66, 108));
        jButtonPatientsMedicalExams.setFont(new java.awt.Font("Arial", 0, 18));
        jButtonPatientsMedicalExams.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPatientsMedicalExams.setText("Meus exames");
        jButtonPatientsMedicalExams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPatientsMedicalExamsActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPatientsMedicalExams);
        jButtonPatientsMedicalExams.setBounds(190, 450, 250, 50);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(120, 270, 377, 10);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 632, 770);

        setSize(new java.awt.Dimension(633, 748));
        setLocationRelativeTo(null);
    }

    private void jButtonMedicalSchedulingActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        PatientRegisterMedicalScheduling medicalScheduling = new PatientRegisterMedicalScheduling(connection, user, adminScreen);
        medicalScheduling.setLocationRelativeTo(null);
        medicalScheduling.setVisible(true);
    }

    private void jButtonMedicalExamActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        PatientRegisterMedicalExam medicalExam = new PatientRegisterMedicalExam(connection, user, adminScreen);
        medicalExam.setLocationRelativeTo(null);
        medicalExam.setVisible(true);
    }

    private void jButtonPatientMedicalSchedulingActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        PatientViewMedicalScheduling patientMedicalAppointments = new PatientViewMedicalScheduling(connection, user, adminScreen);
        patientMedicalAppointments.setLocationRelativeTo(null);
        patientMedicalAppointments.setVisible(true);
    }

    private void jButtonPatientsMedicalExamsActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        PatientViewMedicalExams patientMedicalExams = new PatientViewMedicalExams(connection, user, adminScreen);
        patientMedicalExams.setLocationRelativeTo(null);
        patientMedicalExams.setVisible(true);
    }

    public void OpenScreen() {
        CreateConnection criarConexao = new CreateConnection();
        Connection connection = criarConexao.connect();

        Login login = new Login(connection, adminScreen);
        String username = login.getUsername();

        HomePatient homePatient = new HomePatient(connection, username, adminScreen);
        homePatient.setLocationRelativeTo(null);
        homePatient.setVisible(true);
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButtonMedicalExam;
    private javax.swing.JButton jButtonMedicalScheduling;
    private javax.swing.JButton jButtonPatientMedicalScheduling;
    private javax.swing.JButton jButtonPatientsMedicalExams;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration
}
