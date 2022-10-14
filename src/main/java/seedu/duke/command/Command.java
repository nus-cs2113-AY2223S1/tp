package seedu.duke.command;


import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param ui User interface object that handles interactions between user and the app.
     * @param storage Storage object that is responsible for storing and loading the app data.
     * @param propertyList PropertyList object that handles all app interactions with the list clients.
     * @param pairingList PairingList object that handles all interactions with the list clients paired with properties.
     */
    public abstract void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                                 PairingList pairingList);
}
