package crud.dao;

import entity.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankDAO extends AbstractDAO {

    public BankDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Bank> getAll(){
        String sql = "SELECT * FROM bank";
        List<Bank> banks = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Bank bank = new Bank();
                bank.setId(resultSet.getInt("bank_id"));
                bank.setName(resultSet.getString("bank_name"));
                banks.add(bank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banks;
    }
}
