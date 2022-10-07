package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

import static seedu.duke.common.InfoMessages.*;

/**
 * Represents a purge command object that will execute the operations for Purge command.
 */
public class PurgeCommand extends Command {
    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage An instance of the Storage class.
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
