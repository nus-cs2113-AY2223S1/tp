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
        clientDetails.append("  Client: ").append(clientName).append(System.lineSeparator());
        clientDetails.append("  Contact Number: ").append(clientContactNumber).append(System.lineSeparator());
        boolean hasEmail = (!clientEmail.isEmpty());
        if (hasEmail) {
            clientDetails.append("  Email: ").append(clientEmail).append(System.lineSeparator());
        }
        clientDetails.append("  Budget: SGD").append(clientBudgetPerMonth).append("/month")
                .append(System.lineSeparator());
        return clientDetails.toString().trim();
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientContactNumber() {
        return clientContactNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public String getClientEmailForList() {
        if (clientEmail.isEmpty()) {
            return "This client has not provided an e-mail";
        }
        return clientEmail;
    }

    public String getClientBudgetPerMonth() {
        return clientBudgetPerMonth;
    }
}
