import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Deserialization<T> {

    public List<T> deserializeJsonToObjects(String filePath, Class<T> objectType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Read the JSON from the file
        String json = new String(Files.readAllBytes(Paths.get(filePath)));

        // Deserialize JSON from file into a list of objects
        List<T> objects = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, objectType));

        return objects;
    }

}
