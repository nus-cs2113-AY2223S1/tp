package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

import static seedu.duke.common.InfoMessages.INFO_LIST;
import static seedu.duke.common.InfoMessages.INFO_LIST_EMPTY;

/**
 * Represents a list command object that will execute the operations for List command.
 */
public class ListCommand extends Command {
    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        String transactionsList = transactions.listTransactions();
        if (transactionsList.isEmpty()) {
            Ui.showInfoMessage(INFO_LIST_EMPTY.toString());
            return;
        }
        Ui.showTransactionsList(transactionsList, INFO_LIST.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
