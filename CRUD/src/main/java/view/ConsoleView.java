package view;

import connection.ConnectDB;
import crud.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class ConsoleView implements View{

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() throws SQLException {
        Connection connection = ConnectDB.connector();
        CRUD crud = new CRUD(connection);
        while (true) {

            break;
        }
        connection.close();
    }

    @Override
    public void create() throws SQLException {

    }

    @Override
    public void read() throws SQLException {

    }

    @Override
    public void update() throws SQLException {

    }

    @Override
    public void delete() throws SQLException {

    }

    @Override
    public void readAll() throws SQLException {

    }
}
