package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CardAccount {

    @JsonIgnore
    private int clientId;
    @JsonIgnore
    private String clientName;
    @JsonIgnore
    private String birthday;
    @JsonProperty("account")
    private Account accountNumber;
    @JsonProperty("code_word")
    private String codeWord;
    @JsonProperty("card")
    private Card cards;

    public CardAccount() {
    }

    public CardAccount(int clientId, String clientName, String birthday,
                       Account accountNumber, String codeWord, Card card) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.birthday = birthday;
        this.accountNumber = accountNumber;
        this.codeWord = codeWord;
        this.cards = card;
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

    public Card getCard() {
        return cards;
    }

    public void setCards(String num, String per, String clientName, String CVV, String code, int pin, Account cardAccId) {
        cards = new Card(num, per, clientName, CVV, code, pin, cardAccId);
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

    public void closeCard(int numberCard) {

    }

    public void openCard(int cardNumber, String period) {

    }

}
