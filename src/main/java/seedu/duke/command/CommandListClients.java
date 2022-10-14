package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.PairingList2;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

public class CommandListClients extends Command {
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList2 pairingList2) {
        for (int i = 0; i < clientList.getCurrentListSize(); i++) {
            ui.displayOneClient(clientList.getClientList().get(i), i + 1);
        }
    }
}

