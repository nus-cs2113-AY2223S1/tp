package seedu.duke.parsermanager;

import seedu.duke.CommandStructure;
import seedu.duke.command.Command;
import seedu.duke.command.CommandListPairs;
import seedu.duke.command.CommandListPairsShort;
import seedu.duke.exception.IncorrectListFlagsException;

import static seedu.duke.Messages.MESSAGE_INCORRECT_PAIR_LIST_FLAG;

public class ParseListPair extends Parser {
    private final String commandFlags;

    public ParseListPair(String listPropertyCommandDescription) {
        this.commandFlags = listPropertyCommandDescription;
    }

    @Override
    public Command parseCommand() throws IncorrectListFlagsException {
        if (commandFlags.isEmpty()) {
            return new CommandListPairs();
        } else if (commandFlags.equals(CommandStructure.SHORT_FLAG)) {
            return new CommandListPairsShort();
        } else {
            throw new IncorrectListFlagsException(MESSAGE_INCORRECT_PAIR_LIST_FLAG);
        }
    }
}

