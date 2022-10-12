package seedu.duke.command.user;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

public class ListUsersCommand extends Command {
    private final UserList userList;

    public ListUsersCommand(UserList userList) {
        this.userList = userList;
    }

    public boolean executeCommand() {
        Ui.printResponse(userList.toString());
        return false;
    }
}
