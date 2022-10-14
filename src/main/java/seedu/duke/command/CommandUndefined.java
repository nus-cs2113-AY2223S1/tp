package seedu.duke.command;


import seedu.duke.ClientList;
import seedu.duke.PairingList2;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

/**
 * Represents a command that the app does not recognise.
 */
public class CommandUndefined extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList2 pairingList) {
        ui.showCommandUndefinedMessage();
    }
}
