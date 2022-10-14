package seedu.duke.command.transaction;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;
import seedu.duke.transaction.TransactionList;

public class ListTransactionsCommand extends Command {
    private final TransactionList txList;

    public ListTransactionsCommand(TransactionList txList) {
        this.txList = txList;
    }

    public boolean executeCommand() {
        Ui.printResponse(this.txList.toString());
        return false;
    }
}
