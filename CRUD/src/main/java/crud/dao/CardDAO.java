package crud.dao;

import java.sql.Connection;

public class CardDAO extends AbstractDAO {
    public CardDAO(Connection connection) {
        super(connection);
    }

    public void createCard(int clientId) {

    }

    public void findCardByClientId(int clientId) {

    }

    public void findCardByClientName(String name) {

    }

    public void findCardByNumber(String number) {

    }

    public void updateCardPinById(int clientId) {

    }

    public void updateCardPinByClientName(String name) {

    }

    public void updateCardPinByNumber(String number) {

    }

    public void deleteCardByClientId(int clientId) {

    }

    public void deleteCardByCardNumber(String number) {

    }
}
