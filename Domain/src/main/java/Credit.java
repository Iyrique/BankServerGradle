import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Credit {

    @JsonProperty("id_cl")
    private final int clientId;
    @JsonProperty("clientName")
    private String clientName;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("period")
    private String period;
    @JsonProperty("monthlyPayment")
    private double monthlyPayment;
    @JsonProperty("percent")
    private double percent;
    @JsonProperty("sum")
    private double sum;
    @JsonProperty("requisites")
    private final String requisites;

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setMonthlyPayment(int monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getRequisites() {
        return requisites;
    }

    public double getPercent() {
        return percent;
    }

    public Credit(int clientId, String clientName, String birthday,
                  String period, double monthlyPayment, double percent, double sum, String requisites) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.birthday = birthday;
        this.period = period;
        this.monthlyPayment = monthlyPayment;
        this.percent = percent;
        this.sum = sum;
        this.requisites = requisites;
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

    public String getPeriod() {
        return period;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getSum() {
        return sum;
    }

    public void depositMoney(double sum) {

    }


}

