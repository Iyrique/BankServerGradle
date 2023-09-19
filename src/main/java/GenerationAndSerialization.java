import java.io.IOException;
import java.util.List;

public class GenerationAndSerialization {

    public static void main (String args[]) throws IOException {
        List<Client> clients = Generator.generateObjects();

        String filePath ="JSON/clients.json";

        Serialization<Client> serialization = new Serialization<>();
        serialization.serializeObjectsToJson(clients, filePath);
    }

}
