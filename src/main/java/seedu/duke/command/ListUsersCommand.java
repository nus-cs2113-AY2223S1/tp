package seedu.duke.command;

import seedu.duke.command.Command;
import seedu.duke.user.UserList;

public class ListUsersCommand extends Command {
    private final UserList userList;

    public ListUsersCommand(UserList userList) {
        this.userList = userList;
    }

    public boolean executeCommand() {
        System.out.println(this.userList.listUser());
        return false;
    }
}
