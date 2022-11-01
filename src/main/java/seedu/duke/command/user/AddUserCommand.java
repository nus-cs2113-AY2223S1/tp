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

import java.util.ArrayList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CONTACT_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CONTACT_LENGTH_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NAME_LENGTH_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USERNAME_TAKEN;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_AGE_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_AGE_OUT_OF_RANGE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CONTACT_DUPLICATE;

// @@author bdthanh

/**
 * A representation of a command to add a new user.
 */
public class AddUserCommand extends Command {
    private final String[] parts;
    private final UserList userList;
    private static final String NAME_DELIMITER = "n";
    private static final String AGE_DELIMITER = "a";
    private static final String CONTACT_DELIMITER = "c";
    private static final int NUMBER_OF_ARGS = 3;
    private static final int NAME_INDEX = 0;
    private static final int AGE_INDEX = 1;
    private static final int CONTACT_INDEX = 2;
    private static final int CONTACT_LENGTH = 8;
    private static final int AGE_LOWER_LIMIT = 10;
    private static final int AGE_UPPER_LIMIT = 100;
    private static final int NAME_LIMIT = 20;

    /**
     * Constructor for AddUserCommand.
     *
     * @param parts The parts from user input
     * @param userList The list of users to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public AddUserCommand(String[] parts, UserList userList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        if (parts.length != 3) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }

    /**
     * Gets arg values from the given part.
     *
     * @return AN array of arg values
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     */
    private String[] getArgsAddUserCmd() throws InvalidArgumentException {
        String[] args = new String[NUMBER_OF_ARGS];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals(NAME_DELIMITER)) {
                args[NAME_INDEX] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(AGE_DELIMITER)) {
                args[AGE_INDEX] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(CONTACT_DELIMITER)) {
                args[CONTACT_INDEX] = CommandParser.getArgValue(part);
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
    private boolean isValidName(String userName) throws DuplicateException, InvalidUserException {
        if (userName.length() > NAME_LIMIT) {
            throw new InvalidUserException(MESSAGE_NAME_LENGTH_INVALID);
        }
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
            if (Integer.parseInt(age) < AGE_LOWER_LIMIT
                    || Integer.parseInt(age) > AGE_UPPER_LIMIT) {
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
        if (contactNumber.length() != CONTACT_LENGTH) {
            throw new ContactNumberInvalidException(MESSAGE_CONTACT_LENGTH_INVALID);
        }
        if (Integer.parseInt(contactNumber) < 0) {
            throw new ContactNumberInvalidException(MESSAGE_CONTACT_FORMAT_INVALID);
        }
        try {
            ArrayList<User> checkDuplicate = userList.getUserList();
            for (User user : checkDuplicate) {
                if (contactNumber.equals(user.getContactNumber())) {
                    throw new ContactNumberInvalidException(MESSAGE_CONTACT_DUPLICATE);
                }
            }
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
     * @throws DuplicateException If that username is taken
     * @throws InvalidUserException If age is out of range
     */
    private boolean areValidArgs(String[] args)
            throws ContactNumberInvalidException, DuplicateException, InvalidUserException {
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        return isValidName(args[NAME_INDEX]) && isValidAge(args[AGE_INDEX])
                && isValidContactNumber(args[CONTACT_INDEX]);
    }

    /**
     * Executes AddUserCommand.
     *
     * @return false If it is not an exit command
     * @throws InsufficientArgumentsException If the number of args is incorrect
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     * @throws ContactNumberInvalidException If contact number has the wrong length
     * @throws DuplicateException If that username is taken
     * @throws InvalidUserException If age is out of range
     */
    public boolean executeCommand() throws InsufficientArgumentsException, InvalidArgumentException,
            ContactNumberInvalidException, DuplicateException, InvalidUserException {
        String[] args = getArgsAddUserCmd();
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        if (areValidArgs(args)) {
            User user = new User(args[0], Integer.parseInt(args[1]), args[2]);
            this.userList.addUser(user);
            Ui.addUserMessage(user, userList.getSize());
        }
        return false;
    }
}
