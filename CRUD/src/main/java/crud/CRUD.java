package crud;

import crud.dao.*;
import entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CRUD{

    private AccountDAO accountCRUD;
    private BankDAO bankCRUD;
    private CardAccountDAO cardAccountCRUD;
    private CardDAO cardCRUD;
    private ClientDAO clientCRUD;
    private CreditDAO creditCRUD;
    private DepositDAO depositCRUD;

    public CRUD(Connection connection) {
        this.accountCRUD = new AccountDAO(connection);
        this.bankCRUD = new BankDAO(connection);
        this.cardAccountCRUD = new CardAccountDAO(connection);
        this.cardCRUD = new CardDAO(connection);
        this.clientCRUD = new ClientDAO(connection);
        this.creditCRUD = new CreditDAO(connection);
        this.depositCRUD = new DepositDAO(connection);
    }

    public void createClient(String name, String birthday) throws SQLException {
        clientCRUD.createClient(name, birthday);
        Client client = clientCRUD.readClientByName(name, null, null, null);
        createCardAccount(client.getId());
        createAccount(cardAccountCRUD.readCardAccount(client.getId(), client, null, null).getCardAccountId());
        createCard(client.getId());
    }

    public void createCredit(int clientId, double sum, double percent, double payment, String period) throws SQLException {
        String requisites = generateReq();
        creditCRUD.createCredit(clientId, sum, percent, payment, period, requisites);
    }

    public void createDeposit(int clientId, double sum, double percent, String period, boolean topUp, boolean withdraw) throws SQLException {
        String requisites = generateReq();
        depositCRUD.createDeposit(clientId, sum, percent, period, requisites, topUp, withdraw);
    }

    public void createCardAccount(int clientId) throws SQLException {
        cardAccountCRUD.createCardAccount(clientId);
    }

    public void createAccount(int cardAccountId) throws SQLException {
        accountCRUD.createAccount(cardAccountId);
    }

    public void createCard(int clientId) throws SQLException {
        Client client = clientCRUD.readClientById(clientId, null, null, null);
        cardCRUD.createCard(clientId, client, generateCreditCardNumber());
    }

    public void readClientById(int id) throws SQLException {
        Client client = clientCRUD.readClientById(id, null, null, null);
        if (client != null) {
            client.setCredits(creditCRUD.readCreditsByClientId(client.getId()));
            client.setDeposits(depositCRUD.readDepositsByClientId(client.getId()));
            client.setCardAccounts(cardAccountCRUD.readCardAccount(id, client, accountCRUD.findAccountByClientId(id),
                    cardCRUD.findCardByClientId(id,accountCRUD.findAccountByClientId(id))));
            System.out.println(client.toString());
        } else System.out.println("Клиента не существует!");
    }

    public void readClientByName(String name) throws SQLException {
        Client client = clientCRUD.readClientByName(name, null, null, null);
        if (client != null) {
            client.setCredits(creditCRUD.readCreditsByClientId(client.getId()));
            client.setDeposits(depositCRUD.readDepositsByClientId(client.getId()));
            client.setCardAccounts(cardAccountCRUD.readCardAccount(client.getId(),client,accountCRUD.findAccountByClientId(client.getId()),
                    cardCRUD.findCardByClientName(name, accountCRUD.findAccountByClientId(client.getId()))));
            System.out.println(client.toString());
        } else System.out.println("Клиента не существует!");
    }

    public void readCreditById(int creditId) throws SQLException {
        Credit credit = creditCRUD.readCreditById(creditId);
        if (credit != null) {
            System.out.println(credit.toString());
        } else System.out.println("Кредита не существует!");
    }

    public void readCreditsById(int clientId) throws SQLException {
        List<Credit> credits = new ArrayList<>();
        credits = creditCRUD.readCreditsByClientId(clientId);
        if (credits.isEmpty()) {
            System.out.println("Кредитов нет!");
        } else {
            for (Credit credit : credits) {
                System.out.println(credit.toString());
            }
        }
    }

    public void readDepositById(int depositId) throws SQLException {
        Deposit deposit = depositCRUD.readDepositById(depositId);
        if (deposit != null) {
            System.out.println(deposit.toString());
        } else System.out.println("Не существует!");
    }

    public void readDepositsById(int clientId) throws SQLException {
        List<Deposit> deposits = new ArrayList<>();
        deposits = depositCRUD.readDepositsByClientId(clientId);
        if (deposits.isEmpty()) {
            System.out.println("Депозитов нет!");
        } else {
            for (Deposit deposit : deposits) {
                System.out.println(deposit.toString());
            }
        }
    }

    public void readCardAccount(int clientId) throws SQLException {
        Account account = accountCRUD.findAccountByClientId(clientId);
        Client client = clientCRUD.readClientById(clientId, null, null, null);
        System.out.println(cardAccountCRUD.readCardAccount(clientId, client, account, cardCRUD.findCardByClientId(clientId,
                accountCRUD.findAccountByClientId(clientId))).toString());
    }

    public void readAccount(int clientId) throws SQLException {
        Account account = accountCRUD.findAccountByClientId(clientId);
        System.out.println(account.toString());
    }

    public void readCard(int clientId) throws SQLException {
        System.out.println(cardCRUD.findCardByClientId(clientId, accountCRUD.findAccountByClientId(clientId)).toString());
    }

    public void getAllBanks() {
        List<Bank> banks = bankCRUD.getAll();
        for (Bank bank: banks) {
            System.out.println(bank.toString());
        }
    }

    public void getAllClients() {
        List<Client> clients = clientCRUD.getAll();
        for (Client client: clients) {
            System.out.println(client.toString());
        }
    }

    public void getAllAccounts() {
        List<Account> accounts = accountCRUD.getAll();
        for (Account account: accounts) {
            System.out.println(account.toString());
        }
    }

    public void getAllCardAccounts() {
        List<CardAccount> cardAccounts = cardAccountCRUD.getAll();
        for (CardAccount cardAccount: cardAccounts) {
            System.out.println(cardAccount.toString());
        }
    }

    public void getAllCards() {
        List<Card> cards = cardCRUD.getAll();
        for (Card card: cards) {
            System.out.println(card.toString());
        }
    }

    public void getAllCredits() {
        List<Credit> credits = creditCRUD.getAll();
        for (Credit credit: credits) {
            System.out.println(credit.toString());
        }
    }

    public void getAllDeposits() {
        List<Deposit> deposits = depositCRUD.getAll();
        for (Deposit deposit: deposits) {
            System.out.println(deposit.toString());
        }
    }

    // Генерация случайного номера счета
    private static String generateReq() {
        Random random = new Random();
        long accountNumber = 1000000000L + random.nextInt(900000000);
        return String.valueOf(accountNumber);
    }

    // Генерация случайного номера кредитной карты
    private static String generateCreditCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder("2"); // Первая цифра 2 для Мир

        for (int i = 2; i <= 16; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
            if (i % 4 == 0 && i < 15) {
                cardNumber.append(" "); // Разделители каждые 4 цифры
            }
        }

        return cardNumber.toString();
    }
}
