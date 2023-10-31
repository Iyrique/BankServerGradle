import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    private Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertObjects() throws IOException {
        String sql = "INSERT INTO clients (client_id, bank_id, client_name, birthday) VALUES (?, ?, ?, ?)";
        Deserialization<Client> deserialization = new Deserialization<>();
        String filePath = "clients.json";
        List<Client> objects = deserialization.deserializeJsonToObjects(filePath, Client.class);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Client client : objects) {
                statement.setInt(1, client.getId());
                statement.setInt(2, 1);
                statement.setString(3, client.getName());
                statement.setString(4, client.getBirthday());
                statement.executeUpdate();
            }
            System.out.println("Insert successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
