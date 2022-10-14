package seedu.duke.command.user;

import seedu.duke.command.Command;
import seedu.duke.exception.ContactNumberInvalidException;
import seedu.duke.exception.DuplicateException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidUserException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.user.User;
import seedu.duke.user.UserList;
import seedu.duke.ui.Ui;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CONTACT_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CONTACT_LENGTH_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USERNAME_TAKEN;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_AGE_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_AGE_OUT_OF_RANGE;

public class AddUserCommand extends Command {
    private final String[] parts;
    private final UserList userList;

    public AddUserCommand(String[] parts, UserList userList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        if (parts.length != 3) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
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
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }

        }
        return args;
    }

    private boolean isValidName(String userName) throws DuplicateException {
        try {
            userList.getUserById(userName);
            throw new DuplicateException(MESSAGE_USERNAME_TAKEN);
        } catch (UserNotFoundException e) {
            return true;
        }
    }

    private boolean isValidAge(String age) throws InvalidUserException {
        try {
            if (Integer.parseInt(age) < 10 || Integer.parseInt(age) > 100) {
                throw new InvalidUserException(MESSAGE_USER_AGE_OUT_OF_RANGE);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_USER_AGE_INVALID);
        }
    }

    private boolean isValidContactNumber(String contactNumber) throws ContactNumberInvalidException {
        if (contactNumber.length() != 8) {
            throw new ContactNumberInvalidException(MESSAGE_CONTACT_LENGTH_INVALID);
        }
        try {
            Integer.parseInt(contactNumber);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_CONTACT_FORMAT_INVALID);
        }
    }

    private boolean areValidArgs(String[] args)
            throws ContactNumberInvalidException, DuplicateException, InvalidUserException {
        return isValidName(args[0]) && isValidAge(args[1]) && isValidContactNumber(args[2]);
    }

    public boolean executeCommand()
            throws InsufficientArgumentsException, InvalidArgumentException,
            ContactNumberInvalidException, DuplicateException, InvalidUserException {
        String[] args = getArgsAddUserCmd();
        if (areValidArgs(args)) {
            User user = new User(args[0], Integer.parseInt(args[1]), args[2]);
            this.userList.addUser(user);
            Ui.addUserMessage(user, userList.getSize());
        }
        return false;
    }
}
