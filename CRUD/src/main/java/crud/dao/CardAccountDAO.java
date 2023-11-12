package crud.dao;

import entity.Account;
import entity.Card;
import entity.CardAccount;
import entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardAccountDAO extends AbstractDAO {
    public CardAccountDAO(Connection connection) {
        super(connection);
    }

    public void createCardAccount(int clientId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM card_accounts WHERE client_id = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, clientId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count < 1) {
                    sql = "INSERT INTO card_accounts (client_id, acc_number, code_word) VALUES (?,?,?)";
                    try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
                        statement.setInt(1, clientId);
                        statement.setString(2, String.valueOf(clientId));
                        statement.setString(3, "codeword");
                        statement.executeUpdate();
                        System.out.println("Успешно");
                    }
                } else System.out.println("Больше создать нельзя!(");
            }
        }
    }

    public CardAccount readCardAccount(int clientId, Client client, Account account, Card card) throws SQLException {
        String sql = "SELECT * FROM card_accounts WHERE client_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new CardAccount(resultSet.getInt("account_id"),clientId, client.getName(), client.getBirthday(), account, resultSet.getString("code_word"),
                            card);
                } else {
                    return null;
                }
            }
        }
    }

    public void updateCardAccount(CardAccount cardAccount) throws SQLException {
        String sql = "UPDATE card_accounts SET code_word = ? WHERE client_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, cardAccount.getCodeWord());
            statement.setInt(2, cardAccount.getClientId());
            statement.executeUpdate();
        }
    }

    public void deleteCardAccount(int clientId) throws SQLException {
        String sql = "DELETE FROM card_accounts WHERE client_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, clientId);
            statement.executeUpdate();
        }
    }

    @Override
    public List<CardAccount> getAll() {
        String sql = "SELECT * FROM card_accounts";
        List<CardAccount> cardAccounts = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ClientDAO clientDAO = new ClientDAO(getConnection());
            AccountDAO accountDAO = new AccountDAO(getConnection());
            CardDAO cardDAO = new CardDAO(getConnection());
            Client client;
            Account account;
            while (resultSet.next()) {
                CardAccount cardAccount = new CardAccount();
                cardAccount.setClientId(resultSet.getInt("client_id"));
                client = clientDAO.readClientById(resultSet.getInt("client_id"), null,null,null);
                cardAccount.setClientName(client.getName());
                cardAccount.setBirthday(client.getBirthday());
                account = accountDAO.findAccountByClientId(client.getId());
                cardAccount.setAccountNumber(account);
                cardAccount.setCodeWord(resultSet.getString("code_word"));
                cardAccount.setCards(cardDAO.findCardByClientId(client.getId(), account));
                cardAccounts.add(cardAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cardAccounts;
    }
}
