package seedu.duke.parser;

import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.Command;
import seedu.duke.command.RemoveModuleCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.InfoCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.TimetableCommand;
import seedu.duke.command.SelectSlotCommand;
import seedu.duke.command.SelectSemesterCommand;
import seedu.duke.command.SearchModuleCommand;
import seedu.duke.command.ImportCommand;
import seedu.duke.command.ExportCommand;
import seedu.duke.exceptions.YamomException;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the analysing of user input into logical syntactical components that is understood by the application.
 */
public class Parser {
    private static final String ERROR_EMPTY_INPUT = "Please enter a command! "
            + System.lineSeparator() + "Enter \"help\" to see all available commands in YAMOM!";

    /**
     * Analyzes based on the first word which is supposed to be a keyword specifying the
     * feature that the user wants to call upon. Converts user input to lower case so the
     * parser will be case-insensitive.
     *
     * @param userInput The whole <code>String</code> that the user entered.
     * @return The corresponding <code>Command</code>.
     * @throws YamomException Contains a string that specifies the type of incorrect user input.
     */
    public static Command parse(String userInput) throws YamomException {
        userInput = userInput.toLowerCase();
        String[] keywords = userInput.split("\\s+");

        if (isEmptyInput(userInput)) {
            throw new YamomException(ERROR_EMPTY_INPUT);
        }

        Command toExecute;
        switch (keywords[0]) {
        case (SearchModuleCommand.COMMAND_WORD):
            toExecute = new SearchModuleCommand(userInput);
            break;
        case (InfoCommand.COMMAND_WORD):
            toExecute = new InfoCommand(keywords);
            break;
        case (AddModuleCommand.COMMAND_WORD):
            toExecute = new AddModuleCommand(keywords);
            break;
        case (RemoveModuleCommand.COMMAND_WORD):
            toExecute = new RemoveModuleCommand(keywords);
            break;
        case (TimetableCommand.COMMAND_WORD):
            toExecute = new TimetableCommand(userInput);
            break;
        case (HelpCommand.COMMAND_WORD):
            toExecute = new HelpCommand(keywords);
            break;
        case (SelectSlotCommand.COMMAND_WORD):
            toExecute = new SelectSlotCommand(userInput);
            break;
        case (ByeCommand.COMMAND_WORD):
            toExecute = new ByeCommand(keywords);
            break;
        case (SelectSemesterCommand.COMMAND_WORD):
            toExecute = new SelectSemesterCommand(keywords);
            break;
        case (ExportCommand.COMMAND_WORD):
            toExecute = new ExportCommand(keywords);
            break;
        case (ImportCommand.COMMAND_WORD):
            toExecute = new ImportCommand(keywords);
            break;
        case (ListCommand.COMMAND_WORD):
            toExecute = new ListCommand(keywords);
            break;
        default:
            throw new YamomException("Cannot process the command");
        }
        return toExecute;
    }

