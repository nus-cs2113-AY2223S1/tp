package seedu.duke.command;


import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList2;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.ArrayList;

/**
 * Represents a pair-type command.
 */
public class CommandPair extends Command {

    private int clientIndex;
    private int propertyIndex;


    /**
     * Constructs CommandPair object.
     * @param commandPairDetails Parsed client and property indexes from the user's input.
     */
    public CommandPair(ArrayList<Integer> commandPairDetails) {
        this.clientIndex = commandPairDetails.get(0);
        this.propertyIndex = commandPairDetails.get(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList2 pairingList2) {
        Client client = clientList.getClientList().get(clientIndex);
        Property property = propertyList.getPropertyList().get(propertyIndex);

        String clientFormat = pairingList2.convertToPairingData(client);
        String propertyFormat = pairingList2.convertToPairingData(property);

        pairingList2.addPairing(client, property);
        storage.addToPairFile(clientFormat, propertyFormat);

        ui.showPairedConfirmationMessage(client, property);
    }
}
