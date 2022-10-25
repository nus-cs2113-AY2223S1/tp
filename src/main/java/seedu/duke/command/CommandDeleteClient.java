package seedu.duke.command;


import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

/**
 * Deletes a client from the client list.
 */
public class CommandDeleteClient extends CommandDelete {
    private final int clientIndex;

    public CommandDeleteClient(int clientIndex) {
        this.clientIndex = clientIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList,
                        ClientList clientList, PairingList pairingList) {
        Client deletedClient = clientList.deleteClient(clientIndex);
        ui.showClientDeletedConfirmationMessage(deletedClient);
        ui.showPairedPropertiesDeletedConfirmationMessage(deletedClient, pairingList);
        pairingList.deletePairing(deletedClient);

        //Update Storage
        storage.updateClient();
        storage.updatePair();
    }
}
