import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Serialization<T> {

    public void serializeObjectsToJson(List<T> objects, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Serialize the objects to JSON
        String json = objectMapper.writeValueAsString(objects);

        // Write the JSON to a file
        try {
            File file = new File(filePath);
            objectMapper.writeValue(file, objects);
            System.out.println("Objects serialized and saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
