package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.CreateCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.DeleteCommand;

import java.util.Arrays;

import seedu.duke.command.AddCommand;
import seedu.duke.command.CommandType;
import seedu.duke.command.FavouriteCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.command.Database;
import seedu.duke.exceptions.InvalidCommentException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidUserCommandException;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.exceptions.UniversityNotFoundException;
import seedu.duke.module.Module;
import seedu.duke.timetable.Lesson;
import seedu.duke.ui.Ui;

/**
 * Class to handle parsing user input and creating Command after checking for validity of user input.
 */
public class CommandParser {
    private static final String UNIVERSITY_PREFIX = "u/";
    private static final String MODULE_PREFIX = "m/";
    private static final String DAY_PREFIX = "d/";
    private static final String START_TIME_PREFIX = "st/";
    private static final String END_TIME_PREFIX = "en/";
    private static final String ADD_FAVOURITE_PREFIX = "add/";
    private static final String DELETE_FAVOURITE_PREFIX = "del/";
    private static final String COMMENT_PREFIX = "note/{";

    private static final String COMMENT_DELETE_PREFIX = "note/";
    private static final String VIEW_FAVOURITE_PREFIX = "VIEW";
    private static final String UNIVERSITIES_OPTION = "UNIVERSITIES";
    private static final String USER_LISTS_OPTION = "LISTS";
    private static final String MODULES_OPTION = "MODULES";
    private static final String DELETE_HISTORY_OPTION = "DELETE HISTORY";
    private static final String TIMETABLES_OPTION = "TIMETABLES";
    private static final int SIX_PARAMETERS_LENGTH = 6;
    private static final int FOUR_PARAMETERS_LENGTH = 4;
    private static final int THREE_PARAMETERS_LENGTH = 3;
    private static final int TWO_PARAMETERS_LENGTH = 2;
    private static final int ONE_PARAMETER_LENGTH = 1;
    private static final int COMMAND_INDEX = 0;
    private static final int UNIVERSITY_INDEX = 1;
    private static final int LIST_OPTION_INDEX = 1;
    private static final int FAVOURITE_OPTION_INDEX = 1;
    private static final int VIEW_OPTION_INDEX = 1;
    private static final int MODULE_INDEX = 2;
    private static final int DAY_INDEX = 3;
    private static final int LESSON_START_TIME_INDEX = 4;
    private static final int LESSON_END_TIME_INDEX = 5;
    private static final int COMMENT_INDEX = 3;
    private static final int NOTE_INDEX = 5;

