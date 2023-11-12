package crud;

import crud.dao.*;
import entity.Bank;

import java.sql.Connection;
import java.util.List;

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

    public void getAllBanks() {
        List<Bank> banks = bankCRUD.getAll();
        for (Bank bank: banks) {
            System.out.print("Название банка: " + bank.getName() + ", id: " + bank.getId() + ", num_clients: " + bank.getClients().size());
        }
    }

}
