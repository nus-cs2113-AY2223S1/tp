//@@author FeliciaBeatrice

package seedu.duke.command.check;

import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandCheckClient extends CommandCheck {
    private int clientIndex;

    /**
     * Constructs an instance of CommandCheckClient.
     *
     * @param commandCheckClientDetails Parsed client index from the user's input.
     */
    public CommandCheckClient(ArrayList<Integer> commandCheckClientDetails) {
        this.clientIndex = commandCheckClientDetails.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        Client client = clientList.getClientList().get(clientIndex);
        HashMap<Client, Property> clientPropertyPairs = pairingList.getClientPropertyPairs();
        boolean isPaired = checkForPairedProperties(clientPropertyPairs, client);
        if (isPaired) {
            Property rentedProperty = clientPropertyPairs.get(client);
            ui.showCheckClient(client, rentedProperty);
        } else {
            ui.showCheckClientNoPair(client);
        }
    }

    protected boolean checkForPairedProperties(HashMap<Client, Property> clientPropertyPairs, Client client) {
        return clientPropertyPairs.containsKey(client);
    }
}
