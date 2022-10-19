package seedu.duke.command.transaction;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.TransactionNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

public class ViewTransactionCommand extends Command {
    private final String[] parts;
    private final TransactionList transactionList;

    public ViewTransactionCommand(String[] parts, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    private String getArgsViewTransactionCmd() throws InvalidArgumentException {
        String arg;
        if (parts[0].startsWith("t ")) {
            arg = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
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
