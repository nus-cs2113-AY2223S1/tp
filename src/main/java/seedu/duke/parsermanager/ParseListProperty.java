package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandListProperties;
import seedu.duke.command.CommandListPropertiesWithTags;
import seedu.duke.CommandStructure;

import seedu.duke.exception.IncorrectListFlagsException;

import static seedu.duke.Messages.MESSAGE_INCORRECT_PROPERTY_LIST_FLAG;

//@@author zoranabc201

public class ParseListProperty extends Parser {

    private final String commandTags;

    public ParseListProperty(String listPropertyCommandDescription) {
        this.commandTags = listPropertyCommandDescription;
    }

    @Override
    public Command parseCommand() throws IncorrectListFlagsException {
        if (commandTags.isEmpty()) {
            return new CommandListProperties();
        } else if (isValidTag(commandTags)) {
            return new CommandListPropertiesWithTags(commandTags);
        } else {
            throw new IncorrectListFlagsException(MESSAGE_INCORRECT_PROPERTY_LIST_FLAG);
        }
    }

    /**
     * Checks if the tags entered by the user are valid according to the UG.
     * @param commandTags Stores any tags entered by the user
     * @return true if the tags are valid. False otherwise
     */

    boolean isValidTag(String commandTags) {
        switch (commandTags) {
        case CommandStructure.NAME_TAG:
            //deliberate fall through till SHORT_TAG
        case CommandStructure.ADDRESS_TAG:
        case CommandStructure.PRICE_TAG:
        case CommandStructure.TYPE_TAG:
        case CommandStructure.SHORT_TAG:
            return true;
            //break not needed as we are returning
        default:
            return false;
        }
    }
}

