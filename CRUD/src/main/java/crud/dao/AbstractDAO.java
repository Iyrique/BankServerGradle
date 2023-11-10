package crud.dao;

import java.sql.Connection;

public abstract class AbstractDAO {

    private Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
