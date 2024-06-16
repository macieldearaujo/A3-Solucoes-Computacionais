package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateConnection {
    private String serverName = "localhost";
    private String mydatabase = "db_hospital";
    private String usuario = "root";
    private String senha = "1234";

    private String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

    public Connection connect() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, usuario, senha);

            System.out.println("CONEXÃO FEITA COM SUCESSO!!");

        } catch (SQLException e) {
            System.out.println("FALHA AO CONECTAR COM O BANCO DE DADOS");
        }

        return connection;
    }

    public void fecharConexao(ResultSet rs, PreparedStatement stmt) {

        try {
            rs.close();

            stmt.close();

        } catch (SQLException e) {
            System.out.println("erro ao fechar objetos");
            e.printStackTrace();
        }
    }
}
