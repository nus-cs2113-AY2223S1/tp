package seedu.duke.command.user;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

// @@author chiewyx

/**
 * A representation of a command to view a user's items.
 */
public class ViewUserItemsCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final TransactionList transactionList;
    private final UserList userList;

    private static final String USER_ID_DELIM = "u";

    /**
     * Constructor for ViewUserItemsCommand.
     *
     * @param parts The parts from user input
     * @param userList The list of users to work with
     * @param itemList The list of items to work with
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException if arguments is insufficient
     */
    public ViewUserItemsCommand(String[] parts, UserList userList, ItemList itemList,
            TransactionList transactionList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        this.itemList = itemList;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }

    /**
     * Get arg values from the respective parts.
     *
     * @return A string of arg value
     * @throws InvalidArgumentException if the part does not fit the command
     */
    private String getArgViewUserItemsCmd() throws InvalidArgumentException {
        String args;
        String delimiter = CommandParser.getArgsDelimiter(parts[0]);
        if (delimiter.equals(USER_ID_DELIM)) {
            args = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
        }
        return args;
    }

    /**
     * Checks if user is valid.
     *
     * @param userName name of user
     * @return true if is valid
     * @throws UserNotFoundException if user is not found in the list of users
     */
    private boolean isValidUser(String userName) throws UserNotFoundException {
        try {
            userList.getUserById(userName);
            return true;
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    /**
     * Get the list of items that belongs to the user.
     *
     * @return list of items that belong to the user
     * @throws UserNotFoundException if user is not found in list of users
     * @throws InvalidArgumentException if arg is invalid
     */
    protected ItemList getUserItems() throws UserNotFoundException, InvalidArgumentException {
        String args = getArgViewUserItemsCmd();
        ItemList userItems = new ItemList();
        if (isValidUser(args)) {
            for (Item item : itemList.getItemList()) {
                if (item.getOwnerId().equals(args)) {
                    userItems.addItem(item);
                }
            }
        }
        return userItems;
    }

    /**
     * Executes ViewUserItemsCommand.
     *
     * @return false
     * @throws InvalidArgumentException If the argument is invalid
     * @throws UserNotFoundException If the user is not found in list of users
     */
    @Override
    public boolean executeCommand() throws InvalidArgumentException, UserNotFoundException {
        ItemList userItems = getUserItems();
        Ui.printResponse(userItems.toString(transactionList));
        return false;
    }
}
