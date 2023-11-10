package view;

import java.sql.SQLException;

public interface View {

    void displayMenu() throws SQLException;

    void create() throws SQLException;

    void read() throws SQLException;

    void update() throws SQLException;

    void delete() throws SQLException;

    void readAll() throws SQLException;
}
