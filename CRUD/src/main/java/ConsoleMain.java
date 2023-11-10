import crud.InterfaceCRUD;

import java.sql.Connection;
import java.sql.SQLException;


public class ConsoleMain {

    public static void main (String[] args) throws SQLException {
        Connection connection = ConnectDB.connector();
        while (true) {
            break;
        }
        connection.close();
    }
}
