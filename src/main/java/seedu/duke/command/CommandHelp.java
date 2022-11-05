package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.exception.DukeException;

import static seedu.duke.Messages.HELP_COMMAND_PRINT_LIST;

public class CommandHelp extends Command {
    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) throws DukeException {
        ui.showToUser(HELP_COMMAND_PRINT_LIST);
    }
}
