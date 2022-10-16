package seedu.duke.command;


import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.ArrayList;

/**
 * Represents an unpair-type command.
 */
public class CommandUnpair extends Command {

    private int clientIndex;
    private int propertyIndex;

    /**
     * Constructs an instance of CommandCheckProperty.
     * @param commandUnpairDetails Parsed client and property indexes from the user's input.
     */
    public CommandUnpair(ArrayList<Integer> commandUnpairDetails) {
        this.propertyIndex = commandUnpairDetails.get(0);
        this.clientIndex = commandUnpairDetails.get(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        Client client = clientList.getClientList().get(clientIndex);
        Property property = propertyList.getPropertyList().get(propertyIndex);

        pairingList.deletePairing(client, property);



        storage.updatePair(pairingList);
        ui.showUnpairedConfirmationMessage(client, property);

    }
}
