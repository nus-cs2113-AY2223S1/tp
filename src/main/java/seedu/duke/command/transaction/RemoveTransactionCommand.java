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

/**
 * A representation of a command to remove a transaction.
 */
public class RemoveTransactionCommand extends Command {
    private final String[] parts;
    private final TransactionList transactionList;

    /**
     * Constructor for RemoveItemCommand.
     *
     * @param parts The parts from user input
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public RemoveTransactionCommand(String[] parts, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    /**
     * Gets arg values from the given part.
     *
     * @return An array of arg values
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     */
    private String[] getArgsRemoveTxCmd() throws InvalidArgumentException {
        String[] args = new String[1];
        for (String part : parts) {
            if (part.startsWith("t ")) {
                args[0] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return args;
    }

    /**
     * Executes RemoveTransactionCommand.
     *
     * @return false
     * @throws TransactionNotFoundException If the transaction cannot be found in the list
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     */
    public boolean executeCommand()
            throws TransactionNotFoundException, InvalidArgumentException {
        String[] args = getArgsRemoveTxCmd();
        String transactionId = args[0].trim();
        Transaction deletedTransaction = transactionList.getTransactionById(transactionId);
        transactionList.deleteTransaction(transactionId);
        Ui.deleteTransactionMessage(deletedTransaction, transactionList.getSize());
        return false;
    }
}
