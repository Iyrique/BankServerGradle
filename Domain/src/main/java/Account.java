public class Account {

    private final int account;
    private double balance;

    public Account(int account) {
        this.account = account;
        this.balance = 0;
    }

    public int getAccount() {
        return account;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
