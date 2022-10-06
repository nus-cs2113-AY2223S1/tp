package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

import static seedu.duke.common.InfoMessages.INFO_PURGE;
import static seedu.duke.common.InfoMessages.INFO_PURGE_ABORT;
import static seedu.duke.common.InfoMessages.INFO_PURGE_WARNING;

public class PurgeCommand extends Command {
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        // Shows confirmation prompt before deleting all transactions
        ui.showInfoMessage(INFO_PURGE_WARNING.toString());
        String input = ui.readCommand();

        if (input.equals("Y")) {
            TransactionList.purgeEntries(transactions);
            Ui.showInfoMessage(INFO_PURGE.toString());
            return;
        }

        Ui.showInfoMessage(INFO_PURGE_ABORT.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
