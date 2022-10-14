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

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_BORROWING;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_LENDING;

public class RemoveUserCommand extends Command {
    private final String[] parts;
    private final UserList userList;
    private final ItemList itemList;
    private final TransactionList transactionList;

    public RemoveUserCommand(String[] parts, UserList userList, ItemList itemList, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        this.itemList = itemList;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    public String[] getArgsRemoveUserCmd() throws InvalidArgumentException {
        String[] args = new String[1];
        for (String part : parts) {
            if (part.startsWith("u")) {
                args[0] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return args;
    }

    public boolean isBorrowing(String username) throws InvalidUserException {
        if (transactionList.hasThisBorrower(username)) {
            throw new InvalidUserException(MESSAGE_USER_BORROWING);
        }
        return false;
    }

    public boolean isLending(String username, TransactionList transactionList) throws InvalidUserException {
        if (itemList.hasThisLender(username, transactionList)) {
            throw new InvalidUserException(MESSAGE_USER_LENDING);
        }
        return false;
    }

    public boolean canDeleteUser(String username, TransactionList transactionList) throws InvalidUserException {
        return !isBorrowing(username) && !isLending(username, transactionList);
    }

    public boolean executeCommand()
            throws InvalidArgumentException, InvalidUserException, UserNotFoundException {
        String[] args = getArgsRemoveUserCmd();
        String username = args[0];
        User user = userList.getUserById(username);
        if (canDeleteUser(username, transactionList)) {
            userList.deleteUser(username);
            itemList.deleteAllItemOfAnUser(username, transactionList);
        }
        Ui.deleteUserMessage(user, userList.getSize());
        return false;
    }
}
