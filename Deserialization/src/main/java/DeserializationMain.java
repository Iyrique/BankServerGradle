import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DeserializationMain  {

    public static void main(String[] args) throws IOException {
        Deserialization<Client> deserializer = new Deserialization<>();
        String filePath = "clients.json"; // Specify the path to your serialized JSON file
        try {
            List<Client> deserializedObjects = deserializer.deserializeJsonToObjects(filePath, Client.class);
            // Now you have the deserialized objects in the deserializedObjects list.
            for (Client obj : deserializedObjects) {
                System.out.println(obj.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