    /**
     * Creates a user command based on user input.
     *
     * @param userInput A line of user input.
     * @return A Command corresponding to the type of action user wants to carry out
     *
     * @throws InvalidUserCommandException if the user command does not follow the command format laid out
     */
    public static Command getUserCommand(String userInput) throws InvalidUserCommandException,
            ModuleNotFoundException, InvalidModuleException, UniversityNotFoundException, InvalidCommentException {
        String[] userInputTokenized = parseUserCommand(userInput);
        if (isEmptyUserInput(userInputTokenized)) {
            throw new InvalidUserCommandException("Error: Missing command. "
                    + "Please follow the command format provided");
        }
        String userInputCommand = userInputTokenized[COMMAND_INDEX];
        switch (userInputCommand) {
        case "/exit":
            if (!isValidExitCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error: Invalid exit command. "
                        + "Please follow the command format provided");
            }
            ExitCommand newExitCommand = new ExitCommand(userInputTokenized, CommandType.EXIT);
            return newExitCommand;
        case "/help":
            if (!isValidHelpCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error: Invalid help command. "
                        + "Please follow the command format provided");
            }
            HelpCommand newHelpCommand = new HelpCommand(userInputTokenized, CommandType.HELP);
            return newHelpCommand;
        case "/create":
            if (!isValidCreateCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error: Invalid create command. "
                        + "Please follow the command format provided");
            }
            CreateCommand newCreateCommand = new CreateCommand(userInputTokenized, CommandType.CREATE);
            return newCreateCommand;
        case "/add":
            if (!isValidAddCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error: Invalid add command. "
                        + "Please follow the command format provided");
            }
            String comment = "";
            if (userInputTokenized.length == 4) {
                comment = userInputTokenized[3];
            }
            Lesson lessonToAdd = parseLesson(userInputTokenized);
            AddCommand newAddCommand = new AddCommand(userInputTokenized, CommandType.ADD, lessonToAdd, comment);

            return newAddCommand;
        case "/view":
            if (!isValidViewCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error: Invalid view command. "
                        + "Please follow the command format provided");
            }
            ViewCommand newViewCommand = new ViewCommand(userInputTokenized, CommandType.VIEW);
            return newViewCommand;
        case "/delete":
            if (!isValidDeleteCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error: Invalid delete command. "
                        + "Please follow the command format provided");
            }
            boolean isDeleteModule = (userInputTokenized.length == THREE_PARAMETERS_LENGTH
                                    || userInputTokenized.length == FOUR_PARAMETERS_LENGTH);
            Lesson lessonToDelete = parseLesson(userInputTokenized);
            DeleteCommand newDeleteCommand = new DeleteCommand(userInputTokenized, CommandType.DELETE,
                    isDeleteModule, lessonToDelete);
            return newDeleteCommand;
        case "/list":
            if (!isValidListCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error: Invalid list command. "
                        + "Please follow the command format provided");
            }
            ListCommand newListCommand = new ListCommand(userInputTokenized, CommandType.LIST);
            return newListCommand;
        case "/favourite":
            if (!isValidFavouriteCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error: Invalid favourite command. "
                        + "Please follow the command format provided");
            }
            FavouriteCommand newFavouriteCommand = new FavouriteCommand(userInputTokenized, CommandType.FAVOURITE);
            return newFavouriteCommand;
        default:
            throw new InvalidUserCommandException("Error: Unidentified command. "
                    + "Please follow the command format provided");
        }
    }

    /**
     * Creates a Lesson class from user input lesson timetable details.
     *
     * @param parameters User input split by spaces.
     * @return Lesson if the user input is a valid command on timetables, null otherwise.
     *
     * @throws ModuleNotFoundException if user input module code is not found in database
     * @throws InvalidModuleException if module details are invalid when passed into Lesson constructor
     */
    private static Lesson parseLesson(String[] parameters) throws ModuleNotFoundException, InvalidModuleException,
            UniversityNotFoundException {
        if (!isValidCommandOnTimetable(parameters)) {
            return null;
        } else {
            String code = parameters[MODULE_INDEX].substring(2);
            String universityName = parameters[UNIVERSITY_INDEX].substring(2);

            Module puModule = Database.findPuMapping(code, universityName).getPartnerUniversityModule();
            String day = parameters[DAY_INDEX].substring(2);
            String startTime = parameters[LESSON_START_TIME_INDEX].substring(3);
            String endTime = parameters[LESSON_END_TIME_INDEX].substring(3);
            return new Lesson(puModule.getCode(), puModule.getTitle(), puModule.getCredit(), puModule.getUniversity(),
                    day, startTime, endTime);
        }
    }

    /**
     * Checks whether a line of user input is empty.
     *
     * @param parameters User input split by spaces.
     * @return True if the UserInput is empty. False otherwise.
     */
    private static boolean isEmptyUserInput(String[] parameters) {
        return parameters.length < ONE_PARAMETER_LENGTH;
    }

    /**
     * Checks whether user input is a valid view command.
     *
     * @param parameters User input split by spaces.
     * @return True if user input is a valid view command. False otherwise.
     */
    private static boolean isValidViewCommand(String[] parameters) {
        return parameters.length == TWO_PARAMETERS_LENGTH && isValidViewOption(parameters[VIEW_OPTION_INDEX]);
    }

    /**
     * Checks whether user input option is a valid view command option.
     *
     * @param option User input option
     * @return True if user input option is a valid view command option. False otherwise.
     */
    private static boolean isValidViewOption(String option) {
        return option.trim().equals(USER_LISTS_OPTION) || option.startsWith(UNIVERSITY_PREFIX)
                || option.trim().equals(DELETE_HISTORY_OPTION) || option.trim().equals(TIMETABLES_OPTION);
    }

    /**
     * Checks whether user input is a valid add command.
     *
     * @param parameters User input split by spaces.
     * @return True if user input is a valid add command. False otherwise.
     */
    private static boolean isValidAddCommand(String[] parameters) {
        return isValidCommandOnModules(parameters) || isValidCommandOnTimetable(parameters)
                || isValidAddCommentOnModules(parameters);
    }

    /**
     * Checks whether user input is a valid exit command.
     *
     * @param parameters User input split by spaces.
     * @return True if user input is a valid exit command. False otherwise.
     */
    private static boolean isValidExitCommand(String[] parameters) {
        return parameters.length == ONE_PARAMETER_LENGTH;
    }

    /**
     * Checks whether user input is a valid help command.
     *
     * @param parameters User input split by spaces.
     * @return True if user input is a valid help command. False otherwise.
     */
    private static boolean isValidHelpCommand(String[] parameters) {
        return parameters.length == ONE_PARAMETER_LENGTH;
    }

    /**
     * Checks whether user input is a valid delete command.
     *
     * @param parameters User input split by spaces.
     * @return True if user input is a valid delete command. False otherwise.
     */
    private static boolean isValidDeleteCommand(String[] parameters) {
        return isValidCommandOnUniversity(parameters) || isValidCommandOnModules(parameters)
                || isValidCommandOnTimetable(parameters) || isValidDeleteCommandOnModules(parameters);
    }


    /**
     * Checks whether user input is a valid create command.
     *
     * @param parameters User input split by spaces.
     * @return True if user input is a valid create command. False otherwise.
     */
    private static boolean isValidCreateCommand(String[] parameters) {
        return isValidCommandOnUniversity(parameters);
    }

    /**
     * Checks whether user input is a valid list command.
     *
     * @param parameters User input split by spaces.
     * @return True if user input is a valid list command. False otherwise.
     */
    private static boolean isValidListCommand(String[] parameters) {
        return parameters.length == TWO_PARAMETERS_LENGTH
                && isValidListOption(parameters[LIST_OPTION_INDEX]);
    }

    /**
     * Checks whether user input option is a valid list command option.
     *
     * @param option User input option
     * @return True if user input option is a valid list command option. False otherwise.
     */
    private static boolean isValidListOption(String option) {
        return option.trim().equals(UNIVERSITIES_OPTION) || option.trim().equals(MODULES_OPTION)
                || option.startsWith(UNIVERSITY_PREFIX) || option.startsWith(MODULE_PREFIX);
    }

    /**
     * Checks whether user input is a valid favourite command.
     *
     * @param parameters User input split by spaces.
     * @return True if user input is a valid favourite command. False otherwise.
     */
    private static boolean isValidFavouriteCommand(String[] parameters) {
        return parameters.length == TWO_PARAMETERS_LENGTH
                && isValidFavouriteOption(parameters[FAVOURITE_OPTION_INDEX]);
    }

    /**
     * Checks whether user input option is a valid favourite command option.
     *
     * @param option User input option
     * @return True if user input option is a valid favourite command option. False otherwise.
     */
    private static boolean isValidFavouriteOption(String option) {
        return option.startsWith(ADD_FAVOURITE_PREFIX) || option.startsWith(DELETE_FAVOURITE_PREFIX)
                || option.startsWith(VIEW_FAVOURITE_PREFIX);
    }

    /**
     * Checks whether user input is a valid command on modules.
     *
     * @param parameters User input split by spaces.
     * @return True if user input is a valid command on modules. False otherwise.
     */
    private static boolean isValidCommandOnModules(String[] parameters) {
        return parameters.length == THREE_PARAMETERS_LENGTH
                && parameters[UNIVERSITY_INDEX].startsWith(UNIVERSITY_PREFIX)
                && parameters[MODULE_INDEX].startsWith(MODULE_PREFIX);
    }

    /**
     * Checks whether user input is a valid command on universities.
     *
     * @param parameters User input split by spaces.
     * @return True if user input is a valid command on universities. False otherwise.
     */
    private static boolean isValidCommandOnUniversity(String[] parameters) {
        return parameters.length == TWO_PARAMETERS_LENGTH
                && parameters[UNIVERSITY_INDEX].startsWith(UNIVERSITY_PREFIX);
    }

    /**
     * Checks whether user input is a valid command on timetables.
     *
     * @param parameters User input split by spaces.
     * @return True if user input is a valid command on timetables. False otherwise.
     */
    private static boolean isValidCommandOnTimetable(String[] parameters) {
        return parameters.length == SIX_PARAMETERS_LENGTH
                && parameters[UNIVERSITY_INDEX].startsWith(UNIVERSITY_PREFIX)
                && parameters[MODULE_INDEX].startsWith(MODULE_PREFIX)
                && parameters[DAY_INDEX].startsWith(DAY_PREFIX)
                && parameters[LESSON_START_TIME_INDEX].startsWith(START_TIME_PREFIX)
                && parameters[LESSON_END_TIME_INDEX].startsWith(END_TIME_PREFIX);
    }

    private static boolean isValidAddCommentOnModules(String [] parameters) {
        return parameters.length == FOUR_PARAMETERS_LENGTH
                && parameters[UNIVERSITY_INDEX].startsWith(UNIVERSITY_PREFIX)
                && parameters[MODULE_INDEX].startsWith(MODULE_PREFIX)
                && parameters[COMMENT_INDEX].startsWith(COMMENT_PREFIX)
                && parameters[COMMENT_INDEX].charAt(parameters[COMMENT_INDEX].length() - 1) == '}';
    }

    private static boolean isValidDeleteCommandOnModules(String [] parameters) {
        return parameters.length == FOUR_PARAMETERS_LENGTH
                && parameters[UNIVERSITY_INDEX].startsWith(UNIVERSITY_PREFIX)
                && parameters[MODULE_INDEX].startsWith(MODULE_PREFIX)
                && parameters[COMMENT_INDEX].startsWith(COMMENT_DELETE_PREFIX);
    }

    /**
     * Split user input by spaces and process each parameter by removing underscores.
     *
     * @param userInput A line of user input.
     * @return An array of user input parameters split by spaces with underscores removed.
     */
    private static String[] parseUserCommand(String userInput) {
        if (userInput.contains("note/{") && userInput.charAt(userInput.length() - 1) == '}') {
            return parseUserCommandWithComments(userInput);
        }
        return parseUserCommandWithoutComments(userInput);
    }

    private static String[] parseUserCommandWithoutComments(String userInput) {
        String[] userInputTokenized = userInput.split(" +");
        for (int i = 0; i < userInputTokenized.length; i++) {
            userInputTokenized[i] = removeParameterUnderscores(userInputTokenized[i]);
        }
        return userInputTokenized;
    }

    private static String[] parseUserCommandWithComments(String userInput) {
        int index =  getParametersBeforeComment(userInput);
        String before = userInput.substring(0, index);
        String after = userInput.substring(index);
        String[] userInputTokenized = before.split(" +");
        for (int i = 0; i < userInputTokenized.length; i++) {
            userInputTokenized[i] = removeParameterUnderscores(userInputTokenized[i]);
        }
        String [] allTokens = Arrays.copyOf(userInputTokenized, userInputTokenized.length + 1);
        allTokens[userInputTokenized.length] = after;
        return allTokens;
    }

    /**
     * Removes underscores from user input parameter.
     *
     * @param parameter User input.
     * @return User input with underscores removed.
     */
    private static String removeParameterUnderscores(String parameter) {
        if (parameter.startsWith("_") || parameter.endsWith("_")) {
            return "ERROR";
        }
        return parameter.replace("_", " ");
    }

    private static int getParametersBeforeComment(String parameter) {
        int start = 0;
        String temp = "";
        for (int i = 0; i < parameter.length() - 6; ++i) {
            temp = parameter.substring(i, i +   6);
            if (temp.equals("note/{")) {
                start = i;
            }
        }
        return start;
    }
}
