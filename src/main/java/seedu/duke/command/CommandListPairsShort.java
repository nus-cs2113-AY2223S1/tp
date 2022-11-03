package seedu.duke.command;

//@@author zoranabc201
import seedu.duke.ClientList;
import seedu.duke.CommandStructure;
import seedu.duke.Messages;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

public class CommandListPairsShort extends Command {

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        pairingList.getClientPropertyPairs().entrySet().forEach(entry -> {
            ui.displayOneClientInPairShort(entry.getKey());
            ui.displayOnePropertyInPairShort(entry.getValue());
            System.out.println(Messages.LINE_BREAK);
        });
        ui.displayNoOfPairs(pairingList.getClientPropertyPairs().size(),
                pairingList.getClientPropertyPairs().size() == CommandStructure.ONE_ITEM_IN_LIST);
        //newline at the end of command
        ui.printNewline();
    }
}
