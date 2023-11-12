package crud.dao;

import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends AbstractDAO {

    public ClientDAO(Connection connection) {
        super(connection);
    }

    public void createClient(String name, String birthday) throws SQLException {
        String sql = "INSERT INTO clients (bank_id, client_name, birthday) VALUES (?,?,?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, 1);
            statement.setString(2, name);
            statement.setString(3, birthday);
            statement.executeUpdate();
            System.out.println("Добавление успешно!");
        }
    }


    public Client readClientById(int id, List<Credit> credits, List<Deposit> deposits, CardAccount cardAccount) throws SQLException {
        String sql = "SELECT * FROM clients WHERE client_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(id, resultSet.getString("client_name"), resultSet.getString("birthday"),
                            credits, deposits, cardAccount);
                } else {
                    return null;
                }
            }
        }
    }


    public Client readClientByName(String name, List<Credit> credits, List<Deposit> deposits, CardAccount cardAccount) throws SQLException {
        String sql = "SELECT * FROM clients WHERE client_name = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(resultSet.getInt("client_id"), name, resultSet.getString("birthday"),
                            credits, deposits, cardAccount);
                } else {
                    return null;
                }
            }
        }
    }


    public void deleteClient(int id) throws SQLException {
        String sql = "DELETE FROM clients WHERE client_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Клиент удален!");
        }
    }

    public void updateClient(Client client) throws SQLException {
        String sql = "UPDATE clients SET client_name = ? WHERE client_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setInt(2, client.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Client> getAll() {
        String sql = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            CreditDAO creditDAO = new CreditDAO(getConnection());
            DepositDAO depositDAO = new DepositDAO(getConnection());
            CardAccountDAO cardAccountDAO = new CardAccountDAO(getConnection());
            CardDAO cardDAO = new CardDAO(getConnection());
            AccountDAO accountDAO = new AccountDAO(getConnection());
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("client_id"));
                client.setName(resultSet.getString("client_name"));
                client.setBirthday(resultSet.getString("birthday"));
                client.setCredits(creditDAO.readCreditsByClientId(client.getId()));
                client.setDeposits(depositDAO.readDepositsByClientId(client.getId()));
                Account account = accountDAO.findAccountByClientId(client.getId());
                client.setCardAccounts(cardAccountDAO.readCardAccount(client.getId(), client,
                        account, cardDAO.findCardByClientId(client.getId(), account)));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
