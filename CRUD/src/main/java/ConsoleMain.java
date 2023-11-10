import connection.ConnectDB;
import crud.CRUD;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;

public class ConsoleMain {

    public static void main (String[] args) throws SQLException {
        ConsoleView consoleView = new ConsoleView();
        consoleView.displayMenu();
    }
}
