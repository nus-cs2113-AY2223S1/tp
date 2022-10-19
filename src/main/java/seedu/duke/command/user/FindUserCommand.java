package seedu.duke.command.user;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

public class FindUserCommand extends Command {
    private final String[] parts;
    private final UserList userList;

    public FindUserCommand(String[] parts, UserList userList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    private String getArgsFindUserCommand() throws InvalidArgumentException {
        String arg = "";
        for (String part : parts) {
            if (part.startsWith("k ")) {
                arg = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return arg;
    }

    @Override
    public boolean executeCommand() throws InvalidArgumentException, UserNotFoundException {
        String arg = getArgsFindUserCommand();
        Ui.printResponse(userList.getUsersByKeyword(arg).toString());
        return false;
    }
}
