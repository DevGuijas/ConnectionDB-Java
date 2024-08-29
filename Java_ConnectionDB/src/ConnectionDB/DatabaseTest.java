package ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseTest {
    private static final String URL = "jdbc:postgresql://autorack.proxy.rlwy.net:45179/railway";
    private static final String USER = "postgres";
    private static final String PASSWORD = "LHqcDvopwywdoBcwlMNjIVhRGFFQFouS";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Conectar ao banco de dados
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");

            // Preparar a query para buscar dados da tabela 'users'
            String query = "SELECT id, username, password FROM users";
            stmt = connection.prepareStatement(query);

            // Executar a query
            rs = stmt.executeQuery();

            // Exibir os resultados
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                System.out.println("ID: " + id);
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                System.out.println("--------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } finally {
            // Fechar os recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}