package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Credit {

    @JsonIgnore
    private int creditId;
    @JsonIgnore
    private int clientId;
    @JsonIgnore
    private String clientName;
    @JsonIgnore
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
    private String requisites;

    public Credit() {
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

    public int getCreditId() {
        return creditId;
    }

    public String getRequisites() {
        return requisites;
    }

    public double getPercent() {
        return percent;
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

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void depositMoney(double sum) {

    }


}

