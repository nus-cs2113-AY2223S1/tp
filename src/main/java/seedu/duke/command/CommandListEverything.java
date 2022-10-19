package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.Messages;

public class CommandListEverything extends Command {
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        for (int i = 0; i < propertyList.getCurrentListSize(); i++) {
            ui.displayOneProperty(propertyList.getPropertyList().get(i), i + 1);
        }
        System.out.println("There are " + propertyList.getCurrentListSize()
                + " properties in this list");
        System.out.println(Messages.LINE_BREAK);
        for (int i = 0; i < clientList.getCurrentListSize(); i++) {
            ui.displayOneClient(clientList.getClientList().get(i), i + 1);
        }
        System.out.println("There are " + clientList.getCurrentListSize()
                + " clients in this list");
    }
}
