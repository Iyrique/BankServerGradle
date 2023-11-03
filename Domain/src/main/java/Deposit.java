import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Deposit {

    @JsonIgnore
    private int clientId;
    @JsonIgnore
    private String clientName;
    @JsonIgnore
    private String birthday;
    @JsonProperty("sum")
    private double sum;
    @JsonProperty("percent")
    private double percent;
    @JsonProperty("period")
    private String period;
    @JsonProperty("requisites")
    private String requisites;
    @JsonProperty("TopUp")
    private boolean abilityTopUp;
    @JsonProperty("Withdraw")
    private boolean abilityWithdraw;

    public Deposit(int clientId, String clientName, String birthday,
                   double sum, double percent, String period,
                   String requisites, boolean abilityTopUp,
                   boolean abilityWithdraw) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.birthday = birthday;
        this.sum = sum;
        this.percent = percent;
        this.period = period;
        this.requisites = requisites;
        this.abilityTopUp = abilityTopUp;
        this.abilityWithdraw = abilityWithdraw;
    }

    public Deposit() {
    }

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

    public void setAbilityWithdraw(boolean abilityWithdraw) {
        this.abilityWithdraw = abilityWithdraw;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    public void setAbilityTopUp(boolean abilityTopUp) {
        this.abilityTopUp = abilityTopUp;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public double getSum() {
        return sum;
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
