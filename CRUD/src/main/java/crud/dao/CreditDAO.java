package crud.dao;

import entity.Client;
import entity.Credit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditDAO extends AbstractDAO {
    public CreditDAO(Connection connection) {
        super(connection);
    }

    public void createCredit(int clientId, double sum, double percent, double payment, String period, String requisites) throws SQLException {
        String sql = "INSERT INTO credits (client_id, credit_sum, credit_percent, payment, period, requisites) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, clientId);
            statement.setString(2, String.valueOf(sum));
            statement.setString(3, String.valueOf(percent));
            statement.setString(4, String.valueOf(payment));
            statement.setString(5, period);
            statement.setString(6, requisites);
            statement.executeUpdate();
        }
    }


    public Credit readCreditById(int id, Client client) throws SQLException {
        String sql = "SELECT * FROM credits WHERE credit_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Credit credit = new Credit(client.getId(), client.getName(), client.getBirthday(),
                            resultSet.getString("period"),
                            Double.parseDouble(resultSet.getString("payment")),
                            Double.parseDouble(resultSet.getString("credit_percent")),
                            Double.parseDouble(resultSet.getString("credit_sum")),
                            resultSet.getString("requisites"));
                    credit.setCreditId(id);
                    return credit;
                } else {
                    return null;
                }
            }
        }
    }


    public List<Credit> readCreditsByClientId(Client client) throws SQLException {
        String sql = "SELECT * FROM credits WHERE client_id = ?";
        List<Credit> credits = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, client.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Credit credit = new Credit(client.getId(), client.getName(), client.getBirthday(),
                            resultSet.getString("period"),
                            Double.parseDouble(resultSet.getString("payment")),
                            Double.parseDouble(resultSet.getString("credit_percent")),
                            Double.parseDouble(resultSet.getString("credit_sum")),
                            resultSet.getString("requisites"));
                    credit.setCreditId(client.getId());
                    credits.add(credit);
                }
            }
        }
        return credits;
    }


    public void updateCredit(Credit credit) throws SQLException {
        String sql = "UPDATE credits SET credit_sum = ?, credit_percent = ?, payment = ?,  WHERE credit_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, String.valueOf(credit.getSum()));
            statement.setString(2, String.valueOf(credit.getPercent()));
            statement.setString(3, String.valueOf(credit.getMonthlyPayment()));
            statement.setInt(4, credit.getCreditId());
            statement.executeUpdate();
        }
    }


    public void deleteCreditById(int id) throws SQLException {
        String sql = "DELETE FROM credits WHERE credit_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Credit> getAll() {
        String sql = "SELECT * FROM credits";
        List<Credit> credits = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ClientDAO clientDAO = new ClientDAO(getConnection());
            Client client;
            while (resultSet.next()) {
                Credit credit = new Credit();
                credit.setCreditId(resultSet.getInt("credit_id"));
                credit.setClientId(resultSet.getInt("client_id"));
                client = clientDAO.readClientById(credit.getClientId(), null,null,null);
                credit.setClientName(client.getName());
                credit.setBirthday(client.getBirthday());
                credit.setSum(Double.parseDouble(resultSet.getString("credit_sum")));
                credit.setPercent(Double.parseDouble(resultSet.getString("credit_percent")));
                credit.setMonthlyPayment(Double.parseDouble(resultSet.getString("payment")));
                credit.setPeriod(resultSet.getString("period"));
                credit.setRequisites(resultSet.getString("requisites"));
                credits.add(credit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credits;
    }
}
