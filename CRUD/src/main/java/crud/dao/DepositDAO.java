package crud.dao;

import java.sql.Connection;

public class DepositDAO extends AbstractDAO {
    public DepositDAO(Connection connection) {
        super(connection);
    }


    public void createDepositWithClientId(int clientId, String sum, String percent, String period, Boolean topUp, boolean withdraw) {

    }

    public void createDepositWithClientNameAndBirthday(String name, String birthday, String sum, String percent, String period, Boolean topUp, boolean withdraw) {

    }

    public void readDepositById(int id) {

    }

    public void readDepositsByClientId(int clientId) {

    }

    public void updateDepositById(int id) {

    }

    public void deleteDepositById(int id) {

    }
}
