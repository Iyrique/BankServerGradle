import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("credits")
    private List<Credit> credits; //Кредитные договоры
    @JsonProperty("deposits")
    private List<Deposit> deposits; //Депозитные договоры
    @JsonProperty("cardAccount")
    private CardAccount cardAccounts; //Договоры, по карточным счетам

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", credits=" + credits +
                ", deposits=" + deposits +
                ", cardAccounts=" + cardAccounts +
                '}';
    }

    public Client() {
    }

    public Client(int id, String name, String birthday, List<Credit> credits,
                  List<Deposit> deposits, CardAccount cardAccounts) {
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

    public void setCardAccounts(CardAccount cardAccounts) {
        this.cardAccounts = cardAccounts;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public CardAccount getCardAccounts() {
        return cardAccounts;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void addCredit(Credit credit) {
        credits.add(credit);
    }

    public void addDeposit(Deposit deposit) {
        deposits.add(deposit);
    }

    public double getCardBalance(int cardNumber) {
        return 0;
    }

    public void closeDeposit(int idDeposit) {

    }

    public void closeCard(int idCardAccount, int cardNumber){

    }

}
