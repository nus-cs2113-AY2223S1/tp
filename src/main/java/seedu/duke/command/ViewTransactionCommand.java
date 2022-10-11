package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.TransactionNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

public class ViewTransactionCommand extends Command {
    private final String[] parts;
    private final TransactionList transactionList;

    public ViewTransactionCommand(String[] parts, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException();
        }
    }

    public String getArgsViewTransactionCmd() throws InvalidArgumentException {
        String arg;
        if (parts[0].startsWith("t")) {
            arg = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException("Please input transaction name in the correct format");
        }
        return arg;
    }

    private boolean isValidTransaction(String transactionId) throws TransactionNotFoundException {
        try {
            transactionList.getTransactionById(transactionId);
            return true;
        } catch (TransactionNotFoundException e) {
            throw new TransactionNotFoundException(e.getMessage());
        }
    }

    public boolean executeCommand() throws TransactionNotFoundException, InvalidArgumentException {
        String transactionName = getArgsViewTransactionCmd();
        if (isValidTransaction(transactionName)) {
            Transaction transaction = this.transactionList.getTransactionById(transactionName);
            Ui.viewTransactionMessage(transaction);
        }
        return false;
    }
}
