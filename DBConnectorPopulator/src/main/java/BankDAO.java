import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankDAO {

    private Connection connection;

    public BankDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertObject(String name) {
        String sql = "INSERT INTO bank (bank_name) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Insert successful.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                System.out.println("Error: Duplicate entry. The name already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void getAll() {
        String sql = "SELECT * FROM bank";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Bank> banks = new ArrayList<>();
            while (resultSet.next()) {
                Bank bank = new Bank();
                bank.setId(resultSet.getInt("bank_id"));
                bank.setName(resultSet.getString("bank_name"));
                banks.add(bank);
            }
            banks.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
