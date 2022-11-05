package seedu.duke.command.transaction;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidTransactionException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

// @@author jorellesee
public class ViewLendTransactionsByUserCommand extends Command {

    private final String[] parts;
    private static final String USER_ID_DELIMITER = "u";

    private final TransactionList transactionList;
    private final UserList userList;

    /**
     * Constructor for ViewLendTransactionsByUserCommand.
     *
     * @param parts The parts from user input
     * @param userList The list of users to work with
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public ViewLendTransactionsByUserCommand(String[] parts, TransactionList transactionList,
            UserList userList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = transactionList;
        this.userList = userList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }

    private String getArgs() throws InvalidArgumentException {
        String args;
        String delimiter = CommandParser.getArgsDelimiter(parts[0]);
        if (delimiter.equals(USER_ID_DELIMITER)) {
            args = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
        }
        return args;
    }

    private boolean isValidUser(String arg) throws UserNotFoundException {
        try {
            userList.getUserById(arg);
            return true;
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    /**
     * Executes ViewLendTransactionsByUserCommand.
     *
     * @return false
     * @throws InsufficientArgumentsException If insufficient arguments are given
     * @throws UserNotFoundException If the user cannot be found
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     * @throws InvalidTransactionException If given transaction is invalid
     */
    public boolean executeCommand() throws InsufficientArgumentsException, UserNotFoundException,
            InvalidArgumentException, InvalidTransactionException {
        String arg = getArgs();
        if (isValidUser(arg)) {
            TransactionList returnList = transactionList.getLendTransactionsByUser(arg);
            Ui.printResponse(returnList.toString());
        }
        return false;
    }
}

