import java.util.ArrayList;
import java.util.List;

public class CardAccount {

    private final int id;
    private final int clientId;
    private String clientName;
    private String birthday;
    private final Account accountNumber;
    private String codeWord;
    private List<Card> cards;

    public CardAccount(int id, int clientId, String clientName, String birthday,
                       Account accountNumber, String codeWord) {
        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.birthday = birthday;
        this.accountNumber = accountNumber;
        this.codeWord = codeWord;
        this.cards = new ArrayList<>();
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCodeWord(String codeWord) {
        this.codeWord = codeWord;
    }

    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getBirthday() {
        return birthday;
    }

    public Account getAccountNumber() {
        return accountNumber;
    }

    public String getCodeWord() {
        return codeWord;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void closeCard(int numberCard) {

    }

    public void openCard(int cardNumber, String period) {

    }

}
