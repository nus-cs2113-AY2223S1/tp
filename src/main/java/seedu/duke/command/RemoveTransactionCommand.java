package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

public class RemoveTransactionCommand extends Command {
    private final String[] args;
    private final TransactionList txList;

    public RemoveTransactionCommand(String[] args, TransactionList txList)
            throws InsufficientArgumentsException {
        this.args = args;
        this.txList = txList;
        if (args.length != 1) {
            throw new InsufficientArgumentsException();
        }
    }

    public boolean executeCommand() throws DukeException {
        String txId = args[0];
        this.txList.deleteTransaction(txId);
        Ui.deleteTransactionMessage(txList.getTransactionById(txId), txList.getSize());
        return false;
    }
}
