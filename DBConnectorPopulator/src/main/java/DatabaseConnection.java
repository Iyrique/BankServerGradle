import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/bank.db";
        String username = "postgres";
        String password = "postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Теперь у вас есть соединение с базой данных.
            System.out.println("NICE!");
            // Не забудьте закрыть соединение, когда оно больше не нужно.
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
