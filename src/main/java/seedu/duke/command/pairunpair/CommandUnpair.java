package seedu.duke.command.pairunpair;

//@@author ngdeqi
import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.exception.pairunpair.CommandPairUnpairException;
import seedu.duke.exception.pairunpair.unpair.NoExistingPairException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.FIRST_INDEX;
import static seedu.duke.CommandStructure.SECOND_INDEX;

/**
 * Represents an unpair-type command.
 */
public class CommandUnpair extends CommandPairUnpair {

    private int clientIndex;
    private int propertyIndex;

    /**
     * Constructs an instance of CommandCheckProperty.
     *
     * @param commandUnpairDetails Parsed client and property indexes from the user's input.
     */
    public CommandUnpair(ArrayList<Integer> commandUnpairDetails) {
        this.propertyIndex = commandUnpairDetails.get(FIRST_INDEX);
        this.clientIndex = commandUnpairDetails.get(SECOND_INDEX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) throws CommandPairUnpairException {
        super.checkForClientListIndexOutOfBounds(clientIndex, clientList);
        super.checkForPropertyListIndexOutOfBounds(propertyIndex, propertyList);

        Client client = clientList.getClientList().get(clientIndex);
        Property property = propertyList.getPropertyList().get(propertyIndex);

        if (!pairingList.isAlreadyPaired(client, property)) {
            throw new NoExistingPairException();
        }

        pairingList.deletePairing(client, property);

        storage.updatePair();
        ui.showUnpairedConfirmationMessage(client, property);

    }
}
