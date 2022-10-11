package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.user.User;
import seedu.duke.user.UserList;

public class ViewUserCommand extends Command {
    private final String[] parts;
    private final UserList userList;

    public ViewUserCommand(String[] parts, UserList userList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException();
        }
    }

    public String getArgsViewUserCmd() throws InvalidArgumentException {
        String arg;
        if (parts[0].startsWith("u")) {
            arg = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException("Please input user name in the correct format");
        }
        return arg;
    }

    private boolean isValidUser(String userId) throws UserNotFoundException {
        try {
            userList.getUserById(userId);
            return true;
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    public boolean executeCommand() throws UserNotFoundException, InvalidArgumentException {
        String userName = getArgsViewUserCmd();
        if (isValidUser(userName)) {
            User user = this.userList.getUserById(userName);
            Ui.viewUserMessage(user);
        }
        return false;
    }
}
