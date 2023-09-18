import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private static final List<String> FIRST_NAMES = loadNames("Generator/src/main/resources/names.txt");
    private static final List<String> LAST_NAMES = loadNames("Generator/src/main/resources//surnames.txt");

    private static List<String> loadNames(String fileName) {
        List<String> names = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "Windows-1251");
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                names.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    private static String generateRandomName() {
        Random random = new Random();
        String firstName = FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size()));
        String lastName = LAST_NAMES.get(random.nextInt(LAST_NAMES.size()));
        return firstName + " " + lastName;
    }

    private static List<Client> generateObjects() {
        List<Client> objects = new ArrayList<>();
        for (int i = 1; i <= 1000000; i++) {
            objects.add(new Client(i, generateRandomName(), "date", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        }
        return objects;
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            String randomName = generateRandomName();
//            System.out.println(randomName);
//        }
//    }
}
