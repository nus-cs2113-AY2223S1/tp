package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.CommandType;
import seedu.duke.command.CreateCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.FavouriteCommand;
import seedu.duke.exceptions.InvalidUserCommandException;

public class CommandParser {
    private static final int THREE_PARAMETERS_LENGTH = 3;
    private static final int TWO_PARAMETERS_LENGTH = 2;
    private static final int ONE_PARAMETER_LENGTH = 1;
    private static final int COMMAND_INDEX = 0;
    private static final int UNIVERSITY_INDEX = 1;
    private static final int LIST_OPTION_INDEX = 1;
    private static final int FAVORITE_OPTION_INDEX = 1;
    private static final int VIEW_OPTION_INDEX = 1;
    private static final int MODULE_INDEX = 2;

    public static Command getUserCommand(String userInput) throws InvalidUserCommandException {
        String[] userInputTokenized = parseUserCommand(userInput);
        if (isEmptyUserInput(userInputTokenized)) {
            throw new InvalidUserCommandException("Error! Missing command. "
                    + "Please follow the command format provided!");
        }
        String userInputCommand = userInputTokenized[COMMAND_INDEX];
        switch (userInputCommand) {
        case "/exit":
            if (!isValidExitCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid exit command. "
                        + "Please follow the command format provided");
            }
            ExitCommand newExitCommand = new ExitCommand(userInputTokenized, CommandType.EXIT);
            return newExitCommand;
        case "/help":
            if (!isValidHelpCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid help command. "
                        + "Please follow the command format provided");
            }
            HelpCommand newHelpCommand = new HelpCommand(userInputTokenized, CommandType.HELP);
            return newHelpCommand;
        case "/create":
            if (!isValidCreateCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid create command. "
                        + "Please follow the command format provided");
            }
            CreateCommand newCreateCommand = new CreateCommand(userInputTokenized, CommandType.CREATE);
            return newCreateCommand;
        case "/add":
            if (!isValidAddCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid add command. "
                        + "Please follow the command format provided");
            }
            boolean isAddModuleCommand = userInputTokenized.length == THREE_PARAMETERS_LENGTH;
            if (isAddModuleCommand) {
                userInputTokenized[MODULE_INDEX] = removeParameterUnderscores(userInputTokenized[MODULE_INDEX]);
            }
            AddCommand newAddCommand = new AddCommand(userInputTokenized, CommandType.ADD);
            return newAddCommand;
        case "/view":
            if (!isValidViewCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid view command. "
                        + "Please follow the command format provided");
            }
            ViewCommand newViewCommand = new ViewCommand(userInputTokenized, CommandType.VIEW);
            return newViewCommand;
        case "/delete":
            if (!isValidDeleteCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid delete command. "
                        + "Please follow the command format provided");
            }
            boolean isDeleteModule = userInputTokenized.length == THREE_PARAMETERS_LENGTH;
            DeleteCommand newDeleteCommand = new DeleteCommand(userInputTokenized, CommandType.DELETE,
                    isDeleteModule);
            return newDeleteCommand;
        case "/list":
            if (!isValidListCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid list command. "
                        + "Please follow the command format provided");
            }
            ListCommand newListCommand = new ListCommand(userInputTokenized, CommandType.LIST);
            return newListCommand;
        case "/favourite":
            if (!isValidFavouriteCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid favourite command. "
                        + "Please follow the command format provided");
            }
            FavouriteCommand newFavouriteCommand = new FavouriteCommand(userInputTokenized, CommandType.FAVOURITE);
            return newFavouriteCommand;
        default:
            throw new InvalidUserCommandException("Error! Unidentified command. "
                    + "Please follow the command format provided!");
        }
    }

    private static String removeParameterUnderscores(String parameter) {
        return parameter.replace("_", " ");
    }

    private static boolean isEmptyUserInput(String[] userInputTokenized) {
        return userInputTokenized.length < ONE_PARAMETER_LENGTH;
    }

    private static boolean isValidViewCommand(String[] parameters) {
        return parameters.length == TWO_PARAMETERS_LENGTH && isValidViewOptionIndex(parameters[VIEW_OPTION_INDEX]);
    }

    private static boolean isValidViewOptionIndex(String option) {
        return option.trim().equals("LISTS") || option.startsWith("u/") || option.trim().equals("DELETE_HISTORY");
    }

    private static boolean isValidAddCommand(String[] parameters) {
        return isValidCommandOnModules(parameters);
    }

    private static boolean isValidExitCommand(String[] parameters) {
        return parameters.length == ONE_PARAMETER_LENGTH;
    }

    private static boolean isValidHelpCommand(String[] parameters) {
        return parameters.length == ONE_PARAMETER_LENGTH;
    }

    private static boolean isValidDeleteCommand(String[] parameters) {
        return isValidCommandOnUniversity(parameters) || isValidCommandOnModules(parameters);
    }


    private static boolean isValidCreateCommand(String[] parameters) {
        return isValidCommandOnUniversity(parameters);
    }

    private static boolean isValidListCommand(String[] parameters) {
        return parameters.length == TWO_PARAMETERS_LENGTH
                && isValidListOption(parameters[LIST_OPTION_INDEX]);
    }

    private static boolean isValidListOption(String option) {
        return option.trim().equals("UNIVERSITIES") || option.trim().equals("MODULES")
                || option.startsWith("u/") || option.startsWith("m/");
    }

    private static boolean isValidFavouriteCommand(String[] parameters) {
        return parameters.length == TWO_PARAMETERS_LENGTH
                && isValidFavouriteOption(parameters[FAVORITE_OPTION_INDEX]);
    }

    private static boolean isValidFavouriteOption(String option) {
        return option.startsWith("add/") || option.startsWith("del/") || option.startsWith("view/");
    }
    private static boolean isValidCommandOnModules(String[] parameters) {
        return parameters.length == THREE_PARAMETERS_LENGTH && parameters[UNIVERSITY_INDEX].startsWith("u/")
                && parameters[MODULE_INDEX].startsWith("m/");
    }

    private static boolean isValidCommandOnUniversity(String[] parameters) {
        return parameters.length == TWO_PARAMETERS_LENGTH && parameters[UNIVERSITY_INDEX].startsWith("u/");
    }

    private static String[] parseUserCommand(String userInput) {
        String[] userInputTokenized = userInput.split(" +");
        for (int i = 0; i < userInputTokenized.length; i++) {
            userInputTokenized[i] = removeParameterUnderscores(userInputTokenized[i]);
        }
        return userInputTokenized;
    }
}
