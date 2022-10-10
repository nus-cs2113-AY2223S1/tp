package seedu.duke.command;

import seedu.duke.exception.ContactNumberInvalidException;
import seedu.duke.exception.DuplicateException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.user.User;
import seedu.duke.user.UserList;
import seedu.duke.Ui;

public class AddUserCommand extends Command {
    private final String[] parts;
    private final UserList userList;

    public AddUserCommand(String[] parts, UserList userList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        if (parts.length != 3) {
            throw new InsufficientArgumentsException();
        }
    }

    public String[] getArgsAddUserCmd() throws InvalidArgumentException {
        String[] args = new String[3];
        for (String part : parts) {
            if (part.startsWith("n")) {
                args[0] = CommandParser.getArgValue(part);
            } else if (part.startsWith("a")) {
                args[1] = CommandParser.getArgValue(part);
            } else if (part.startsWith("c")) {
                args[2] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException("One of the parts is in incorrect format");
            }

        }
        return args;
    }

    private boolean isValidName(String userName) throws DuplicateException {
        try {
            userList.getUserById(userName);
            throw new DuplicateException("This username has been taken");
        } catch (UserNotFoundException e) {
            return true;
        }
    }

    private boolean isValidAge(String age) {
        try {
            Integer.parseInt(age);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Age should only contain digit 0-9");
        }
    }

    private boolean isValidContactNumber(String contactNumber) throws ContactNumberInvalidException {
        if (contactNumber.length() != 8) {
            throw new ContactNumberInvalidException("Contact number length must have length of 8");
        }
        try {
            Integer.parseInt(contactNumber);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Contact number should only contain digit 0-9");
        }
    }

    private boolean areValidArgs(String[] args) throws ContactNumberInvalidException, DuplicateException {
        return isValidName(args[0]) && isValidAge(args[1]) && isValidContactNumber(args[2]);
    }

    public boolean executeCommand()
            throws InsufficientArgumentsException, InvalidArgumentException,
            ContactNumberInvalidException, DuplicateException {
        String[] args = getArgsAddUserCmd();
        if (areValidArgs(args)) {
            User user = new User(args[0], Integer.parseInt(args[1]), args[2]);
            this.userList.addUser(user);
            Ui.addUserMessage(user, userList.getSize());
        }
        return false;
    }
}
