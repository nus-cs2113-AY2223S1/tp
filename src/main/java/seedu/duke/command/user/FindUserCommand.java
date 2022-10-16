package seedu.duke.command.user;

import seedu.duke.command.Command;
import seedu.duke.exception.*;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;

public class FindUserCommand extends Command {
    private final String[] parts;
    private final UserList userList;

    public FindUserCommand(String[] parts, UserList userList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        if (parts.length != 3) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    @Override
    public boolean executeCommand(){
        try {
            Ui.printResponse(userList.getUsersByKeyword(parts[3]).toString());
        }catch(UserNotFoundException e){
            Ui.printResponse(e.getMessage());
        }
        return false;
    }
}
