//@@author OVReader

package seedu.duke.command.add;

import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Adds a client to the client list.
 */
public class CommandAddClient extends CommandAdd {
    private final String clientName;
    private final String clientContactNumber;
    private final String clientEmail;
    private final String clientBudgetPerMonth;

    private static final int CLIENT_NAME_INDEX = 0;
    private static final int CLIENT_CONTACT_NUMBER_INDEX = 1;
    private static final int CLIENT_EMAIL_INDEX = 2;
    private static final int CLIENT_BUDGET_INDEX = 3;

    private static final String COMMAND_ADD_CLIENT_LOGGING_LABEL = "CommandAddClient";
    private static final Logger logger = Logger.getLogger(COMMAND_ADD_CLIENT_LOGGING_LABEL);
    private static final String ADD_CLIENT_LOGGING_MESSAGE = "Adding new client now";

    /**
     * Constructs constructor for Command Add Client which stores client's name, contact number, email and budget/month.
     *
     * @param clientDetails Contains client's name, contact number, email and budget/month.
     */
    public CommandAddClient(ArrayList<String> clientDetails) {
        for (String clientDetail : clientDetails) {
            assert clientDetail != null;
        }

        clientName           = clientDetails.get(CLIENT_NAME_INDEX);
        clientContactNumber  = clientDetails.get(CLIENT_CONTACT_NUMBER_INDEX);
        clientEmail          = clientDetails.get(CLIENT_EMAIL_INDEX);
        clientBudgetPerMonth = clientDetails.get(CLIENT_BUDGET_INDEX);
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        logger.log(Level.FINEST, ADD_CLIENT_LOGGING_MESSAGE);
        clientList.addClient(clientName, clientContactNumber, clientEmail, clientBudgetPerMonth);
        ui.showClientAddedConfirmationMessage(clientList);

        // Updates Storage
        storage.addToClientFile(clientName, clientContactNumber, clientEmail, clientBudgetPerMonth);
    }
}
