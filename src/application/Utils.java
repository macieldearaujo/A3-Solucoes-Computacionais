package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author andressa
 */
public class Utils {

    public static String getPatientId(Connection connection, String username) throws SQLException {
        String user_id = "";
        String patient_id = "";

        PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT usuario_id from tb_usuarios WHERE email = ?");
        preparedStatement1.setString(1, username);
        try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
            if (resultSet1.next()) {
                user_id = resultSet1.getString("usuario_id");
            }
        }

        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT pacientes_id FROM tb_pacientes WHERE usuario_id = ?");
        preparedStatement2.setString(1, user_id);
        try (ResultSet resultSet2 = preparedStatement2.executeQuery()) {
            if (resultSet2.next()) {
                patient_id = resultSet2.getString("pacientes_id");
            }
        }
        return patient_id;

    }

    public static String getDoctorIDByCPF(Connection connection, String cpf) throws SQLException {
        String user_id = "";
        String doctor_id = "";

        PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT usuario_id from tb_usuarios WHERE cpf = ?");
        preparedStatement1.setString(1, cpf);

        try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
            if (resultSet1.next()) {
                user_id = resultSet1.getString("usuario_id");
            }
        }

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT medico_id from tb_medicos WHERE usuario_id = ?");
        preparedStatement.setString(1, user_id);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                doctor_id = resultSet.getString("medico_id");
            }
        }
        return doctor_id;
    }

    public static Map<String, String> getDoctorsBySpecialty(Connection connection, String specialty, String queryDoctorBySpecialty) throws SQLException {
        PreparedStatement preparedStatement1 = connection.prepareStatement(queryDoctorBySpecialty);
        preparedStatement1.setString(1, specialty);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        List<String> doctorsCpf = new ArrayList<>();

        Map<String, String> doctorsCrmMap = new HashMap<>();
        while (resultSet1.next()) {
            String cpf = resultSet1.getString("cpf");
            String crm = resultSet1.getString("crm");
            doctorsCpf.add(cpf);
            doctorsCrmMap.put(cpf, crm);
        }

        String baseQueryMapping = "SELECT nome, cpf FROM tb_usuarios WHERE cpf IN (";
        StringJoiner joiner = new StringJoiner(", ", baseQueryMapping, ")");
        for (int i = 0; i < doctorsCpf.size(); i++) {
            joiner.add("?");
        }
        String query = joiner.toString();

        PreparedStatement preparedStatement2 = connection.prepareStatement(query);
        for (int i = 0; i < doctorsCpf.size(); i++) {
            preparedStatement2.setString(i + 1, doctorsCpf.get(i));
        }
        ResultSet resultSet2 = preparedStatement2.executeQuery();

        Map<String, String> doctors = new HashMap<>();
        while (resultSet2.next()) {
            String name = resultSet2.getString("nome");
            String cpf = resultSet2.getString("cpf");
            String crm = doctorsCrmMap.get(cpf);
            doctors.put(name + " - CRM " + crm, cpf);
        }
        doctorsCpf.clear();
        doctorsCrmMap.clear();

        return doctors;
    }
    
    public static String convertDate(String birthDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Faz o parsing da String para LocalDate usando o formato de entrada e formata para o formato desejado
        String formattedDate = LocalDate.parse(birthDate, inputFormatter).format(outputFormatter);
        // Exibe o resultado
        return formattedDate;
    }
}
