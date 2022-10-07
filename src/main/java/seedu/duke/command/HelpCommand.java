package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

/**
 * Represents a help command object that will execute the operations for Help command.
 */
public class HelpCommand extends Command {

    // The command word used to trigger the execution of Moolah Manager's operations.
    public static final String COMMAND_WORD = "HELP";
    // The description for the usage of command.
    public static final String COMMAND_DESCRIPTION = "To display a list of available commands "
            + "with their respective expected parameters.\n"
            + "Type \"help o/detailed\" for a detailed version of all parameters used.";
    // The guiding information for the usage of command.
    public static final String COMMAND_USAGE = "Usage: help [o/detailed]";
    // The formatting information for the parameters used by the command.
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information: \n"
            + "(Optional) o/detailed - Detailed version of guide.";

    /**
     * Executes the operations related to the command.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        Ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
