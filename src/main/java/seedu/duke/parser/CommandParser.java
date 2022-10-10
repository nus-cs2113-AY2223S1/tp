package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.StatsCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.PurgeCommand;

import seedu.duke.exception.MoolahException;
import seedu.duke.exception.InvalidCommandException;

public class CommandParser {
    private static final String EMPTY_STRING = "";
    private static final String DELIMITER = " ";
    private static final int SPLIT_POSITION = 2;

    /**
     * Parses the user input into Command class based on the command word.
     *
     * @param fullCommandInput A line of input entered by the user.
     * @return IS_EXIT If input equals "bye", else return IS_CONTINUE.
     * @throws MoolahException Any command input exceptions captured by Moolah Manager.
     */
    public static Command parse(String fullCommandInput) throws MoolahException {
        Command command = null;
        String[] inputTokens = splitInput(fullCommandInput);

        assert  inputTokens.length == 2;
        String commandWordInput = inputTokens[0];
        String parametersInput = inputTokens[1];

        // Parse the command word from user input
        command = getCommand(commandWordInput);

        // Parse the parameters from user input to set up the parameters for the command
        assert command != null;
        ParameterParser.parse(command, parametersInput);

        return command;
    }

    /**
     * Splits the user input into two parts, i.e. the command word and the parameter(s).
     *
     * @param fullCommandInput A line of input entered by the user.
     * @return A string array of input tokens.
     */
    public static String[] splitInput(String fullCommandInput) {
        String[] inputTokens;
        // Separate the command word and the parameters into an array of size two
        if (fullCommandInput.contains(DELIMITER)) {
            inputTokens = fullCommandInput.split(DELIMITER, SPLIT_POSITION);
        } else {
            inputTokens = new String[]{fullCommandInput, EMPTY_STRING};
        }
        return inputTokens;
    }

    /**
     * Creates a Command object based on the command word entered by user.
     *
     * @param commandWordInput The command word entered by user.
     * @return Command object created
     * @throws InvalidCommandException Exception when the command word is not supported by the application.
     */
    private static Command getCommand(String commandWordInput) throws InvalidCommandException{
        Command command = null;
        switch (commandWordInput.toUpperCase()) {
        case HelpCommand.COMMAND_WORD:
            command = new HelpCommand();
            break;
        case AddCommand.COMMAND_WORD:
            command = new AddCommand();
            break;
        case EditCommand.COMMAND_WORD:
            command = new EditCommand();
            break;
        case ListCommand.COMMAND_WORD:
            command = new ListCommand();
            break;
        case FindCommand.COMMAND_WORD:
            command = new FindCommand();
            break;
        case StatsCommand.COMMAND_WORD:
            command = new StatsCommand();
            break;
        case PurgeCommand.COMMAND_WORD:
            command = new PurgeCommand();
            break;
        case DeleteCommand.COMMAND_WORD:
            command = new DeleteCommand();
            break;
        case ByeCommand.COMMAND_WORD:
            command = new ByeCommand();
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;

    }
}
