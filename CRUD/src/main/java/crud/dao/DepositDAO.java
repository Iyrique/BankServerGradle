package crud.dao;

import entity.Client;
import entity.Deposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepositDAO extends AbstractDAO {
    public DepositDAO(Connection connection) {
        super(connection);
    }


    public void createDeposit(int clientId, double sum, double percent, String period, String requisites, boolean topUp, boolean withdraw) throws SQLException {
        String sql = "INSERT INTO deposits (cl_id, sum, percent, period, requisites, top_up, withdraw) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, clientId);
            statement.setString(2, String.valueOf(sum));
            statement.setString(3, String.valueOf(percent));
            statement.setString(4, period);
            statement.setString(5, requisites);
            statement.setBoolean(6, topUp);
            statement.setBoolean(7, withdraw);
            statement.executeUpdate();
        }
    }

    public Deposit readDepositById(int id, Client client) throws SQLException {
        String sql = "SELECT * FROM deposits WHERE dep_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Deposit deposit = new Deposit(client.getId(), client.getName(), client.getBirthday(), Double.parseDouble(resultSet.getString("sum")),
                            Double.parseDouble(resultSet.getString("percent")), resultSet.getString("period"),
                            resultSet.getString("requisites"), resultSet.getBoolean("top_up"), resultSet.getBoolean("withdraw"));
                    deposit.setDepositId(id);
                    return deposit;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Deposit> readDepositsByClientId(Client client) throws SQLException {
        String sql = "SELECT * FROM deposits WHERE cl_id = ?";
        List<Deposit> deposits = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, client.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Deposit deposit = new Deposit(client.getId(), client.getName(), client.getBirthday(), Double.parseDouble(resultSet.getString("sum")),
                            Double.parseDouble(resultSet.getString("percent")), resultSet.getString("period"),
                            resultSet.getString("requisites"), resultSet.getBoolean("top_up"), resultSet.getBoolean("withdraw"));
                    deposit.setDepositId(resultSet.getInt("dep_id"));
                    deposits.add(deposit);
                }
            }
        }
        return deposits;
    }

    public void updateDeposit(Deposit deposit) throws SQLException {
        String sql = "UPDATE deposits SET sum = ?, percent = ? WHERE dep_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, String.valueOf(deposit.getSum()));
            statement.setString(2, String.valueOf(deposit.getPercent()));
            statement.setInt(3, deposit.getDepositId());
            statement.executeUpdate();
        }
    }

    public void deleteDepositById(int id) throws SQLException {
        String sql = "DELETE FROM deposits WHERE dep_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Deposit> getAll() {
        String sql = "SELECT * FROM deposits";
        List<Deposit> deposits = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ClientDAO clientDAO = new ClientDAO(getConnection());
            Client client;
            while (resultSet.next()) {
                Deposit deposit = new Deposit();
                deposit.setDepositId(resultSet.getInt("dep_id"));
                deposit.setClientId(resultSet.getInt("cl_id"));
                client = clientDAO.readClientById(deposit.getClientId(), null,null,null);
                deposit.setClientName(client.getName());
                deposit.setBirthday(client.getBirthday());
                deposit.setSum(Double.parseDouble(resultSet.getString("sum")));
                deposit.setPercent(Double.parseDouble(resultSet.getString("percent")));
                deposit.setPeriod(resultSet.getString("period"));
                deposit.setRequisites(resultSet.getString("requisites"));
                deposit.setAbilityTopUp(resultSet.getBoolean("top_up"));
                deposit.setAbilityWithdraw(resultSet.getBoolean("withdraw"));
                deposits.add(deposit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deposits;
    }
}
