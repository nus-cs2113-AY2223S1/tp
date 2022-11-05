package seedu.duke.exception.storageexception;


public class InvalidPairingClientException extends StorageException {
    private String clientName;
    private String clientContact;
    private String clientEmail;
    private String clientBudget;
    private static final String NAME_LABEL = "Client Name: ";
    private static final String CONTACT_LABEL = "Client Contact: ";
    private static final String EMAIL_LABEL = "Client Email: ";
    private static final String BUDGET_LABEL = "Client Budget: ";
    private static final String ERROR_ENTRY_HEADER = "The following won't be added as it does not exist in the client "
            + "list:";
    private static final String NEXT_LINE = "\n";

    public InvalidPairingClientException(String name, String contact, String email, String budget) {
        clientName = name;
        clientContact = contact;
        clientEmail = email;
        clientBudget = budget;
    }

    @Override
    public String toString() {
        return ERROR_ENTRY_HEADER + NEXT_LINE
                + NAME_LABEL + clientName + NEXT_LINE
                + CONTACT_LABEL + clientContact + NEXT_LINE
                + EMAIL_LABEL + clientEmail + NEXT_LINE
                + BUDGET_LABEL + clientBudget + NEXT_LINE;
    }
}
