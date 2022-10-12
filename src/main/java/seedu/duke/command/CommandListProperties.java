package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

public class CommandListProperties extends Command {
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        for (int i = 0; i < propertyList.getCurrentListSize(); i++) {
            ui.displayOneProperty(propertyList.getPropertyList().get(i));
        }
    }
}
