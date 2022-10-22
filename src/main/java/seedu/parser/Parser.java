package seedu.parser;

import seedu.api.Api;
import seedu.commands.*;
import seedu.data.CarparkList;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCommandArgumentException;
import seedu.exception.UnneededArgumentsException;
import seedu.parser.search.Sentence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class to deal with parsing commands.
 */
public class Parser {

    private CarparkList carparkList;
    private Api api;

    /**
     * Used for the initial separation of command word and arguments
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern KEYWORDS_ARGS_FORMAT = Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)");

    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string
     * @param api api of the carpark data
     * @param carparkList carpark List
     * @return the command based on user input
     */
    public Command parseCommand(String input, Api api, CarparkList carparkList) {
        this.api = api;
        this.carparkList = carparkList;
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new InvalidCommand("Invalid Command");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AuthCommand.COMMAND_WORD:
            return prepareAuth(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case FavouriteCommand.COMMAND_WORD:
            return prepareFavourite(arguments);
        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand(carparkList);
        case SearchCommand.COMMAND_WORD:
            return prepareSearch(arguments);
        case UpdateCommand.COMMAND_WORD:
            return new UpdateCommand(api);
        default:
            return new InvalidCommand("Invalid Command");
        }
    }

    /**
     * To prepare the arguments to be taken in for Auth Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareAuth(String arguments) {
        final String apiKey = arguments.trim();
        return new AuthCommand(api, apiKey);
    }

    private Command prepareFavourite(String arguments) {
        return new FavouriteCommand();
    }

    /**
     * To prepare the arguments to be taken in for Find Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareFind(String arguments) {
        final String carparkID = arguments.trim();
        return new FindCommand(carparkID);
    }

    /**
     * To prepare the arguments to be taken in for Search Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareSearch(String arguments) {
        Sentence searchQuery = new Sentence(arguments);
        return new SearchCommand(carparkList, searchQuery);
    }


    /**
     * Check number of words in string and see if there are arguments.
     *
     * @param input input string to check
     * @return If arguments are present, return true. If not, return false
     */
    public boolean hasCommandArguments(String input) {
        String[] words = input.trim().split("\\s+");
        return words.length > 1;
    }

    public static String[] splitCommandArgument(String input) {
        return input.split("\\s+", 2);
    }
}