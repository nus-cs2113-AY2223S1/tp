package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

/**
 * Represents a bye command object that will execute the operations for Bye command.
 */
public class ByeCommand extends Command {

    // The command word used to trigger the execution of Moolah Manager's operations.
    public static final String COMMAND_WORD = "BYE";
    // The description for the usage of command.
    public static final String COMMAND_DESCRIPTION = "To exit the application.";
    // The guiding information for the usage of command.
    public static final String COMMAND_USAGE = "Usage: bye";
    // The formatting information for the parameters used by the command.
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information: -NIL-";

    /**
     * Executes the operations related to the command.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        Ui.showExit();
        //storage.writeToFile(transactions);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
