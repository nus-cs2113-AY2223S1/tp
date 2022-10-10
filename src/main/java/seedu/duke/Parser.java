package seedu.duke;

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

import static seedu.duke.common.Utilities.splitInput;

public class Parser {
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
        String input = inputTokens[1];

        switch (inputTokens[0].toUpperCase()) {
        case HelpCommand.COMMAND_WORD:
            command = new HelpCommand(input);
            break;
        case AddCommand.COMMAND_WORD:
            command = new AddCommand(input);
            break;
        case EditCommand.COMMAND_WORD:
            command = new EditCommand(input);
            break;
        case ListCommand.COMMAND_WORD:
            command = new ListCommand(input);
            break;
        case FindCommand.COMMAND_WORD:
            command = new FindCommand(input);
            break;
        case StatsCommand.COMMAND_WORD:
            command = new StatsCommand(input);
            break;
        case PurgeCommand.COMMAND_WORD:
            command = new PurgeCommand();
            break;
        case DeleteCommand.COMMAND_WORD:
            command = new DeleteCommand(input);
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
