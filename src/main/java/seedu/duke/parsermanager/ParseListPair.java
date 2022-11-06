package seedu.duke.parsermanager;

import seedu.duke.CommandStructure;
import seedu.duke.command.Command;
import seedu.duke.command.CommandListPairs;
import seedu.duke.command.CommandListPairsShort;
import seedu.duke.exception.IncorrectListFlagsException;

import static seedu.duke.Messages.MESSAGE_INCORRECT_PAIR_LIST_FLAG;

//@@author zoranabc201
public class ParseListPair extends Parser {
    private final String commandTags;

    public ParseListPair(String listPropertyCommandDescription) {
        this.commandTags = listPropertyCommandDescription;
    }

    /**
     * Checks tags(if any) entered by the user are valid, and returns the corresponding Command type.
     * @return CommandListPairs object if no Tags are present. CommandListPairsShort object if short tag is present
     * @throws IncorrectListFlagsException if an invalid tag is present
     */

    @Override
    public Command parseCommand() throws IncorrectListFlagsException {
        if (commandTags.isEmpty()) {
            return new CommandListPairs();
        } else if (commandTags.equals(CommandStructure.SHORT_TAG)) {
            return new CommandListPairsShort();
        } else {
            throw new IncorrectListFlagsException(MESSAGE_INCORRECT_PAIR_LIST_FLAG);
        }
    }
}

