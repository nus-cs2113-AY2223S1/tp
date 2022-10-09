package seedu.duke.command;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.user.User;
import seedu.duke.user.UserList;

public class ViewUserCommand extends Command {
    private final String[] args;
    private final UserList userList;

    public ViewUserCommand(String[] args, UserList userList) throws InsufficientArgumentsException {
        this.args = args;
        this.userList = userList;
        if (args.length != 1) {
            throw new InsufficientArgumentsException();
        }
    }

    public boolean executeCommand() throws UserNotFoundException {
        User user = this.userList.getUserById(args[DEFAULT_FIRST_INDEX]);
        System.out.println(user);
        return false;
    }
}
