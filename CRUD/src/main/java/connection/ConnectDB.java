package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public static Connection connector() throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/bank.db";
        String username = "postgres";
        String password = "postgres";
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Connect!");
        return connection;
    }
}
