package seedu.duke.command;

import seedu.duke.*;

import java.util.ArrayList;

/**
 * Deletes a client from the client list.
 */
public class CommandDeleteClient extends CommandDelete {
    private final int clientIndex;

    public CommandDeleteClient(int clientIndex) {
        this.clientIndex = clientIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList){
        Client deletedClient = clientList.deleteClient(clientIndex);
        ui.showClientDeletedConfirmationMessage(deletedClient);
        //Update Storage
    }
}
