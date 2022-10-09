package seedu.duke;

/**
 * Represents a client.
 */
public class Client {
    private String clientName;
    private String clientContactNumber;
    private String clientEmail;
    private String clientBudgetPerMonth;

    public Client(String clientName, String clientContactNumber, String clientEmail, String clientBudgetPerMonth) {
        this.clientName = clientName;
        this.clientContactNumber = clientContactNumber;
        this.clientEmail = clientEmail;
        this.clientBudgetPerMonth = clientBudgetPerMonth;
    }

    @Override
    public String toString() {
        StringBuilder clientDetails = new StringBuilder();
        clientDetails.append("  Client: " + clientName).append(System.lineSeparator());
        clientDetails.append("  Contact Number: " + clientContactNumber).append(System.lineSeparator());
        boolean hasEmail = (!clientEmail.isEmpty());
        if (hasEmail) {
            clientDetails.append("  Email: " + clientEmail).append(System.lineSeparator());
        }
        clientDetails.append("  Budget: SGD" + clientBudgetPerMonth + "/month").append(System.lineSeparator());
        return clientDetails.toString().trim();
    }

}
