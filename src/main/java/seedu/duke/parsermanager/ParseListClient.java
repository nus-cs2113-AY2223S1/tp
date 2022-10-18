package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandListClients;
import seedu.duke.command.CommandListClientsWithTags;
import seedu.duke.exception.IncorrectListFlagsException;
import seedu.duke.CommandStructure;

import static seedu.duke.Messages.MESSAGE_INCORRECT_CLIENT_LIST_FLAG;

public class ParseListClient extends Parser {

    private final String commandFlags;

    public ParseListClient(String listClientCommandDescription) {
        this.commandFlags = listClientCommandDescription;
    }

    @Override
    public Command parseCommand() throws IncorrectListFlagsException {
        if (commandFlags.isEmpty()) {
            return new CommandListClients();
        } else if (isValidTag(commandFlags)) {
            return new CommandListClientsWithTags(commandFlags);
        } else {
            throw new IncorrectListFlagsException(MESSAGE_INCORRECT_CLIENT_LIST_FLAG);
        }
    }

    boolean isValidTag(String commandFlags) {
        switch (commandFlags) {
        case CommandStructure.NAME_FLAG://deliberate fall through till BUDGET_FLAG
        case CommandStructure.CONTACT_NUMBER_FLAG:
        case CommandStructure.EMAIL_FLAG:
        case CommandStructure.BUDGET_FLAG:
            return true;//break not needed as we are returning
        default:
            return false;
        }
    }
}
