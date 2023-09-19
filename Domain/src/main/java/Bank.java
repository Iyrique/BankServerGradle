import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Client> clients;

    public Bank(List<Client> clients) {
        this.clients = clients;
    }

    public Bank() {
        this.clients = new ArrayList<>();
    }
}
