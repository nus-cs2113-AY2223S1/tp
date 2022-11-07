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
import seedu.duke.exception.pairunpair.pair.BudgetExceededException;
import seedu.duke.exception.pairunpair.pair.ClientAlreadyPairedException;
import seedu.duke.exception.pairunpair.pair.ExistingPairException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.FIRST_INDEX;
import static seedu.duke.CommandStructure.SECOND_INDEX;

/**
 * Represents a pair-type command.
 */
public class CommandPair extends CommandPairUnpair {

    private int clientIndex;
    private int propertyIndex;


    /**
     * Constructs CommandPair object.
     *
     * @param commandPairDetails Parsed client and property indexes from the user's input.
     */
    public CommandPair(ArrayList<Integer> commandPairDetails) {
        this.propertyIndex = commandPairDetails.get(FIRST_INDEX);
        this.clientIndex = commandPairDetails.get(SECOND_INDEX);
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


        if (pairingList.isAlreadyPaired(client, property)) {
            throw new ExistingPairException();
        }

        if (pairingList.isClientPairedWithProperty(client)) {
            throw new ClientAlreadyPairedException();
        }

        if (pairingList.hasPriceExceededBudget(client, property)) {
            throw new BudgetExceededException(client, property);
        }

        pairingList.addPairing(client, property);

        String clientStorageData = pairingList.convertToPairingData(client);
        String propertyStorageData = pairingList.convertToPairingData(property);
        storage.addToPairFile(clientStorageData, propertyStorageData);

        ui.showPairedConfirmationMessage(client, property);
    }
}
