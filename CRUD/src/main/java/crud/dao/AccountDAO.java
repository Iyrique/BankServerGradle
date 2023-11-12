package crud.dao;

import entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends AbstractDAO {

    public AccountDAO(Connection connection) {
        super(connection);
    }

    public void createAccount(int cardAccountId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM account WHERE card_account_id = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, cardAccountId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count < 1) {
                    sql = "INSERT INTO account (balance, card_account_id) VALUES (?,?)";
                    try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
                        statement.setString(1, "0");
                        statement.setInt(2, cardAccountId);
                        statement.executeUpdate();
                        System.out.println("Успешно");
                    }
                } else System.out.println("Уже существует! Больше нельзя");
            }
        }
    }

    public Account findAccountByClientId(int clientId) throws SQLException {
        String sql = "SELECT * FROM card_accounts WHERE client_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int accId = resultSet.getInt("account_id");
                    sql = "SELECT * FROM account WHERE card_account_id = ?";
                    try (PreparedStatement statement1 = getConnection().prepareStatement(sql);){
                        statement1.setInt(1, accId);
                        try (ResultSet resultSet1 = statement1.executeQuery();){
                            if (resultSet1.next()) {
                                return new Account(accId, Double.parseDouble(resultSet1.getString("balance")));
                            }

                        }
                    }

                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public void updateAccount(Account account) throws SQLException {
        String sql = "UPDATE account SET balance = ? WHERE card_account_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, String.valueOf(account.getBalance()));
            statement.setInt(2, account.getCardAccId());
            statement.executeUpdate();
        }
    }

    public void deleteAccount(int cardAccountId) throws SQLException {
        String sql = "DELETE FROM account WHERE card_account_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, cardAccountId);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Account> getAll() {
        String sql = "SELECT * FROM account";
        List<Account> accounts = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Account account = new Account();
                account.setCardAccId(resultSet.getInt("card_account_id"));
                account.setBalance(Double.parseDouble(resultSet.getString("balance")));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

}
