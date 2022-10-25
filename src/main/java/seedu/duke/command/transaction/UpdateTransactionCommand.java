package seedu.duke.command.transaction;


import seedu.duke.command.Command;
import seedu.duke.exception.DurationInvalidException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.TransactionNotFoundException;
import seedu.duke.ui.Ui;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_DURATION_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NUMBER_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_TX_NOT_FOUND;

// @@author winston-lim

/**
 * A representation of a command to add a new transaction.
 */
public class UpdateTransactionCommand extends Command {
    private final String[] parts;
    private final TransactionList transactionList;

    /**
     * Constructor for AddTransactionCommand.
     *
     * @param parts           The parts from user input
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public UpdateTransactionCommand(String[] parts, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = transactionList;
        // For now only support updating duration, we can extend this in the future to support
        // updating all fields.
        if (parts.length != 2) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    /**
     * Gets arg values from the given part.
     *
     * @return An array of arg values
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     */
    private String[] getArgsAddTxCmd() throws InvalidArgumentException {
        String[] args = new String[this.parts.length];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals("t")) {
                args[0] = CommandParser.getArgValue(part);
            } else if (delimiter.equals("d")) {
                args[1] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }

        }
        return args;
    }

    /**
     * Checks if the given transaction is valid or not.
     *
     * @param id The input id
     * @return true If that id exists in TransactionList
     * @throws TransactionNotFoundException thrown when transaction cannot be found
     */
    private boolean isValidTxId(String id) throws TransactionNotFoundException {
        try {
            transactionList.getTransactionById(id);
            return true;
        } catch (TransactionNotFoundException e) {
            throw new TransactionNotFoundException(MESSAGE_TX_NOT_FOUND);
        }
    }

    /**
     * Checks if the duration is valid or not.
     *
     * @param duration The input duration
     * @return true If that number can be parsed and greater than 0
     * @throws DurationInvalidException If the number is less than 0
     */
    private boolean isValidDuration(String duration) throws DurationInvalidException {
        try {
            if (Integer.parseInt(duration) < 0) {
                throw new DurationInvalidException(MESSAGE_DURATION_INVALID);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_NUMBER_FORMAT_INVALID);
        }
    }

    private boolean areValidArgs(String[] args)
            throws TransactionNotFoundException, DurationInvalidException {
        return isValidTxId(args[0]) && isValidDuration(args[1]);
    }

    /**
     * Executes UpdateTransactionCommand.
     *
     * @return false
     * @throws InvalidArgumentException     If there is a part that cannot be parsed
     * @throws TransactionNotFoundException If the user cannot be found
     * @throws DurationInvalidException     If the number is less than 0
     */
    public boolean executeCommand() throws InvalidArgumentException, TransactionNotFoundException,
            DurationInvalidException {
        String[] args = getArgsAddTxCmd();
        if (areValidArgs(args)) {
            String txId = args[0];
            int duration = Integer.parseInt(args[1]);
            Transaction updatedTx = this.transactionList.updateTransactionDuration(txId, duration);
            Ui.updateTransactionMessage(updatedTx);
        }
        return false;
    }
}

