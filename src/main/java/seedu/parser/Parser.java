package seedu.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.api.Api;
import seedu.commands.AuthCommand;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.FavouriteCommand;
import seedu.commands.FilterCommand;
import seedu.commands.FindCommand;
import seedu.commands.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.ListCommand;
import seedu.commands.UnfavouriteCommand;
import seedu.commands.UpdateCommand;
import seedu.common.CommonData;
import seedu.data.CarparkList;
import seedu.files.Favourite;
import seedu.parser.search.Sentence;

/**
 * Class to deal with parsing commands.
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String EMPTY_RESPONSE_HEADER = "Empty argument. Valid command(s): \n";

    private CarparkList carparkList;
    private Api api;
    private Favourite favourite;
    private ArrayList<String> favouriteList;

    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string
     * @param api api of the carpark data
     * @param carparkList carpark List
     * @return the command based on user input
     */
    public Command parseCommand(String input, Api api, CarparkList carparkList, Favourite favourite) {
        this.api = api;
        this.carparkList = carparkList;
        this.favourite = favourite;
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new InvalidCommand("Invalid Command");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AuthCommand.COMMAND_WORD:
            if (arguments.trim().isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.AUTH_FORMAT);
            }
            return prepareAuth(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case FavouriteCommand.COMMAND_WORD:
            if (arguments.trim().isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FAVOURITE_FORMAT);
            }
            return prepareFavourite(arguments);
        case FindCommand.COMMAND_WORD:
            if (arguments.trim().isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FIND_FORMAT);
            }
            return prepareFind(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand(carparkList);
        case FilterCommand.COMMAND_WORD:
            if (arguments.trim().isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FILTER_FORMAT);
            }
            return prepareFilter(arguments);
        case UpdateCommand.COMMAND_WORD:
            return new UpdateCommand(api, carparkList);
        case UnfavouriteCommand.COMMAND_WORD:
            if (arguments.trim().isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.UNFAVOURITE_FORMAT);
            }
            return prepareUnfavourite(arguments);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
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

    /**
     * To prepare the arguments to be taken in for Favourite Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareFavourite(String arguments) {
        final String carparkID = arguments.trim();
        return new FavouriteCommand(carparkID, favourite, carparkList);
    }

    /**
     * To prepare the arguments to be taken in for unfavourite Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareUnfavourite(String arguments) {
        final String carparkID = arguments.trim();
        return new UnfavouriteCommand(carparkID, favourite, carparkList);
    }

    /**
     * To prepare the arguments to be taken in for Find Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareFind(String arguments) {
        final String carparkID = arguments.trim();
        return new FindCommand(carparkID, carparkList);
    }

    /**
     * To prepare the arguments to be taken in for Search Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareFilter(String arguments) {
        Sentence searchQuery = new Sentence(arguments);
        return new FilterCommand(carparkList, searchQuery);
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
