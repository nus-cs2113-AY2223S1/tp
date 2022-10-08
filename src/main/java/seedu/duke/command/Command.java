package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;

/**
 * Represents an object that can be inherited by other command objects.
 */
public abstract class Command {
    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage An instance of the Storage class.
     */

    // The command word used to trigger the execution of Moolah Manager's operations
    public static String COMMAND_WORD;
    // The description for the usage of command
    public static String COMMAND_DESCRIPTION;
    // The guiding information for the usage of command
    public static String COMMAND_USAGE;
    // The formatting information for the parameters used by the command
    public static String COMMAND_PARAMETERS_INFO;


    public abstract void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException;

    public abstract boolean isExit();
}
