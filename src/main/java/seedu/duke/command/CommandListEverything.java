package seedu.duke.command;

//@@author zoranabc201
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.Messages;

public class CommandListEverything extends Command {

    /**
     * Prints everything by instantiating CommandListClient, CommandListProperties, and CommandListPairs objects and
     * executing.
     * @param ui User interface object that handles interactions between user and the app.
     * @param storage Storage object that is responsible for storing and loading the app data.
     * @param propertyList PropertyList object that handles all app interactions with the list of properties.
     * @param clientList ClientList object that handles all app interactions with the list of clients.
     * @param pairingList PairingList object that handles all app interactions with the clients' rental of property.
     */

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        System.out.println(Messages.CLIENTS);
        new CommandListClients().execute(ui, storage, propertyList, clientList, pairingList);
        System.out.println(Messages.LINE_BREAK);
        System.out.println(Messages.PROPERTIES);
        new CommandListProperties().execute(ui, storage, propertyList, clientList, pairingList);
        System.out.println(Messages.LINE_BREAK);
        System.out.println(Messages.PAIRS);
        new CommandListPairs().execute(ui, storage, propertyList, clientList, pairingList);
    }
}
