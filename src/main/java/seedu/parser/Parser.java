package seedu.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.api.Api;
import seedu.commands.AuthCommand;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.FavouriteCommand;
import seedu.commands.FilterAddressCommand;
import seedu.commands.FilterCarparkIdCommand;
import seedu.commands.FilterCommand;
import seedu.commands.FindCommand;
import seedu.commands.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.ListCommand;
import seedu.commands.UnfavouriteCommand;
import seedu.commands.UpdateCommand;
import seedu.common.CommonData;
import seedu.data.CarparkList;
import seedu.exception.DashedArgumentsNotInFrontException;
import seedu.exception.UnneededArgumentsException;
import seedu.files.Favourite;
import seedu.parser.search.Arguments;
import seedu.parser.search.Sentence;

/**
 * Class to deal with parsing commands.
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String EMPTY_RESPONSE_HEADER = "Empty argument. Valid command(s): \n";
    private static final String INVALID_NUMBER_OF_ARGS_HEADER = "This command only takes exactly %s argument(s). Valid "
        + "command(s): \n";
    private static final String TOO_MANY_DASHED_ARGS_HEADER = "This command only takes exactly %s dashed argument(s)"
        + ".\n";

    private CarparkList carparkList;
    private Api api;
    private Favourite favourite;

    /**
     * Parses user input into command for execution.
     *
     * @param input       full user input string
     * @param api         api of the carpark data
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

        final String commandWord = matcher.group("commandWord").trim();
        final Arguments argsList;
        final String arguments;
        try {
            argsList = new Arguments(matcher.group("arguments").trim());
            arguments = argsList.getArguments().toString();
        } catch (DashedArgumentsNotInFrontException e) {
            return new InvalidCommand(e.getMessage());
        }

        if (commandWord.equalsIgnoreCase(AuthCommand.COMMAND_WORD)
                || commandWord.equalsIgnoreCase(AuthCommand.COMMAND_WORD_SHORT)) {
            if (arguments.isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.AUTH_FORMAT);
            } else if (numberOfArguments(arguments) != AuthCommand.NUMBER_OF_ARGUMENTS) {
                return new InvalidCommand(String.format(INVALID_NUMBER_OF_ARGS_HEADER,
                        AuthCommand.NUMBER_OF_ARGUMENTS) + CommonData.FAVOURITE_FORMAT);
            }
            return prepareAuth(arguments);
        } else if (commandWord.equalsIgnoreCase(ExitCommand.COMMAND_WORD)
                || commandWord.equalsIgnoreCase(ExitCommand.COMMAND_WORD_SHORT)) {
            return prepareExit(arguments);
        } else if (commandWord.equalsIgnoreCase(FavouriteCommand.COMMAND_WORD)
                || commandWord.equalsIgnoreCase(FavouriteCommand.COMMAND_WORD_SHORT)) {
            if (arguments.isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FAVOURITE_FORMAT);
            } else if (numberOfArguments(arguments) != FavouriteCommand.NUMBER_OF_ARGUMENTS) {
                return new InvalidCommand(String.format(INVALID_NUMBER_OF_ARGS_HEADER,
                        FavouriteCommand.NUMBER_OF_ARGUMENTS) + CommonData.FAVOURITE_FORMAT);
            }
            return prepareFavourite(arguments);
        } else if (commandWord.equalsIgnoreCase(FindCommand.COMMAND_WORD)
                || commandWord.equalsIgnoreCase(FindCommand.COMMAND_WORD_SHORT)) {
            if (arguments.isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FIND_FORMAT);
            } else if (numberOfArguments(arguments) != FindCommand.NUMBER_OF_ARGUMENTS) {
                return new InvalidCommand(String.format(INVALID_NUMBER_OF_ARGS_HEADER,
                        FindCommand.NUMBER_OF_ARGUMENTS) + CommonData.FIND_FORMAT);
            }
            return prepareFind(arguments);
        } else if (commandWord.equalsIgnoreCase(ListCommand.COMMAND_WORD)
                || commandWord.equalsIgnoreCase(ListCommand.COMMAND_WORD_SHORT)) {
            return prepareList(arguments);
        } else if (commandWord.equalsIgnoreCase(FilterCommand.COMMAND_WORD)
                || commandWord.equalsIgnoreCase(FilterCommand.COMMAND_WORD_SHORT)) {
            String dashedCommand;
            Sentence actualArgument = argsList.getArguments();
            if (argsList.getDashedArgsCount() == 1) {
                dashedCommand = argsList.getDashedArgs().get(0);
                if (dashedCommand.equalsIgnoreCase("i")) {
                    if (actualArgument.getWordCount() == 0) {
                        return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FILTER_FORMAT);
                    }
                    return prepareFilterCarparkId(actualArgument);
                } else if (dashedCommand.equalsIgnoreCase("a")) {
                    if (actualArgument.getWordCount() == 0) {
                        return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FILTER_FORMAT);
                    }
                    return prepareFilterAddress(actualArgument);
                }
            } else if (argsList.getDashedArgsCount() > 1) {
                return new InvalidCommand(String.format(TOO_MANY_DASHED_ARGS_HEADER, 1)
                    + CommonData.FILTER_FORMAT);
            } else {
                if (actualArgument.getWordCount() == 0) {
                    return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FILTER_FORMAT);
                }
                return prepareFilterAddress(actualArgument);
            }
            if (arguments.isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.FILTER_FORMAT);
            }
            return prepareFilter(arguments);
        } else if (commandWord.equalsIgnoreCase(UpdateCommand.COMMAND_WORD.trim())
                || commandWord.equalsIgnoreCase(UpdateCommand.COMMAND_WORD_SHORT.trim())) {
            return prepareUpdate(arguments);
        } else if (commandWord.equalsIgnoreCase(UnfavouriteCommand.COMMAND_WORD)
                || commandWord.equalsIgnoreCase(UnfavouriteCommand.COMMAND_WORD_SHORT)) {
            if (arguments.isEmpty()) {
                return new InvalidCommand(EMPTY_RESPONSE_HEADER + CommonData.UNFAVOURITE_FORMAT);
            }
            return prepareUnfavourite(arguments);
        } else if (commandWord.equalsIgnoreCase(HelpCommand.COMMAND_WORD)
                || commandWord.equalsIgnoreCase(HelpCommand.COMMAND_WORD_SHORT)) {
            return prepareHelp(arguments);
        } else {
            return new InvalidCommand("Invalid Command. ");
        }
    }

    /**
     * To check that user does not input in any parameters for ExitCommand.
     *
     * @param arguments arguments that may be given by the user after the command word
     * @return Command to be carried out
     */
    private Command prepareExit(String arguments) {
        try {
            if (arguments.trim().isEmpty()) {
                return new ExitCommand();
            }
            throw new UnneededArgumentsException("exit");
        } catch (UnneededArgumentsException e) {
            return new InvalidCommand(e.getMessage());
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
     * To check that user does not input in any parameters for ListCommand.
     *
     * @param arguments arguments that may be given by the user after the command word
     * @return Command to be carried out
     */
    private Command prepareList(String arguments) {
        try {
            if (arguments.trim().isEmpty()) {
                return new ListCommand(carparkList);
            }
            throw new UnneededArgumentsException("list");
        } catch (UnneededArgumentsException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareFilter(String arguments) {
        Sentence searchQuery = new Sentence(arguments);
        return new FilterCommand(carparkList, searchQuery);
    }

    /**
     * To prepare the arguments to be taken in for Search Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareFilterAddress(Sentence searchQuery) {
        return new FilterAddressCommand(carparkList, searchQuery);
    }

    /**
     * To prepare the arguments to be taken in for Search Command.
     *
     * @param arguments arguments given by the user after the command word
     * @return command to be carried out
     */
    private Command prepareFilterCarparkId(Sentence searchQuery) {
        return new FilterCarparkIdCommand(carparkList, searchQuery);
    }

    /**
     * To check that user does not input in any parameters for UpdateCommand.
     *
     * @param arguments arguments that may be given by the user after the command word
     * @return Command to be carried out
     */
    private Command prepareUpdate(String arguments) {
        try {
            if (arguments.trim().isEmpty()) {
                return new UpdateCommand(api, carparkList);
            }
            throw new UnneededArgumentsException("update");
        } catch (UnneededArgumentsException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * To check that user does not input in any parameters for HelpCommand.
     *
     * @param arguments arguments that may be given by the user after the command word
     * @return Command to be carried out
     */
    private Command prepareHelp(String arguments) {
        try {
            if (arguments.trim().isEmpty()) {
                return new HelpCommand();
            }
            throw new UnneededArgumentsException("help");
        } catch (UnneededArgumentsException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Returns number of arguments separated by a space.
     *
     * @param input input string to check
     * @return return the number of space-separated words
     */
    public int numberOfArguments(String input) {
        String[] words = input.trim().split("\\s+");
        return words.length;
    }

    public static String[] splitCommandArgument(String input) {
        return input.split("\\s+", 2);
    }
}
