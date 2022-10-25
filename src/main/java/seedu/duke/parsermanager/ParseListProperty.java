package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandListProperties;
import seedu.duke.command.CommandListPropertiesWithTags;
import seedu.duke.CommandStructure;

import seedu.duke.exception.IncorrectListFlagsException;

import static seedu.duke.Messages.MESSAGE_INCORRECT_PROPERTY_LIST_FLAG;

public class ParseListProperty extends Parser {

    private final String commandFlags;

    public ParseListProperty(String listPropertyCommandDescription) {
        this.commandFlags = listPropertyCommandDescription;
    }

    @Override
    public Command parseCommand() throws IncorrectListFlagsException {
        if (commandFlags.isEmpty()) {
            return new CommandListProperties();
        } else if (isValidTag(commandFlags)) {
            return new CommandListPropertiesWithTags(commandFlags);
        } else {
            throw new IncorrectListFlagsException(MESSAGE_INCORRECT_PROPERTY_LIST_FLAG);
        }
    }

    boolean isValidTag(String commandFlags) {
        switch (commandFlags) {
        case CommandStructure.NAME_FLAG: //deliberate fall through till SHORT_FLAG
        case CommandStructure.ADDRESS_FLAG:
        case CommandStructure.PRICE_FLAG:
        case CommandStructure.TYPE_FLAG:
        case CommandStructure.SHORT_FLAG:
            return true;//break not needed as we are returning
        default:
            return false;
        }
    }
}

