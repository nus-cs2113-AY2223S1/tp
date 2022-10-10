package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.user.UserList;

public class RemoveUserCommand extends Command {
    private final String[] args;
    private final UserList userList;

    public RemoveUserCommand(String[] args, UserList userList)
            throws InsufficientArgumentsException {
        this.args = args;
        this.userList = userList;
        if (args.length != 1) {
            throw new InsufficientArgumentsException();
        }
    }

    public boolean executeCommand() {
        String userId = args[0];
        this.userList.deleteUser(userId);
        Ui.deleteUserMessage(userList.getUserById(userId), userList.getSize());
        return false;
    }
}
