package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.command.Command;
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
