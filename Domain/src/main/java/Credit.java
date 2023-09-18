import java.util.List;

public class Credit {

    private final int id;
    private final int clientId;
    private String clientName;
    private String birthday;
    private String period;
    private double monthlyPayment;
    private double sum;
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

    public Credit(int id, int clientId, String clientName, String birthday,
                  String period, double monthlyPayment, double sum, String requisites) {
        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.birthday = birthday;
        this.period = period;
        this.monthlyPayment = monthlyPayment;
        this.sum = sum;
        this.requisites = requisites;
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

