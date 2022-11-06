package seedu.duke.command;

//@@author zoranabc201
import seedu.duke.ClientList;
import seedu.duke.CommandStructure;
import seedu.duke.Messages;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

public class CommandListPairs extends Command {

    /**
     * Uses a lambda function to read in the pairing HashMap, and uses functions in Ui to print the corresponding
     * client and property objects.
     * @param ui User interface object that handles interactions between user and the app.
     * @param storage Storage object that is responsible for storing and loading the app data.
     * @param propertyList PropertyList object that handles all app interactions with the list of properties.
     * @param clientList ClientList object that handles all app interactions with the list of clients.
     * @param pairingList PairingList object that handles all app interactions with the clients' rental of property.
     */

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        pairingList.getClientPropertyPairs().entrySet().forEach(entry -> {
            ui.displayOneClientInPair(entry.getKey());
            ui.displayOnePropertyInPair(entry.getValue());
            System.out.println(Messages.LINE_BREAK);
        });
        ui.displayNoOfPairs(pairingList.getClientPropertyPairs().size(),
                pairingList.getClientPropertyPairs().size() == CommandStructure.ONE_ITEM_IN_LIST);
        //newline at the end of command
        ui.printNewline();
    }
}

