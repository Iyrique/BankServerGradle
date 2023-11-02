import java.io.*;
import java.util.*;

public class Generator {

    private static final List<String> FIRST_NAMES = loadNames("Generator/src/main/resources/names.txt");
    private static final List<String> LAST_NAMES = loadNames("Generator/src/main/resources/surnames.txt");

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

    private static String generateRandomDate(int startYear, int endYear) {
        Random random = new Random();

        // Generate a random year between startYear and endYear
        int year = startYear + random.nextInt(endYear - startYear + 1);

        // Generate a random month (1-12)
        int month = random.nextInt(12) + 1;

        // Generate a random day within the selected month
        int maxDay = getMaxDayInMonth(year, month);
        int day = random.nextInt(maxDay) + 1;

        // Create a Calendar instance and set the random year, month, and day
        Calendar calendar = new GregorianCalendar(year, month - 1, day);

        // Format the date as "DD.MM.YYYY"
        String formattedDate = String.format("%02d.%02d.%04d", day, month, year);

        return formattedDate;
    }

    private static int getMaxDayInMonth(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private static boolean randBool() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private static int randNum(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

    private static Credit generateCredit(int id, String name, String birthday,
                                         String period, double payment, double percent) {

    }

    public static List<Client> generateObjects() {
        List<Client> objects = new ArrayList<>();
        for (int i = 1; i <= 1000000; i++) {
            String name = generateRandomName();
            String birthday = generateRandomDate(1950, 2009);
            objects.add(new Client(i, name, birthday, new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>()));
        }
        return objects;
    }
}
