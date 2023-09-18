import java.util.List;

public class Client {

    private int id;
    private String name;
    private String birthday;
    private List<Credit> credits; //Кредитные договоры
    private List<Deposit> deposits; //Депозитные договоры
    private List<CardAccount> cardAccounts; //Договоры, по карточным счетам

    public Client(int id, String name, String birthday, List<Credit> credits,
                  List<Deposit> deposits, List<CardAccount> cardAccounts) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.credits = credits;
        this.deposits = deposits;
        this.cardAccounts = cardAccounts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public List<CardAccount> getCardAccount() {
        return cardAccounts;
    }

    public void addCredit(Credit credit) {
        credits.add(credit);
    }

    public void addDeposit(Deposit deposit) {
        deposits.add(deposit);
    }

    public void addCardAccount(CardAccount cardAccount) {
        cardAccounts.add(cardAccount);
    }

    public double getCardBalance(int cardNumber) {
        return 0;
    }

    public void closeDeposit(int idDeposit) {

    }

    public void closeCard(int idCardAccount, int cardNumber){

    }

}
