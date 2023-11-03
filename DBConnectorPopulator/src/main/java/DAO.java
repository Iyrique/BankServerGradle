import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private Connection connection;
    private Deserialization<Client> deserialization;
    private final List<Client> OBJECTS;


    public DAO(Connection connection) throws IOException {
        this.connection = connection;
        this.deserialization = new Deserialization<>();
        String FILEPATH = "clients.json";
        this.OBJECTS = deserialization.deserializeJsonToObjects(FILEPATH, Client.class);
    }

    public void insertObjectToBank(String bankName) {
        String sql = "INSERT INTO bank (bank_name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bankName);
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

    public void getAllBanks() {
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

    public void insertObjectsToClients() throws IOException {
        String sql = "INSERT INTO clients (client_id, bank_id, client_name, birthday) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Client client : OBJECTS) {
                statement.setInt(1, client.getId());
                statement.setInt(2, 1);
                statement.setString(3, client.getName());
                statement.setString(4, client.getBirthday());
                statement.executeUpdate();
            }
            System.out.println("Insert clients successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertObjectsToCredits() {
        String sql = "INSERT INTO credits (client_id, credit_sum, credit_percent, monthlyPayment, period, requisites) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Client client : OBJECTS) {
                if (!client.getCredits().isEmpty()) {
                    for (int i = 0; i < client.getCredits().size(); i++) {
                        statement.setInt(1, client.getId());
                        statement.setString(2, Double.toString(client.getCredits().get(i).getSum()));
                        statement.setString(3, Double.toString(client.getCredits().get(i).getPercent()));
                        statement.setString(4, Double.toString(client.getCredits().get(i).getMonthlyPayment()));
                        statement.setString(5, client.getCredits().get(i).getPeriod());
                        statement.setString(6, client.getCredits().get(i).getRequisites());
                        statement.executeUpdate();
                    }
                }
            }
            System.out.println("Insert credits successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertObjectsToDeposits() {
        String sql = "INSERT INTO deposits (cl_id, sum, percent, period, requisites, topUp, withdraw) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Client client : OBJECTS) {
                if (!client.getDeposits().isEmpty()) {
                    for (int i = 0; i < client.getDeposits().size(); i++) {
                        statement.setInt(1, client.getId());
                        statement.setString(2, Double.toString(client.getDeposits().get(i).getSum()));
                        statement.setString(3, Double.toString(client.getDeposits().get(i).getPercent()));
                        statement.setString(4, client.getDeposits().get(i).getPeriod());
                        statement.setString(5, client.getDeposits().get(i).getRequisites());
                        statement.setBoolean(6, client.getDeposits().get(i).isAbilityTopUp());
                        statement.setBoolean(7, client.getDeposits().get(i).isAbilityWithdraw());
                        statement.executeUpdate();
                    }
                }
            }
            System.out.println("Insert deposits successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertObjectsToCardAccount() {
        String sql = "INSERT INTO card_accounts (client_id, acc_number, codeWord) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Client client : OBJECTS) {
                CardAccount cardAccount = client.getCardAccounts();
                statement.setInt(1, client.getId());
                statement.setString(2, String.valueOf(cardAccount.getAccountNumber().getCardAccId()));
                statement.setString(3, cardAccount.getCodeWord());
                statement.executeUpdate();
            }
            System.out.println("Insert card account successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertObjectsToAccount() {
        String sql = "INSERT INTO account (balance, card_account_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Client client : OBJECTS) {
                Account account = client.getCardAccounts().getAccountNumber();
                statement.setString(1, String.valueOf(account.getBalance()));
                statement.setInt(2, account.getCardAccId());
                statement.executeUpdate();
            }
            System.out.println("Insert accounts successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertObjectsToCards() {
        String sql = "INSERT INTO cards (client_id, card_number, card_period, person_name, cvv, codeForCheckCVV, PIN) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Client client : OBJECTS) {
                Card card = client.getCardAccounts().getCard();
                statement.setInt(1, client.getId());
                statement.setString(2, card.getCardNumber());
                statement.setString(3, card.getCardPeriod());
                statement.setString(4, client.getName());
                statement.setString(5, card.getCVV());
                statement.setString(6, card.getCodeForCheckCVV());
                statement.setString(7, String.valueOf(card.getPIN()));
                statement.executeUpdate();
            }
            System.out.println("Insert cards successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
