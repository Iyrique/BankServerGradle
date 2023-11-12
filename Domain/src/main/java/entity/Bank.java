package entity;

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
        this.clients = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Bank{" +
                "clients=" + clients +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
