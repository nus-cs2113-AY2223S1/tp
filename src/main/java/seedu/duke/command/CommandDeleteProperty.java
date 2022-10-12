package seedu.duke.command;

import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

/**
 * Deletes a property from the property list.
 */
public class CommandDeleteProperty extends CommandDelete {
    private final int propertyIndex;

    public CommandDeleteProperty(int propertyIndex) {
        this.propertyIndex = propertyIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList,
                        ClientList clientList, PairingList pairingList) {

        Property deletedProperty = propertyList.deleteProperty(propertyIndex);

        ui.showPropertyDeletedConfirmationMessage(deletedProperty);

        pairingList.deletePairing(deletedProperty);
        //Update Storage
        storage.updateProperty(propertyList);


        for (String i : pairingList.getClientPropertyPairs().keySet()) {
            System.out.println("Key: " + i + "Value: " + pairingList.getClientPropertyPairs().get(i));
        }

        storage.updatePair(pairingList);

    }
}
