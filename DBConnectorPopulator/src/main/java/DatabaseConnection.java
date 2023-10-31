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
//            BankDAO bankDAO = new BankDAO(connection);
//            bankDAO.insertObject("Sber");
//            bankDAO.insertObject("Tinkoff");
//            bankDAO.insertObject("Gazprom");
//            bankDAO.insertObject("Alfa");
//            bankDAO.insertObject("VTB");
//            bankDAO.insertObject("PochtaBank");
//            bankDAO.getAll();
//            ClientDAO clientDAO = new ClientDAO(connection);
//            clientDAO.insertObjects();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
