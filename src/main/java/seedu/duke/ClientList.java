package seedu.duke;

import java.util.ArrayList;

public class ClientList {
    private static int currentListSize;
    private static ArrayList<Client> clientList;

    public ClientList() {
        clientList = new ArrayList<>();
        currentListSize = 0;
    }

    public int getCurrentListSize() {
        return currentListSize;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public void addClient(String clientName, String clientContactNumber, String clientEmail,
                          String clientBudgetPerMonth) {
        clientList.add(new Client(clientName, clientContactNumber, clientEmail, clientBudgetPerMonth));
        currentListSize++;
    }
}
