package seedu.duke;

/**
 * Represents a client.
 */
public class Client {
    private static final String CLIENT_LABEL = "  Client: ";
    private static final String CONTACT_NUMBER_LABEL = "  Contact Number: ";
    private static final String EMAIL_LABEL = "  Email: ";
    private static final String BUDGET_SGD_LABEL = "  Budget: SGD";
    private static final String PER_MONTH_LABEL = "/month";

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
        clientDetails.append(CLIENT_LABEL).append(clientName).append(System.lineSeparator());
        clientDetails.append(CONTACT_NUMBER_LABEL).append(clientContactNumber).append(System.lineSeparator());
        boolean hasEmail = (!clientEmail.isEmpty());
        if (hasEmail) {
            clientDetails.append(EMAIL_LABEL).append(clientEmail).append(System.lineSeparator());
        }
        clientDetails.append(BUDGET_SGD_LABEL).append(clientBudgetPerMonth).append(PER_MONTH_LABEL)
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
