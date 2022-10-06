package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.InfoMessages;
import seedu.duke.data.TransactionList;

public class ListCommand extends Command {
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        // Prints all transactions if input is equal to "list"
        String transactionsList = transactions.listTransactions();
        if (transactionsList.isEmpty()) {
            Ui.showInfoMessage(InfoMessages.INFO_LIST_EMPTY.toString());
            return;
        }
        Ui.showTransactionsList(transactionsList, InfoMessages.INFO_LIST.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
