//@@author OVReader

package seedu.duke;

import java.util.ArrayList;

/**
 * Stores the list of clients.
 */
public class ClientList {
    private static ArrayList<Client> clientList;
    private static int currentListSize;
    private static final int INITIAL_LIST_SIZE = 0;

    public ClientList() {
        clientList = new ArrayList<>();
        currentListSize = INITIAL_LIST_SIZE;
    }

    public int getCurrentListSize() {
        return currentListSize;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    /**
     * Adds a client to client list and updates client list size.
     *
     * @param clientName Name of client.
     * @param clientContactNumber Contact Number of client.
     * @param clientEmail Email of client.
     * @param clientBudgetPerMonth Budget Per Month for client.
     */
    public void addClient(String clientName, String clientContactNumber, String clientEmail,
                          String clientBudgetPerMonth) {
        clientList.add(new Client(clientName, clientContactNumber, clientEmail, clientBudgetPerMonth));
        currentListSize++;
    }
    //@@author

    //@@author FeliciaBeatrice
    /**
     * Deletes a client from the client list and updates client list size.
     *
     * @param clientIndex Index of client to be deleted.
     * @return Client deleted.
     */
    public Client deleteClient(int clientIndex) {
        Client deletedClient = clientList.get(clientIndex);
        clientList.remove(clientIndex);
        currentListSize--;
        return deletedClient;
    }
    //@@author
}
