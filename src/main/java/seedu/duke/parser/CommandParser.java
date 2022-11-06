package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.BudgetCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.StatsCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.PurgeCommand;

import seedu.duke.exception.MoolahException;
import seedu.duke.exception.GlobalInvalidCommandException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a parser that parses the user input into a Command object ready for execution.
 *
 * <p>The CommandParser will check that the user input calls a valid command,
 * then create the corresponding command object and call ParameterParser to further parse the parameter portion
 * of the user input and set the parameters inside the command object,
 * such that the command object will be ready for an execution.
 */
public class CommandParser {
    //@@author chydarren
    private static final String DELIMITER = " ";
    private static final int SPLIT_POSITION = 2;
    private static final Logger parserLogger = Logger.getLogger(ParameterParser.class.getName());

    //@@author wcwy

    /**
     * Parses the user input into Command class based on the command word.
     *
     * @param fullCommandInput A line of input entered by the user.
     * @return A valid parsed command object containing all required information for execution.
     * @throws MoolahException Any command input exceptions captured by Moolah Manager.
     */
    public static Command parse(String fullCommandInput) throws MoolahException {
        assert fullCommandInput != null : "Full command input received must not be null!";
        Command command;
        String[] inputTokens = splitInput(fullCommandInput);

        assert inputTokens.length == 2;
        String commandWordInput = inputTokens[0];
        String parametersInput = inputTokens[1];

        // Parses the command word from user input
        command = getCommand(commandWordInput, parametersInput);

        // Parses the parameters from user input to set up the parameters for the command
        assert command != null : "Command variable must not be null after a valid getCommand() call!";
        ParameterParser.parse(command, parametersInput);

        return command;
    }

    //@@author chydarren

    /**
     * Splits the user input into two parts, i.e. the command word and the parameter(s).
     *
     * @param fullCommandInput A line of input entered by the user.
     * @return A string array of input tokens.
     */
    public static String[] splitInput(String fullCommandInput) {
        String[] inputTokens;
        // Separates the command word and the parameters into an array of size two
        if (fullCommandInput.contains(DELIMITER)) {
            inputTokens = fullCommandInput.split(DELIMITER, SPLIT_POSITION);
        } else {
            inputTokens = new String[]{fullCommandInput, ""};
        }
        return inputTokens;
    }

    //@@author paullowse

    /**
     * Creates a Command object based on the command word entered by user.
     *
     * @param commandWordInput The command word entered by user.
     * @return Command object created.
     * @throws GlobalInvalidCommandException If the command word is not supported by the application.
     */
    public static Command getCommand(String commandWordInput, String parameterInput) throws
            GlobalInvalidCommandException {
        parserLogger.setLevel(Level.SEVERE);
        Command command;
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
        case BudgetCommand.COMMAND_WORD:
            command = new BudgetCommand();
            break;
        case ByeCommand.COMMAND_WORD:
            command = new ByeCommand();
            break;
        default:
            parserLogger.log(Level.WARNING, "An invalid command error is caught in this command");
            throw new GlobalInvalidCommandException();
        }
        return command;
    }
}
