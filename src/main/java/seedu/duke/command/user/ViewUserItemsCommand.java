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

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

public class ViewUserItemsCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final TransactionList transactionList;
    private final UserList userList;

    public ViewUserItemsCommand(String[] parts, UserList userList, ItemList itemList, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        this.itemList = itemList;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    private String getArgFindUserItemsCmd() throws InvalidArgumentException {
        String args;
        String delimiter = CommandParser.getArgsDelimiter(parts[0]);
        if (delimiter.equals("u")) {
            args = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
        }
        return args;
    }

    private boolean isValidUser(String userName) throws UserNotFoundException {
        try {
            userList.getUserById(userName);
            return true;
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    protected ItemList getUserItems() throws UserNotFoundException, InvalidArgumentException {
        String args = getArgFindUserItemsCmd();
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

    public boolean executeCommand() throws InvalidArgumentException, UserNotFoundException {
        ItemList userItems = getUserItems();
        Ui.printResponse(userItems.toString(transactionList));
        return false;
    }
}
