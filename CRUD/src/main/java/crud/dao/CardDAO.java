package crud.dao;

import entity.Account;
import entity.Card;
import entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDAO extends AbstractDAO {

    public CardDAO(Connection connection) {
        super(connection);
    }

    public void createCard(int clientId, Client client, String cardNumber) throws SQLException {
        String sql = "SELECT COUNT(*) FROM cards WHERE client_id = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, clientId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count < 1) {
                    sql = "INSERT INTO cards (client_id, card_number, person_name) VALUES (?,?,?)";
                    try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
                        statement.setInt(1, client.getId());
                        statement.setString(2, cardNumber);
                        statement.setString(3, client.getName());
                        statement.executeUpdate();
                    }
                }
            }
        }
    }

    public Card findCardByClientId(int clientId, Account account) throws SQLException {
        String sql = "SELECT * FROM cards WHERE client_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Card(resultSet.getString("card_number"),
                            resultSet.getString("card_period"), resultSet.getString("person_name"),
                            resultSet.getString("cvv"), resultSet.getString("code_for_cvv"),
                            Integer.parseInt(resultSet.getString("pin")), account);
                } else {
                    return null;
                }
            }
        }
    }

    public Card findCardByClientName(String name, Account account) throws SQLException {
        String sql = "SELECT * FROM cards WHERE person_name = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Card(resultSet.getString("card_number"),
                            resultSet.getString("card_period"), resultSet.getString("person_name"),
                            resultSet.getString("cvv"), resultSet.getString("code_for_cvv"),
                            Integer.parseInt(resultSet.getString("pin")), account);
                } else {
                    return null;
                }
            }
        }
    }

    public Card findCardByNumber(String number, Account account) throws SQLException {
        String sql = "SELECT * FROM cards WHERE card_number = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, number);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    AccountDAO accountDAO = new AccountDAO(getConnection());
                    return new Card(resultSet.getString("card_number"),
                            resultSet.getString("card_period"), resultSet.getString("person_name"),
                            resultSet.getString("cvv"), resultSet.getString("code_for_cvv"),
                            Integer.parseInt(resultSet.getString("pin")), account);
                } else {
                    return null;
                }
            }
        }
    }


    public void updateCardPin(Card card) throws SQLException {
        String sql = "UPDATE cards SET pin = ? WHERE card_number = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(2, card.getCardNumber());
            statement.setString(1, String.valueOf(card.getPIN()));
            statement.executeUpdate();
        }
    }

    public void deleteCard(int clientId) throws SQLException {
        String sql = "DELETE FROM cards WHERE client_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, clientId);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Card> getAll() {
        String sql = "SELECT * FROM cards";
        List<Card> cards = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            AccountDAO accountDAO = new AccountDAO(getConnection());
            ClientDAO clientDAO = new ClientDAO(getConnection());
            while (resultSet.next()) {
                Card card = new Card();
                card.setCardNumber(resultSet.getString("card_number"));
                card.setCardPeriod(resultSet.getString("card_period"));
                card.setPersonName(resultSet.getString("person_name"));
                card.setCVV(resultSet.getString("cvv"));
                card.setCodeForCheckCVV(resultSet.getString("code_for_cvv"));
                card.setPIN(Integer.parseInt(resultSet.getString("pin")));
                card.setAccount(accountDAO.findAccountByCardAccountId(clientDAO.readClientByName(resultSet.getString("person_name"), null, null, null).getId()));
                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }
}
