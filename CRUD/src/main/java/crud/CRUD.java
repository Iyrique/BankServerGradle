package crud;

import crud.dao.*;

import java.sql.Connection;

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



}
