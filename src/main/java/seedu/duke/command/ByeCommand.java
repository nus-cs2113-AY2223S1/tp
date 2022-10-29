package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.parser.Parser;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_USAGE = "bye";
    public static final String COMMAND_DESCRIPTION = "Exit YAMOM.";

    private static final String MESSAGE_EXIT = "Shutting down......";

    public ByeCommand(String[] input) throws YamomException {
        super(input);
        Parser.singleWordCommandError(input);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        ui.displayMessage(getExecutionMessage());
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getExecutionMessage() {
        return MESSAGE_EXIT;
    }
}
