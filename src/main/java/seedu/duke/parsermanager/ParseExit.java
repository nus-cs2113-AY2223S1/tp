package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandQuit;
import seedu.duke.exception.ExtraParametersException;

import static seedu.duke.Messages.MESSAGE_BYE_PARAMETERS_PRESENT;

public class ParseExit extends Parser {

    private final String commandDetail;

    public ParseExit(String exitCommandDescription) {
        this.commandDetail = exitCommandDescription;
    }

    @Override
    public Command parseCommand() throws ExtraParametersException {
        checkForEmptyDescription(commandDetail);
        return new CommandQuit();
    }

    private void checkForEmptyDescription(String commandDetail) throws ExtraParametersException {
        boolean isNotEmpty = !commandDetail.trim().isEmpty();

        if (isNotEmpty) {
            throw new ExtraParametersException(MESSAGE_BYE_PARAMETERS_PRESENT);
        }

    }
}
