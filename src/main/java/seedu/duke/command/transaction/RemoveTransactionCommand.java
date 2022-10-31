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

//@@author bdthanh

/**
 * A representation of a command to remove a transaction.
 */
public class RemoveTransactionCommand extends Command {
    private final String[] parts;
    private final TransactionList transactionList;
    private static final String TX_ID_DELIMITER = "t";
    private static final int NUMBER_OF_ARGS = 1;
    private static final int ID_INDEX = 0;

    /**
     * Constructor for RemoveItemCommand.
     *
     * @param parts           The parts from user input
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public RemoveTransactionCommand(String[] parts, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = transactionList;
        if (parts.length != NUMBER_OF_ARGS) {
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
        String[] args = new String[NUMBER_OF_ARGS];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals(TX_ID_DELIMITER)) {
                args[ID_INDEX] = CommandParser.getArgValue(part);
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
     * @throws InvalidArgumentException     If there is a part that cannot be parsed
     */
    public boolean executeCommand()
            throws TransactionNotFoundException, InvalidArgumentException {
        String[] args = getArgsRemoveTxCmd();
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        String transactionId = args[ID_INDEX].trim();
        Transaction deletedTransaction = transactionList.getTransactionById(transactionId);
        transactionList.deleteTransaction(transactionId);
        Ui.deleteTransactionMessage(deletedTransaction, transactionList.getSize());
        return false;
    }
}
