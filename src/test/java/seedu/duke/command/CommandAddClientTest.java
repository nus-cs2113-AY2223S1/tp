package seedu.duke.command;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandAddClientTest {
    private static final String SPACE = " ";
    private static final String DOUBLE_SPACE = SPACE + SPACE;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String CLIENT_NAME_LABEL = "Client:";
    private static final String CLIENT_CONTACT_NUMBER_LABEL = "Contact Number:";
    private static final String CLIENT_EMAIL_LABEL = "Email:";
    private static final String CLIENT_BUDGET_LABEL = "Budget: SGD";
    private static final String CLIENT_PER_MONTH_LABEL = "/month";

    //Test Case 1 (Client with Email)
    private static final String CLIENT_ONE_NAME = "Joseph Joestar";
    private static final String CLIENT_ONE_CONTACT_NUMBER = "83625471";
    private static final String CLIENT_ONE_EMAIL = "jojofirst@jmail.com";
    private static final String CLIENT_ONE_BUDGET = "9000";
    private static final ArrayList<String> CLIENT_ONE_DETAILS = new ArrayList<>(
            List.of(CLIENT_ONE_NAME, CLIENT_ONE_CONTACT_NUMBER, CLIENT_ONE_EMAIL, CLIENT_ONE_BUDGET)
    );
    private static final String CLIENT_ONE_SUMMARY = CLIENT_NAME_LABEL + SPACE + CLIENT_ONE_NAME
            + LINE_SEPARATOR + DOUBLE_SPACE + CLIENT_CONTACT_NUMBER_LABEL + SPACE + CLIENT_ONE_CONTACT_NUMBER
            + LINE_SEPARATOR + DOUBLE_SPACE + CLIENT_EMAIL_LABEL + SPACE + CLIENT_ONE_EMAIL
            + LINE_SEPARATOR + DOUBLE_SPACE + CLIENT_BUDGET_LABEL + CLIENT_ONE_BUDGET + CLIENT_PER_MONTH_LABEL;

    //Test Case 2 (Client without Email)
    private static final String CLIENT_TWO_NAME = "Kujou Jotaro";
    private static final String CLIENT_TWO_CONTACT_NUMBER = "65329466";
    private static final String CLIENT_TWO_EMAIL = "";
    private static final String CLIENT_TWO_BUDGET = "10000";
    private static final ArrayList<String> CLIENT_TWO_DETAILS = new ArrayList<>(
            List.of(CLIENT_TWO_NAME, CLIENT_TWO_CONTACT_NUMBER, CLIENT_TWO_EMAIL, CLIENT_TWO_BUDGET)
    );
    private static final String CLIENT_TWO_SUMMARY = CLIENT_NAME_LABEL + SPACE + CLIENT_TWO_NAME
            + LINE_SEPARATOR + DOUBLE_SPACE + CLIENT_CONTACT_NUMBER_LABEL + SPACE + CLIENT_TWO_CONTACT_NUMBER
            + LINE_SEPARATOR + DOUBLE_SPACE + CLIENT_BUDGET_LABEL + CLIENT_TWO_BUDGET + CLIENT_PER_MONTH_LABEL;

    //Initialization for Testing
    public Ui ui = new Ui();
    public PropertyList propertyList = new PropertyList();
    public ClientList clientList = new ClientList();
    public PairingList pairingList = new PairingList();
    public Storage storage = new Storage(clientList, propertyList, pairingList);

    @Test
    public void execute() {
        int clientListSizeByCounting = clientList.getCurrentListSize();

        //Testing Case 1 (With Email)
        new CommandAddClient(CLIENT_ONE_DETAILS).execute(ui, storage, propertyList, clientList, pairingList);
        assertEquals(clientList.getCurrentListSize(), ++clientListSizeByCounting);
        assertEquals(CLIENT_ONE_NAME, clientList.getClientList().get(clientList.getCurrentListSize() - 1)
                .getClientName());
        assertEquals(CLIENT_ONE_CONTACT_NUMBER, clientList.getClientList().get(clientList.getCurrentListSize() - 1)
                .getClientContactNumber());
        assertEquals(CLIENT_ONE_EMAIL, clientList.getClientList().get(clientList.getCurrentListSize() - 1)
                .getClientEmail());
        assertEquals(CLIENT_ONE_BUDGET, clientList.getClientList().get(clientList.getCurrentListSize() - 1)
                .getClientBudgetPerMonth());
        assertEquals(CLIENT_ONE_SUMMARY, clientList.getClientList().get(clientList.getCurrentListSize() - 1)
                .toString());

        //Testing Case 2 (Without Email)
        new CommandAddClient(CLIENT_TWO_DETAILS).execute(ui, storage, propertyList, clientList, pairingList);
        assertEquals(clientList.getCurrentListSize(), ++clientListSizeByCounting);
        assertEquals(CLIENT_TWO_NAME, clientList.getClientList().get(clientList.getCurrentListSize() - 1)
                .getClientName());
        assertEquals(CLIENT_TWO_CONTACT_NUMBER, clientList.getClientList().get(clientList.getCurrentListSize() - 1)
                .getClientContactNumber());
        assertEquals(CLIENT_TWO_EMAIL, clientList.getClientList().get(clientList.getCurrentListSize() - 1)
                .getClientEmail());
        assertEquals(CLIENT_TWO_BUDGET, clientList.getClientList().get(clientList.getCurrentListSize() - 1)
                .getClientBudgetPerMonth());
        assertEquals(CLIENT_TWO_SUMMARY, clientList.getClientList().get(clientList.getCurrentListSize() - 1)
                .toString());
    }
}