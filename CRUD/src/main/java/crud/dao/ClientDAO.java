package crud.dao;

import java.sql.Connection;

public class ClientDAO extends AbstractDAO {

    public ClientDAO(Connection connection) {
        super(connection);
    }


    public static void createClient(String name, String birthday) {

    }


    public static void readClientById(int id) {

    }


    public static void readClientByNameAndBirthday(String name, String birthday) {

    }


    public static void readClientByName(String name) {

    }


    public static void readAllClients() {

    }


    public static void deleteClientByIdAndName(int id, String name) {

    }


    public static void deleteClientByNameAndBirthday(String name, String birthday) {

    }


    public static void updateClientById(int id) {

    }

}
