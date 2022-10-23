package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public abstract class Command {
    private final String[] input;
    protected static final String DESCRIPTION_DELIMITER = " : ";

    public String[] getInput() {
        return input;
    }

    public Command(String[] input) {
        this.input = input;
    }

    public abstract void execute(State state, Ui ui, Storage storage);

    public abstract boolean isExit();

    public abstract String getExecutionMessage();

}
