package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

import static seedu.duke.common.InfoMessages.INFO_PURGE;
import static seedu.duke.common.InfoMessages.INFO_PURGE_ABORT;
import static seedu.duke.common.InfoMessages.INFO_PURGE_EMPTY;
import static seedu.duke.common.InfoMessages.INFO_PURGE_WARNING;

/**
 * Represents a purge command object that will execute the operations for Purge command.
 */
public class PurgeCommand extends Command {

    // The command word used to trigger the execution of Moolah Manager's operations.
    public static final String COMMAND_WORD = "PURGE";
    // The description for the usage of command.
    public static final String COMMAND_DESCRIPTION = "To purge a all entries in the list of transactions.";
    // The guiding information for the usage of command.
    public static final String COMMAND_USAGE = "Usage: purge";
    // The formatting information for the parameters used by the command.
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:  -NIL-";

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
        boolean check = isEmpty(transactions);
        if (check) {
            Ui.showInfoMessage(INFO_PURGE_EMPTY.toString());
            return;
        }
        ui.showInfoMessage(INFO_PURGE_WARNING.toString());
        String input = ui.readCommand();

        if (input.equals("Y")) {
            TransactionList.purgeTransactions();
            Ui.showInfoMessage(INFO_PURGE.toString());
            return;
        }

        Ui.showInfoMessage(INFO_PURGE_ABORT.toString());
    }

    public static boolean isEmpty(TransactionList transactions) {
        int size = transactions.size();
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
