import view.ConsoleView;

import java.sql.SQLException;

public class ConsoleMain {

    public static void main (String[] args) throws SQLException {
        ConsoleView consoleView = new ConsoleView();
        consoleView.displayMenu();
    }
}
