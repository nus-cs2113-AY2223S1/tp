package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.common.InfoMessages.INFO_PURGE;
import static seedu.duke.common.InfoMessages.INFO_PURGE_ABORT;
import static seedu.duke.common.InfoMessages.INFO_PURGE_EMPTY;
import static seedu.duke.common.InfoMessages.INFO_PURGE_WARNING;

/**
 * Represents a purge command object that will execute the operations for Purge command.
 */
public class PurgeCommand extends Command {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "PURGE";
    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To purge a all entries in the list of transactions.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: purge";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:  -NIL-";

    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION + LINE_SEPARATOR
            + COMMAND_USAGE + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR;

    private static final Logger theLogger = Logger.getLogger(AddCommand.class.getName());

    public PurgeCommand() {
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        // Shows confirmation prompt before deleting all transactions
        theLogger.setLevel(Level.WARNING);
        theLogger.log(Level.INFO, "Purge Command checks if there are no transactions"
                + " to be purged. If so, the command is aborted.");
        boolean check = isEmpty(transactions);
        if (check) {
            Ui.showInfoMessage(INFO_PURGE_EMPTY.toString());
            theLogger.log(Level.INFO, "The transactions list is found to be empty"
                    + " and the UI should display that information to the user respectively.");
            return;
        }
        Ui.showInfoMessage(INFO_PURGE_WARNING.toString());
        theLogger.log(Level.INFO, "The UI should display a confirmation prompt"
                + " for which the User would need to respond.");
        String input = ui.readCommand();

        if (input.equals("Y")) {
            TransactionList.purgeTransactions();
            assert PurgeCommand.isEmpty(transactions);
            Ui.showInfoMessage(INFO_PURGE.toString());
            theLogger.log(Level.INFO, "The transactions list is now empty"
                    + " and the UI should display that information to the user respectively.");
            theLogger.log(Level.INFO, "The end of the Purge Command.");
            return;
        }

        Ui.showInfoMessage(INFO_PURGE_ABORT.toString());
        theLogger.log(Level.INFO, "The user would have responded to not proceed with the command"
                + " and the UI should display that information to abort the command respectively.");
        theLogger.log(Level.INFO, "The end of the Purge Command.");
    }

    public static boolean isEmpty(TransactionList transactions) {
        int size = transactions.size();
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Enables the program to exit when the Bye command is issued.
     *
     * @return A boolean value that indicates whether the program shall exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
