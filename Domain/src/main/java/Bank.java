import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Client> clients;

    private int id;

    private String name;

    public Bank(List<Client> clients, String name) {
        this.clients = clients;
        this.name = name;
    }

    public Bank(String name) {
        this.clients = new ArrayList<>();
        this.name = name;
    }

    public Bank() {

    }

    public List<Client> getClients() {
        return clients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
