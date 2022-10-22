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
            return new AuthCommand(api);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
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
    private Command prepareFind(String arguments) {
        final String carparkID = arguments.trim();
        return new FindCommand(carparkID);
    }

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


////-----------------------------------------------------------------------------------------------------------------------------------------------
//    private static final String COMMAND_FIND = "find";
//    private static final String COMMAND_SEARCH = "search";
//    private static final String COMMAND_EXIT = "exit";
//    private static final String COMMAND_UPDATE = "update";
//    private static final String COMMAND_AUTH = "auth";
//    private static final String COMMAND_LIST = "list";
//    private static final String COMMAND_FAVOURITE = "favourite";
//    private static final String COMMAND_UNFAVOURITE = "unfavourite";
//
//    /**
//     * To convert the user input into commands for the program.
//     *
//     * @param input User input
//     * @return Command that user wants to do.
//     */
//    public Command parseInputString(String input) throws NoCommandArgumentException, UnneededArgumentsException {
//        Command command;
//        boolean hasCommandArgumentFlag = hasCommandArguments(input);
//        if (input.equals(COMMAND_EXIT)) {
//            command = Command.EXIT;
//        } else {
//            String instruction = input.trim().split("\\s+")[0];
//            switch (instruction) {
//            case COMMAND_FIND:
//                if (!hasCommandArgumentFlag) {
//                    throw new NoCommandArgumentException("find");
//                }
//                command = Command.FIND;
//                break;
//            case COMMAND_UPDATE:
//                if (hasCommandArgumentFlag) {
//                    throw new UnneededArgumentsException("update");
//                }
//                command = Command.UPDATE;
//                break;
//            case COMMAND_AUTH:
//                if (!hasCommandArgumentFlag) {
//                    throw new NoCommandArgumentException("auth");
//                }
//                command = Command.AUTH;
//                break;
//            case COMMAND_LIST:
//                if (hasCommandArgumentFlag) {
//                    throw new UnneededArgumentsException("list");
//                }
//                command = Command.LIST;
//                break;
//            case COMMAND_SEARCH:
//                if (!hasCommandArgumentFlag) {
//                    throw new NoCommandArgumentException("search");
//                }
//                command = Command.SEARCH;
//                break;
//            case COMMAND_FAVOURITE:
//                if (!hasCommandArgumentFlag) {
//                    throw new NoCommandArgumentException("favourite");
//                }
//                command = Command.FAVOURITE;
//                break;
//            case COMMAND_UNFAVOURITE:
//                if (!hasCommandArgumentFlag) {
//                    throw new NoCommandArgumentException("unfavourite");
//                }
//                command = Command.UNFAVOURITE;
//                break;
//            default:
//                command = Command.INVALID;
//                break;
//            }
//        }
//        return command;
//    }
//

//
//}