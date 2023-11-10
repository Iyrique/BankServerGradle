package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    @JsonIgnore
    private int cardAccId;
    @JsonProperty("balance")
    private double balance;

    public Account(int account, double balance) {
        this.cardAccId = account;
        this.balance = balance;
    }

    public Account() {
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCardAccId(int cardAccId) {
        this.cardAccId = cardAccId;
    }

    public int getCardAccId() {
        return cardAccId;
    }
}
