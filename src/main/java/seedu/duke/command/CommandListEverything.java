package seedu.duke.command;

//@@author zoranabc201
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.Messages;

public class CommandListEverything extends Command {

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
