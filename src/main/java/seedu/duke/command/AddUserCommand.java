package seedu.duke.command;

import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.user.User;
import seedu.duke.user.UserList;
import seedu.duke.Ui;

public class AddUserCommand extends Command {
    private final String[] args;
    private final UserList userList;

    public AddUserCommand(String[] args, UserList userList) throws InsufficientArgumentsException {
        this.args = args;
        this.userList = userList;
        if (args.length != 3) {
            throw new InsufficientArgumentsException();
        }
    }

    public boolean executeCommand() {
        String name = this.args[0];
        int age = Integer.parseInt(this.args[1]);
        String contactNumber = this.args[2];
        this.userList.addUser(new User(name, age, contactNumber));
        Ui.addUserMessage(new User(name, age, contactNumber), userList.getSize());
        return false;
    }
}
