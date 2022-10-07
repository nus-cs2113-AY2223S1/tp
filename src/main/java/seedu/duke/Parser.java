package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.GetCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.PurgeCommand;

import seedu.duke.exception.MoolahException;
import seedu.duke.exception.InvalidCommandException;

public class Parser {
    private static final String EMPTY_STRING = "";
    private static final String DELIMITER = " ";
    private static final int SPLIT_POSITION = 2;

    /**
     * Splits the user input into two parts, i.e. the command and the description.
     *
     * @param inData A line of input entered by the user.
     * @return A string array of input tokens.
     */
    public static String[] splitInput(String inData) {
        String[] inputTokens = inData.split(DELIMITER, SPLIT_POSITION);
        if (!inData.contains(DELIMITER)) {
            inputTokens = new String[]{inData, EMPTY_STRING};
        }
        return inputTokens;
    }

    /**
     * Parses the user input and deal with any input error returned.
     *
     * @param inData A line of input entered by the user.
     * @return IS_EXIT If input equals "bye", else return IS_CONTINUE.
     * @throws MoolahException Any command input exceptions captured by Moolah Manager.
     */
    public static Command parse(String inData) throws MoolahException {
        Command command = null;
        String[] inputTokens = splitInput(inData);

        // list commands duke to list all the tasks stored and their completion status
        // try at the start cos of the errors possibly
        switch (inputTokens[0]) {
        case "help":
            command = new HelpCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "find":
            command = new FindCommand(inputTokens[1]);
            break;
        case "get":
            // Additional tokens will be allowed for get
            command = new GetCommand();
            break;
        case "purge":
            command = new PurgeCommand();
            break;
        case "delete":
            command = new DeleteCommand(inputTokens[1]);
            break;
        case "add":
            command = new AddCommand(inputTokens[1]);
            break;
        case "edit":
            command = new EditCommand(inputTokens[1]);
            break;
        case "bye":
            command = new ByeCommand();
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }
}
