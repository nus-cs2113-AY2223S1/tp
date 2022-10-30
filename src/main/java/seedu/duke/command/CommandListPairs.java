package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.Messages;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

public class CommandListPairs extends Command {
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        pairingList.getClientPropertyPairs().entrySet().forEach(entry -> {
            ui.displayOneClientInPair(entry.getKey());
            ui.displayOnePropertyInPair(entry.getValue());
            System.out.println(Messages.LINE_BREAK);
        });
        ui.displayNoOfPairs(pairingList.getClientPropertyPairs().size());
    }
}

