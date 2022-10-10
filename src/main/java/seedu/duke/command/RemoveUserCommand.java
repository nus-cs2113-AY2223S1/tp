package seedu.duke.command;

import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidUserException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.User;
import seedu.duke.user.UserList;

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
            throw new InsufficientArgumentsException();
        }
    }

    public String[] getArgsRemoveUserCmd() throws InvalidArgumentException {
        String[] args = new String[1];
        for (String part : parts) {
            if (part.startsWith("u")) {
                args[0] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException("One of the parts is in incorrect format");
            }
        }
        return args;
    }

    public boolean isBorrowing(String username) throws InvalidUserException {
        if (transactionList.hasThisBorrower(username)) {
            throw new InvalidUserException("This user is currently borrowing something");
        }
        return false;
    }

    public boolean isLending(String username) throws InvalidUserException {
        if (itemList.hasThisLender(username)) {
            throw new InvalidUserException("This user is currently lending something");
        }
        return false;
    }

    public boolean canDeleteUser(String username) throws InvalidUserException {
        return !isBorrowing(username) && !isLending(username);
    }

    public boolean executeCommand()
            throws InvalidArgumentException, InvalidUserException, UserNotFoundException {
        String[] args = getArgsRemoveUserCmd();
        String username = args[0];
        User user = userList.getUserById(username);
        if (canDeleteUser(username)) {
            userList.deleteUser(username);
            itemList.deleteAllItemOfAnUser(username);
        }
        //ui.confirmDeleteUser(user, userList);
        return false;
    }
}
