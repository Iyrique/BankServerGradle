import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Card {

    @JsonProperty("cardNum")
    private String cardNumber;
    @JsonProperty("cardPer")
    private String cardPeriod;
    @JsonIgnore
    private String personName;
    @JsonProperty("CVV")
    private String CVV;
    @JsonProperty("code")
    private String codeForCheckCVV;
    @JsonProperty("Pin")
    private int PIN;
    @JsonProperty("acc")
    private Account account;

    public Card(String cardNumber, String cardPeriod, String personName,
                String CVV, String codeForCheckCVV, int pin,  Account account) {
        this.cardNumber = cardNumber;
        this.cardPeriod = cardPeriod;
        this.personName = personName;
        this.CVV = CVV;
        this.PIN = pin;
        this.codeForCheckCVV = codeForCheckCVV;
        this.account = account;
    }

    public Card() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardPeriod() {
        return cardPeriod;
    }

    public String getPersonName() {
        return personName;
    }

    public String getCVV() {
        return CVV;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setCodeForCheckCVV(String codeForCheckCVV) {
        this.codeForCheckCVV = codeForCheckCVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public void setCardPeriod(String cardPeriod) {
        this.cardPeriod = cardPeriod;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCodeForCheckCVV() {
        return codeForCheckCVV;
    }

    public int getPIN() {
        return PIN;
    }

    public Account getAccount() {
        return account;
    }


    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public void topUpBalance(double sum) {

    }

    public void withdrawMoney(double sum) {

    }
}
