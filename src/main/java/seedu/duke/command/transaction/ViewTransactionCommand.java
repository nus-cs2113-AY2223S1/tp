package seedu.duke.command.transaction;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.TransactionNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

// @@author jingwei55
public class ViewTransactionCommand extends Command {
    private final String[] parts;
    private final TransactionList transactionList;
    private static final String TX_ID_DELIM = "t";

    /**
     * Constructor for ViewTransactionCommand.
     *
     * @param parts The parts from user input
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public ViewTransactionCommand(String[] parts, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }

    private String getArgsViewTransactionCmd() throws InvalidArgumentException {
        String arg;
        String delimiter = CommandParser.getArgsDelimiter(parts[0]);
        if (delimiter.equals(TX_ID_DELIM)) {
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

    /**
     * Executes ViewTransactionCommand.
     *
     * @return false
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     * @throws TransactionNotFoundException If given transaction is does not exist
     */
    public boolean executeCommand() throws TransactionNotFoundException, InvalidArgumentException {
        String transactionName = getArgsViewTransactionCmd();
        if (isValidTransaction(transactionName)) {
            Transaction transaction = this.transactionList.getTransactionById(transactionName);
            Ui.viewTransactionMessage(transaction);
        }
        return false;
    }
}
