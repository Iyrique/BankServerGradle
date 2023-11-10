import java.io.*;
import java.util.*;

public class Generator {

    private static final List<String> FIRST_NAMES = loadNames("Generator/src/main/resources/names.txt");
    private static final List<String> LAST_NAMES = loadNames("Generator/src/main/resources/surnames.txt");
    private static Set<String> requisites = new HashSet<>();
    private static Set<String> cards = new HashSet<>();

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
        return random.nextInt(bound) + 1;
    }

    // Генерация случайного номера счета
    private static String generateReq() {
        Random random = new Random();
        long accountNumber = 1000000000L + random.nextInt(900000000);
        return String.valueOf(accountNumber);
    }

    // Генерация случайного ежемесячного платежа
    private static double generateMonthlyPayment() {
        Random random = new Random();
        double minPayment = 100.00;
        double maxPayment = 50000.00;
        double monthlyPayment = minPayment + (maxPayment - minPayment) * random.nextDouble();
        return Math.round(monthlyPayment * 100.0) / 100.0; // Округление до двух знаков после запятой
    }

    // Генерация случайного процента кредита
    private static double generateInterestRate() {
        Random random = new Random();
        double minRate = 5.00;
        double maxRate = 15.00;
        double interestRate = minRate + (maxRate - minRate) * random.nextDouble();
        return Math.round(interestRate * 100.0) / 100.0; // Округление до двух знаков после запятой
    }

    // Генерация случайной суммы кредита
    private static double generateLoanAmount() {
        Random random = new Random();
        double minAmount = 100000.00;
        double maxAmount = 4000000.00;
        double loanAmount = minAmount + (maxAmount - minAmount) * random.nextDouble();
        return Math.round(loanAmount * 100.0) / 100.0; // Округление до двух знаков после запятой
    }

    // Генерация случайной суммы
    private static double generateSum() {
        Random random = new Random();
        double minAmount = 100.00;
        double maxAmount = 40000000.00;
        double loanAmount = minAmount + (maxAmount - minAmount) * random.nextDouble();
        return Math.round(loanAmount * 100.0) / 100.0; // Округление до двух знаков после запятой
    }

    private static Credit generateCredit(int client_id, String name, String birthday) {
        String req = generateReq();
        while (true) {
            if (requisites.contains(req)) {
                req = generateReq();
            } else {
                requisites.add(req);
                break;
            }
        }
        return new Credit(client_id, name, birthday, generateRandomDate(2024, 2044), generateMonthlyPayment(),
                generateInterestRate(), generateLoanAmount(), req);
    }

    private static List<Credit> generateListCredit(int client_id, String name, String birthday) {
        List<Credit> list = new ArrayList<>();
        boolean b = randBool();
        if (b) {
            int num = randNum(2);
            for (int i = 0; i < num; i++) {
                list.add(generateCredit(client_id, name, birthday));
            }
        }
        return list;
    }

    private static Deposit generateDeposit(int client_id, String name, String birthday) {
        String req = generateReq();
        while (true) {
            if (requisites.contains(req)) {
                req = generateReq();
            } else {
                requisites.add(req);
                break;
            }
        }
        return new Deposit(client_id, name, birthday, generateSum(), generateInterestRate(), generateRandomDate(2024, 2040), req,
                randBool(), randBool());
    }

    private static List<Deposit> generateListDeposit(int client_id, String name, String birthday) {
        List<Deposit> list = new ArrayList<>();
        boolean b = randBool();
        if (b) {
            int num = randNum(3);
            for (int i = 0; i < num; i++) {
                list.add(generateDeposit(client_id, name, birthday));
            }
        }
        return list;
    }

    // Генерация случайного номера кредитной карты
    private static String generateCreditCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder("2"); // Первая цифра 2 для Мир

        for (int i = 2; i <= 16; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
            if (i % 4 == 0 && i < 15) {
                cardNumber.append(" "); // Разделители каждые 4 цифры
            }
        }

        return cardNumber.toString();
    }

    private static CardAccount generateCardAccount(int client_id, String name, String birthday) {
        String num = generateCreditCardNumber();
        while (true) {
            if (cards.contains(num)) {
                num = generateCreditCardNumber();
            } else {
                cards.add(num);
                break;
            }
        }
        Account account = new Account(client_id, generateSum());
        return new CardAccount(client_id, name, birthday, account, "codeword",
                new Card(num, generateRandomDate(2024,2034), name, "CVV", "code", 123, account));
    }

    public static List<Client> generateObjects() {
        List<Client> objects = new ArrayList<>();
        for (int i = 1; i <= 1000000; i++) {
            String name = generateRandomName();
            String birthday = generateRandomDate(1950, 2009);
            objects.add(new Client(i, name, birthday, generateListCredit(i, name, birthday),
                    generateListDeposit(i, name, birthday), generateCardAccount(i, name, birthday)));
        }
        return objects;
    }
}
