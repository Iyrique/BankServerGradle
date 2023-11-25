package crud;

import crud.dao.*;
import entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CRUD {

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

    public Client readClientById(int id) throws SQLException {
        Client client = clientCRUD.readClientById(id, null, null, null);
        if (client != null) {
            client.setCredits(creditCRUD.readCreditsByClientId(client.getId()));
            client.setDeposits(depositCRUD.readDepositsByClientId(client.getId()));
            client.setCardAccounts(cardAccountCRUD.readCardAccount(id, client, accountCRUD.findAccountByClientId(id),
                    cardCRUD.findCardByClientId(id, accountCRUD.findAccountByClientId(id))));
        }
        return client;
    }

    public Client readClientByName(String name) throws SQLException {
        Client client = clientCRUD.readClientByName(name, null, null, null);
        if (client != null) {
            client.setCredits(creditCRUD.readCreditsByClientId(client.getId()));
            client.setDeposits(depositCRUD.readDepositsByClientId(client.getId()));
            client.setCardAccounts(cardAccountCRUD.readCardAccount(client.getId(), client, accountCRUD.findAccountByClientId(client.getId()),
                    cardCRUD.findCardByClientName(name, accountCRUD.findAccountByClientId(client.getId()))));
        }
        return client;
    }

    public Credit readCreditById(int creditId) throws SQLException {
        return creditCRUD.readCreditById(creditId);

    }

    public List<Credit> readCreditsById(int clientId) throws SQLException {
        return creditCRUD.readCreditsByClientId(clientId);
    }

    public Deposit readDepositById(int depositId) throws SQLException {
        return depositCRUD.readDepositById(depositId);
    }

    public List<Deposit> readDepositsById(int clientId) throws SQLException {
        return depositCRUD.readDepositsByClientId(clientId);
    }

    public CardAccount readCardAccount(int clientId) throws SQLException {
        Account account = accountCRUD.findAccountByClientId(clientId);
        Client client = clientCRUD.readClientById(clientId, null, null, null);
        return cardAccountCRUD.readCardAccount(clientId, client, account, cardCRUD.findCardByClientId(clientId,
                accountCRUD.findAccountByClientId(clientId)));
    }

    public Account readAccount(int clientId) throws SQLException {
        return accountCRUD.findAccountByClientId(clientId);
    }

    public Card readCard(int clientId) throws SQLException {
        return cardCRUD.findCardByClientId(clientId, accountCRUD.findAccountByClientId(clientId));
    }

    public void updateClient(int clientId, String name) throws SQLException {
        Client client = clientCRUD.readClientById(clientId, null, null, null);
        if (client != null) {
            client.setName(name);
            client.setCredits(creditCRUD.readCreditsByClientId(client.getId()));
            client.setDeposits(depositCRUD.readDepositsByClientId(client.getId()));
            client.setCardAccounts(cardAccountCRUD.readCardAccount(clientId, client, accountCRUD.findAccountByClientId(clientId),
                    cardCRUD.findCardByClientId(clientId, accountCRUD.findAccountByClientId(clientId))));
            clientCRUD.updateClient(client);
            System.out.println("Обновлено!");
        } else System.out.println("Клиента не существует!");
    }

    public void updateCredit(int id, double sum, double percent, double payment) throws SQLException {
        Credit credit = creditCRUD.readCreditById(id);
        if (credit != null) {
            credit.setSum(sum);
            credit.setPercent(percent);
            credit.setMonthlyPayment(payment);
            creditCRUD.updateCredit(credit);
        } else System.out.println("Кредита нет");
    }

    public void updateDeposit(int id, double sum, double percent) throws SQLException {
        Deposit deposit = depositCRUD.readDepositById(id);
        if (deposit != null) {
            deposit.setSum(sum);
            deposit.setPercent(percent);
            depositCRUD.updateDeposit(deposit);
        } else System.out.println("Депозита нет!");
    }

//    public void updateCardAccount(int id) throws SQLException {
//        Account account = accountCRUD.findAccountByClientId(id);
//        Client client = clientCRUD.readClientById(id, null, null, null);
//        Scanner scanner = new Scanner(System.in);
//        CardAccount cardAccount = null;
//        if (client.getCardAccounts().getCard() != null) {
//            cardAccount = cardAccountCRUD.readCardAccount(id, client, account, client.getCardAccounts().getCard());
//        }
//        if (cardAccount != null) {
//            System.out.println("Введите кодовое слово:");
//            cardAccount.setCodeWord(scanner.next());
//            cardAccountCRUD.updateCardAccount(cardAccount);
//        } else System.out.println("CardAccount null!");
//    }
//
//    public void updateAccount(int id) throws SQLException {
//        Account account = accountCRUD.findAccountByClientId(id);
//        Scanner scanner = new Scanner(System.in);
//        if (account != null) {
//            account.setBalance(scanner.nextDouble());
//            accountCRUD.updateAccount(account);
//        }
//    }

    public void deleteClient(int clientId) throws SQLException {
        cardCRUD.deleteCard(clientId);
        Account account = accountCRUD.findAccountByClientId(clientId);
        if (account != null) accountCRUD.deleteAccount(account.getCardAccId());
        cardAccountCRUD.deleteCardAccount(clientId);
        List<Credit> credits = credits = creditCRUD.readCreditsByClientId(clientId);
        if (credits.isEmpty()) {
            System.out.println("Кредитов нет!");
        } else {
            for (Credit credit : credits) {
                creditCRUD.deleteCreditById(credit.getCreditId());
            }
        }
        List<Deposit> deposits = depositCRUD.readDepositsByClientId(clientId);
        if (deposits.isEmpty()) {
            System.out.println("Депозитов нет!");
        } else {
            for (Deposit deposit : deposits) {
                depositCRUD.deleteDepositById(deposit.getDepositId());
            }
        }
        clientCRUD.deleteClient(clientId);
    }

    public void deleteCredit(int creditId) throws SQLException {
        creditCRUD.deleteCreditById(creditId);
    }

    public void deleteDeposit(int depositId) throws SQLException {
        depositCRUD.deleteDepositById(depositId);
    }

    public void deleteCardAccount(int clientId) throws SQLException {
        cardCRUD.deleteCard(clientId);
        Account account = accountCRUD.findAccountByClientId(clientId);
        if (account != null) accountCRUD.deleteAccount(account.getCardAccId());
        cardAccountCRUD.deleteCardAccount(clientId);
    }

    public void deleteAccount(int clientId) throws SQLException {
        cardCRUD.deleteCard(clientId);
        Account account = accountCRUD.findAccountByClientId(clientId);
        accountCRUD.deleteAccount(account.getCardAccId());
    }

    public void deleteCard(int clientId) throws SQLException {
        cardCRUD.deleteCard(clientId);
    }

    public List<Bank> getAllBanks() {
        return bankCRUD.getAll();
    }

    public List<Client> getAllClients() {
        return clientCRUD.getAll();
    }

    public List<Account> getAllAccounts() {
        return accountCRUD.getAll();
    }

    public List<CardAccount> getAllCardAccounts() {
        return cardAccountCRUD.getAll();
    }

    public List<Card> getAllCards() {
        return cardCRUD.getAll();
    }

    public List<Credit> getAllCredits() {
        return creditCRUD.getAll();
    }

    public List<Deposit> getAllDeposits() {
        return depositCRUD.getAll();
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
