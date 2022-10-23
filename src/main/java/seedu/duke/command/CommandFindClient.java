package seedu.duke.command;

import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import static seedu.duke.Messages.MESSAGE_NO_CLIENT_MATCHES;



public class CommandFindClient extends Command {
    private final String queryText;

    public CommandFindClient(String queryText) {
        this.queryText = queryText;
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        int clientListSize = clientList.getCurrentListSize();
        int numOfMatches = 0;


        for (int i = 0; i < clientListSize; i += 1) {
            Client currentClient = clientList.getClientList().get(i);

            if (hasQueryText(queryText, currentClient)) {
                int clientIndex = i + 1;
                ui.displayOneClient(currentClient, clientIndex);
                numOfMatches += 1;
            }
        }

        boolean hasNoMatches = numOfMatches == 0;

        // Display to user that there is no match
        if (hasNoMatches) {
            ui.showToUser(MESSAGE_NO_CLIENT_MATCHES);
        }
    }

    /**
     * Determines if the client contains the user-inputted query text.
     *
     * @param queryText The input text the user intend to query.
     * @param currentClient The current Client object that will be checked.
     * @return true if client contains the queried text and false if it does not.
     */
    public boolean hasQueryText(String queryText, Client currentClient) {
        // Change the string to lower case to ignore case when searching
        String clientName = currentClient.getClientName().toLowerCase();
        String clientContactNumber = currentClient.getClientContactNumber().toLowerCase();
        String clientEmail = currentClient.getClientEmail().toLowerCase();
        String clientBudget = currentClient.getClientBudgetPerMonth().toLowerCase();
        String queryTextLowerCase = queryText.toLowerCase();

        return clientName.contains(queryTextLowerCase) || clientContactNumber.contains(queryTextLowerCase)
                || clientEmail.contains(queryTextLowerCase) || clientBudget.contains(queryTextLowerCase);
    }
}
