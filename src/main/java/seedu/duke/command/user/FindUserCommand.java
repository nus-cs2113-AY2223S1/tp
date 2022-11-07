package seedu.duke.command.user;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

// @@author jorellesee
public class FindUserCommand extends Command {
    private final String[] parts;
    private static final String KEYWORD_DELIMITER = "k";

    private final UserList userList;

    /**
     * Constructor for FindUserCommand.
     *
     * @param parts The parts from user input
     * @param userList The list of users to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public FindUserCommand(String[] parts, UserList userList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }

    private String getArgsFindUserCommand() throws InvalidArgumentException {
        String arg = "";
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals(KEYWORD_DELIMITER)) {
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
