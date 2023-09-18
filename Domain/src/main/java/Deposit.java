import java.util.List;

public class Deposit {
    private final int id;
    private final int clientId;
    private String clientName;
    private String birthday;
    private final double minSum;
    private double sum;
    private double percent;
    private String period;
    private final String requisites;
    private final boolean abilityTopUp;
    private final boolean abilityWithdraw;

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }

    public Deposit(int id, int clientId, String clientName, String birthday,
                   double minSum, double sum, double percent, String period,
                   String requisites, boolean abilityTopUp,
                   boolean abilityWithdraw) {
        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.birthday = birthday;
        this.minSum = minSum;
        this.sum = sum;
        this.percent = percent;
        this.period = period;
        this.requisites = requisites;
        this.abilityTopUp = abilityTopUp;
        this.abilityWithdraw = abilityWithdraw;
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

    public double getMinSum() {
        return minSum;
    }

    public double getPercent() {
        return percent;
    }

    public String getPeriod() {
        return period;
    }

    public String getRequisites() {
        return requisites;
    }

    public boolean isAbilityTopUp() {
        return abilityTopUp;
    }

    public boolean isAbilityWithdraw() {
        return abilityWithdraw;
    }

    public void withdrawMoney(double sum) {

    }

    public void topUpMoney(double sum) {

    }

}
