package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

/**
 * An abstract class defining the higher level behaviour expected of all commands when executed.
 */
public abstract class Command {
    private final String[] input;

    /**
     * Getter for user input.
     * @return the array of user input
     */
    public String[] getInput() {
        return input;
    }

    public Command(String[] input) {
        this.input = input;
    }

    /**
     * A standardized method to invoke the execution of various commands.
     *
     * @param state The state of the programme, containing all the modules selected
     * @param ui The user interface of the programme to display messages to user
     * @param storage The programme storage to retrive, modify or write to data file
     * @throws YamomException if the command encounter unexpected behaviour during execution
     */
    public abstract void execute(State state, Ui ui, Storage storage) throws YamomException;

    /**
     * A standardized method to check if the application is to be terminated.
     *
     * @return if the application should terminate after the execution of the command.
     */
    public abstract boolean isExit();

    /**
     * Returns the message to be displayed to the user when the command is executed successfully.
     *
     * @return the execution message to be displayed
     */
    public abstract String getExecutionMessage();
}
