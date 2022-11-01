package seedu.duke.command.user;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidUserException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.User;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_BORROWING;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_LENDING;

// @@author bdthanh

/**
 * A representation of a command to remove a user.
 */
public class RemoveUserCommand extends Command {
    private final String[] parts;
    private final UserList userList;
    private final ItemList itemList;
    private final TransactionList transactionList;
    private static final String USER_ID_DELIMITER = "u";
    private static final int NUMBER_OF_ARGS = 1;
    private static final int ID_INDEX = 0;

    /**
     * Constructor for RemoveUserCommand.
     *
     * @param parts The parts from user input
     * @param userList The list of users to work with
     * @param itemList The list of items to work with
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public RemoveUserCommand(String[] parts, UserList userList, ItemList itemList,
            TransactionList transactionList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        this.itemList = itemList;
        this.transactionList = transactionList;
        if (parts.length != NUMBER_OF_ARGS) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }

    /**
     * Gets arg values from the given part.
     *
     * @return An array of arg values
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     */
    private String[] getArgsRemoveUserCmd() throws InvalidArgumentException {
        String[] args = new String[NUMBER_OF_ARGS];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals(USER_ID_DELIMITER)) {
                args[ID_INDEX] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return args;
    }

    /**
     * Check if that user is currently borrowing something.
     *
     * @param username The input username
     * @return false If he/har is not currently borrowing
     * @throws InvalidUserException If he/har is currently borrowing
     */
    private boolean isBorrowing(String username) throws InvalidUserException {
        if (transactionList.hasThisBorrower(username)) {
            throw new InvalidUserException(MESSAGE_USER_BORROWING);
        }
        return false;
    }

    /**
     * Check if that user is currently lending something.
     *
     * @param username The input username
     * @return false If he/har is not currently lending
     * @throws InvalidUserException If he/har is currently lending
     */
    private boolean isLending(String username, TransactionList transactionList)
            throws InvalidUserException {
        if (itemList.hasThisLender(username, transactionList)) {
            throw new InvalidUserException(MESSAGE_USER_LENDING);
        }
        return false;
    }

    private boolean canDeleteUser(String username, TransactionList transactionList)
            throws InvalidUserException {
        return !isBorrowing(username) && !isLending(username, transactionList);
    }

    /**
     * Executes RemoveUserCommand.
     *
     * @return false
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     * @throws InvalidUserException If he/har is currently lending or borrowing
     * @throws UserNotFoundException If the user cannot be found in the list
     */
    public boolean executeCommand()
            throws InvalidArgumentException, InvalidUserException, UserNotFoundException {
        String[] args = getArgsRemoveUserCmd();
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        String username = args[ID_INDEX];
        User user = userList.getUserById(username);
        if (canDeleteUser(username, transactionList)) {
            userList.deleteUser(username);
            itemList.deleteAllItemOfAnUser(username, transactionList);
        }
        Ui.deleteUserMessage(user, userList.getSize());
        return false;
    }
}