    /**
     * Checks to see if the user supplied moduleCode is a valid module code.
     *
     * @param moduleCode The user supplied moduleCode.
     * @return If the user supplied a valid module code.
     */
    public static boolean isModuleCode(String moduleCode) {
        Pattern pattern = Pattern.compile("[a-z]{2,3}\\d{4}[a-z]?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(moduleCode);
        return (matcher.find() && matcher.group().length() == moduleCode.length());
    }

    public static boolean isTwoWordsCommand(String[] keywords) {
        return keywords.length == 2;
    }

    public static boolean isOneWordCommand(String[] keywords) {
        return keywords.length == 1;
    }

    public static boolean isMultiWordsCommand(String[] keywords) {
        return keywords.length > 2;
    }

    public static boolean isValidTwoWordCommand(String[] keywords) {
        return isTwoWordsCommand(keywords) && isModuleCode(keywords[1]);
    }

    private static boolean isEmptyInput(String command) {
        return command.isBlank() || command.isEmpty();
    }

    /**
     * Checks if the user entered an invalid module related command. Different error messages
     * will be thrown for different invalid commands.
     * 
     * @param keywords            Contains the user input split by spaces.
     * @param initialErrorMessage Indicates the proper format of the command.
     * @throws YamomException Contains a string that specifies the type of incorrect user input.
     */
    public static void moduleRelatedCommandError(String[] keywords, String initialErrorMessage)
            throws YamomException {
        if (isValidTwoWordCommand(keywords)) {
            return;
        }
        String errorMessage = initialErrorMessage + System.lineSeparator();
        if (isOneWordCommand(keywords)) {
            errorMessage += "Your command is incomplete.";
        } else if (isTwoWordsCommand(keywords) && !isModuleCode(keywords[1])) {
            errorMessage += "Module is invalid! "
                    + "Please enter a valid module code." + System.lineSeparator()
                    + "Each module of study has a unique module code consisting of a two- " + System.lineSeparator()
                    + "or three-letter prefix that generally denotes the discipline," + System.lineSeparator()
                    + "and four digits, the first of which indicates the level of the module" + System.lineSeparator()
                    + "(e.g., 1000 indicates a Level 1 module and 2000, a Level 2 module).";
        } else {
            errorMessage += "Unknown command, try again.";
        }

        throw new YamomException(errorMessage);
    }

    public static void singleWordCommandError(String[] keywords) throws YamomException {
        if (!isOneWordCommand(keywords)) {
            throw new YamomException("0 arguments expected");
        }
    }

    /**
     * Check if user entered a valid semester for semester related commands.
     * The semester validity is based on the academic schedule at National University of Singapore
     *
     * @param keywords Contains the user input split by spaces
     * @return if the user entered a valid semester
     */
    public static boolean isValidSemester(String[] keywords) {
        try {
            int semesterInput = Integer.parseInt(keywords[1]);
            return semesterInput > 0 && semesterInput <= 4 && isTwoWordsCommand(keywords);
        } catch (NumberFormatException e) {
            return false;
        }

    }

    /**
     * Checks for valid special term 1 and 2. Spaces and capitalization does not matter.
     * If the user entered a valid special term, the keyword at index 1 will be replaced
     * with the system understood semester number for special term (3/4) for the
     * {@link SelectSemesterCommand} to correctly instantiate the semester number.
     *
     * @param keywords Contains the user input split by spaces.
     * @return If the user entered a valid special term.
     */
    public static boolean isValidSpecialTerm(String[] keywords) {
        if (keywords.length > 4) {
            return false;
        }
        StringBuilder semester = new StringBuilder();
        for (int i = 1; i < keywords.length; i++) {
            semester.append(keywords[i]);
        }
        semester = new StringBuilder(semester.toString());
        if (semester.toString().matches("st1|st2|specialterm1|specialterm2")) {
            int correctSemester = Integer.parseInt(semester.substring(semester.length() - 1)) + 2;
            keywords[1] = String.valueOf(correctSemester);
            return true;
        }
        return false;
    }

    /**
     * Parses a command of format " COMMAND_KEYWORD ( /PARAM_KEY PARAM_VALUE )* "
     * into a map from parameter keys to parameter values.
     * @param description User input
     * @return A map from parameter keys to parameter values
     */
    public static Map<String, String> parseParams(String description) {
        Map<String, String> paramsMap = new TreeMap<>();
        int firstSlash = description.indexOf('/');
        if (firstSlash == -1) {
            return paramsMap;
        }
        String paramsString = description.substring(firstSlash + 1);
        for (String param : paramsString.split(" /")) {
            int firstSpace = param.indexOf(' ');
            String key = firstSpace == -1 ? param : param.substring(0, firstSpace).trim();
            String value = firstSpace == -1 ? "" : param.substring(firstSpace + 1).trim();
            paramsMap.put(key, value);
        }
        return paramsMap;
    }
}
