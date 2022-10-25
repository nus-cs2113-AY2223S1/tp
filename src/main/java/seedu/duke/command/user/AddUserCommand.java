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

//@@author bdthanh

/**
 * A representation of a command to add a new user.
 */
public class AddUserCommand extends Command {
    private final String[] parts;
    private final UserList userList;

    /**
     * Constructor for AddUserCommand.
     *
     * @param parts    The parts from user input
     * @param userList The list of users to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public AddUserCommand(String[] parts, UserList userList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        if (parts.length != 3) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    /**
     * Gets arg values from the given part.
     *
     * @return AN array of arg values
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     */
    private String[] getArgsAddUserCmd() throws InvalidArgumentException {
        String[] args = new String[3];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals("n")) {
                args[0] = CommandParser.getArgValue(part);
            } else if (delimiter.equals("a")) {
                args[1] = CommandParser.getArgValue(part);
            } else if (delimiter.equals("c")) {
                args[2] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }

        }
        return args;
    }

    /**
     * Checks if a username is valid or not.
     *
     * @param userName The input username
     * @return true If that name cannot be found in the current user list
     * @throws DuplicateException If that username is taken
     */
    private boolean isValidName(String userName) throws DuplicateException {
        try {
            userList.getUserById(userName);
            throw new DuplicateException(MESSAGE_USERNAME_TAKEN);
        } catch (UserNotFoundException e) {
            return true;
        }
    }

    /**
     * Checks if age is valid or not.
     *
     * @param age The input user's age
     * @return true If age is in the correct range and correct format
     * @throws InvalidUserException If age is out of range
     */
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

    /**
     * Checks if contact number is valid or not.
     *
     * @param contactNumber The input user's contact number
     * @return true If contact number is in the correct format and correct length
     * @throws ContactNumberInvalidException If contact number has the wrong length
     */
    private boolean isValidContactNumber(String contactNumber)
            throws ContactNumberInvalidException {
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

    /**
     * Check if all args is valid or not.
     *
     * @param args The array of input args
     * @return true If they are all valid
     * @throws ContactNumberInvalidException If contact number has the wrong length
     * @throws DuplicateException            If that username is taken
     * @throws InvalidUserException          If age is out of range
     */
    private boolean areValidArgs(String[] args)
            throws ContactNumberInvalidException, DuplicateException, InvalidUserException {
        assert args.length == 3 : "Args length is invalid";
        return isValidName(args[0]) && isValidAge(args[1]) && isValidContactNumber(args[2]);
    }

    /**
     * Executes AddUserCommand.
     *
     * @return false If it is not an exit command
     * @throws InsufficientArgumentsException If the number of args is incorrect
     * @throws InvalidArgumentException       If there is a part that cannot be parsed
     * @throws ContactNumberInvalidException  If contact number has the wrong length
     * @throws DuplicateException             If that username is taken
     * @throws InvalidUserException           If age is out of range
     */
    public boolean executeCommand() throws InsufficientArgumentsException, InvalidArgumentException,
            ContactNumberInvalidException, DuplicateException, InvalidUserException {
        String[] args = getArgsAddUserCmd();
        assert args.length == 3 : "Args length is invalid";
        if (areValidArgs(args)) {
            User user = new User(args[0], Integer.parseInt(args[1]), args[2]);
            this.userList.addUser(user);
            Ui.addUserMessage(user, userList.getSize());
        }
        return false;
    }
}
