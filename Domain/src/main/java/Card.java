public class Card {

    private final String cardNumber;
    private final String cardPeriod;
    private final String personName;
    private final String CVV;
    private final String codeForCheckCVV;
    private int PIN;
    private final Account account;

    public Card(String cardNumber, String cardPeriod, String personName,
                String CVV, String codeForCheckCVV, Account account) {
        this.cardNumber = cardNumber;
        this.cardPeriod = cardPeriod;
        this.personName = personName;
        this.CVV = CVV;
        this.codeForCheckCVV = codeForCheckCVV;
        this.account = account;
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

    public String getCodeForCheckCVV() {
        return codeForCheckCVV;
    }

    public int getPIN() {
        return PIN;
    }

    public Account getAccount() {
        return account;
    }

    public double getBalance() {
        return 0;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public void topUpBalance(double sum) {

    }

    public void withdrawMoney(double sum) {

    }
}
