package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

public class ViewTransactionCommand extends Command {
    private final String[] args;
    private final TransactionList txList;

    public ViewTransactionCommand(String[] args, TransactionList txList)
            throws InsufficientArgumentsException {
        this.args = args;
        this.txList = txList;
        if (args.length != 1) {
            throw new InsufficientArgumentsException();
        }
    }

    public boolean executeCommand() throws DukeException {
        Transaction tx = this.txList.getTransactionById(args[DEFAULT_FIRST_INDEX]);
        System.out.println(tx);
        return false;
    }
}
