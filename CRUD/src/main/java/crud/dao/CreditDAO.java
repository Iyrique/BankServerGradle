package crud.dao;

import java.sql.Connection;

public class CreditDAO extends AbstractDAO {
    public CreditDAO(Connection connection) {
        super(connection);
    }


    public void createCreditWithClientId(int clientId, String sum, String percent, String payment, String period) {

    }


    public void createCreditWithClientNameAndBirthday(int name, String birthday, String sum, String percent, String payment, String period) {

    }


    public void readCreditById(int id) {

    }


    public void readCreditsByClientId(int clientId) {

    }


    public void updateCreditById(int id) {

    }


    public void deleteCreditById(int id) {

    }
}
