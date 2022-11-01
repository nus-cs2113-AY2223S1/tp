package seedu.duke.command.user;

import seedu.duke.command.Command;
import seedu.duke.exception.ContactNumberInvalidException;
import seedu.duke.exception.DuplicateException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidUserException;
import seedu.duke.parser.CommandParser;
import seedu.duke.user.User;
import seedu.duke.user.UserList;
import seedu.duke.ui.Ui;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

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
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        userList.checkValidArgsForUser(args);
        User user = new User(args[0], Integer.parseInt(args[1]), args[2]);
        this.userList.addUser(user);
        Ui.addUserMessage(user, userList.getSize());

        return false;
    }
}
