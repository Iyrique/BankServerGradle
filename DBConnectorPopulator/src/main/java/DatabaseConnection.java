import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static void main(String[] args) throws IOException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/bank.db";
        String username = "postgres";
        String password = "postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connect!");
            DAO dao = new DAO(connection);
//            dao.insertObjectToBank(1, "Sber");
//            dao.insertObjectsToClients();
            dao.insertObjectsToCredits();
//            dao.insertObjectsToDeposits();
//            dao.insertObjectsToCardAccount();
//            dao.insertObjectsToAccount();
//            dao.insertObjectsToCards();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
