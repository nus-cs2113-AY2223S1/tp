package seedu.duke.command;

//@@author zoranabc201
import seedu.duke.ClientList;
import seedu.duke.CommandStructure;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

public class CommandListProperties extends Command {

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        for (int i = CommandStructure.START_INDEX; i < propertyList.getCurrentListSize(); i++) {
            ui.displayOneProperty(propertyList.getPropertyList().get(i), i + 1);
        }
        ui.displayNoOfProperties(propertyList.getCurrentListSize(),
                propertyList.getCurrentListSize() == CommandStructure.ONE_ITEM_IN_LIST);
        //newline at the end of command
        ui.printNewline();
    }
}
