package service;

import model.Client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Bank implements Serializable {

    private final Map<Integer, Client> clients = new HashMap<>();

    public void addClient(Client client) {
        clients.put(client.getId(), client);
    }

    public void addAccountBalance(int clientId, double amount) {
        if (!clients.containsKey(clientId)) {
            System.out.println("Brak konta dla klienta o id: " + clientId);
        } else {
            Client client = clients.get(clientId);
            client.setAccountBalance(client.getAccountBalance() + amount);
        }
    }

    public void withdrawFromAccount(int clientId, double amount) {
        if (!clients.containsKey(clientId)) {
            System.out.println("Brak konta dla klienta o id: " + clientId);
        } else {
            Client client = clients.get(clientId);
            if (client.getAccountBalance() < amount) {
                System.out.println("Nie mozna wyplacic wiecej niz znajduje sie na koncie");
            } else {
                client.setAccountBalance(client.getAccountBalance() - amount);
                System.out.println("Wyplacono " + amount);
            }
        }
    }

    public void transferBetweenAccounts(int clientIdFrom, int clientIdTo, double amount) {
        if (!clients.containsKey(clientIdFrom) || !clients.containsKey(clientIdTo)) {
            System.out.println("Brak podanych kont w bazie");
        } else {
            Client clientFrom = clients.get(clientIdFrom);
            Client clientTo = clients.get(clientIdTo);
            if (clientFrom.getAccountBalance() < amount) {
                System.out.println("Brak srodkow do przelewu na koncie klienta o id: " + clientIdFrom);
            } else {
                clientFrom.setAccountBalance(clientFrom.getAccountBalance() - amount);
                clientTo.setAccountBalance(clientTo.getAccountBalance() + amount);
            }
        }
    }

    public void printAllClients() {
        clients.values().forEach(client -> {
            System.out.println("\nid: " + client.getId());
            System.out.println("imie: " + client.getName());
            System.out.println("nazwisko: " + client.getSurname());
            System.out.println("stan konta: " + client.getAccountBalance());
            System.out.println("oprocentowanie: " + client.getFullInterestRate());
        });
    }

    public void printClientById(int clientId) {
        if (!clients.containsKey(clientId)) {
            System.out.println("Brak konta dla klienta o id: " + clientId);
        } else {
            Client client = clients.get(clientId);
            System.out.println("id: " + client.getId());
            System.out.println("imie: " + client.getName());
            System.out.println("nazwisko: " + client.getSurname());
            System.out.println("stan konta: " + client.getAccountBalance());
            System.out.println("oprocentowanie" + client.getFullInterestRate());
        }
    }

    public void calculateInterestsForClient(int clientId) {
        if (!clients.containsKey(clientId)) {
            System.out.println("Brak konta dla klienta o id: " + clientId);
        } else {
            Client client = clients.get(clientId);
            double accountBalance = client.getAccountBalance();
            client.setAccountBalance(accountBalance + accountBalance * client.getFullInterestRate());
            System.out.println("Naliczono odsetki");
        }
    }

    public void removeClientById(int clientId) {
        if (!clients.containsKey(clientId)) {
            System.out.println("Brak konta dla klienta o id: " + clientId);
        } else {
            clients.remove(clientId);
            System.out.println("Usunieto klienta o id: " + clientId);
        }
    }

    public int nextAvailableClientId() {
        return clients.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
    }
}
