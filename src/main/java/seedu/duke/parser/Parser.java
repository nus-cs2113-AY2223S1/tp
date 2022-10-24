package seedu.duke.parser;

import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteModuleCommand;
import seedu.duke.command.DisplaySelectedModuleListCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.GetModuleCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ViewTimetableCommand;
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
    /**
     * Analyzes based on the first word which is supposed to be a keyword specifying the
     * feature that the user wants to call upon.
     *
     * @param userInput The whole <code>String</code> that the user entered.
     * @return The corresponding <code>Command</code>.
     * @throws YamomException Contains a string that specifies the type of incorrect user input.
     */
    public static Command parse(String userInput) throws YamomException {
        String[] keywords = userInput.split("\\s+");

        Command toExecute;
        switch (keywords[0]) {
        case (SearchModuleCommand.COMMAND_WORD):
            toExecute = searchCommand(userInput);
            break;
        case (GetModuleCommand.COMMAND_WORD):
            toExecute = getCommand(keywords);
            break;
        case (AddModuleCommand.COMMAND_WORD):
            toExecute = moduleRelatedCommand(keywords, new AddModuleCommand(keywords));
            break;
        case (DeleteModuleCommand.COMMAND_WORD):
            toExecute = moduleRelatedCommand(keywords, new DeleteModuleCommand(keywords));
            break;
        case (ViewTimetableCommand.COMMAND_WORD):
            toExecute = singleWordCommand(keywords, new ViewTimetableCommand(userInput));
            break;
        case (HelpCommand.COMMAND_WORD):
            toExecute = singleWordCommand(keywords, new HelpCommand(keywords));
            break;
        case (SelectSlotCommand.COMMAND_WORD):
            toExecute = new SelectSlotCommand(userInput);
            break;
        case (ExitCommand.COMMAND_WORD):
            toExecute = singleWordCommand(keywords, new ExitCommand(keywords));
            break;
        case (SelectSemesterCommand.COMMAND_WORD):
            toExecute = selectSemesterCommand(keywords, new SelectSemesterCommand(keywords));
            break;
        case (ExportCommand.COMMAND_WORD):
            toExecute = singleWordCommand(keywords, new ExportCommand(keywords));
            break;
        case (ImportCommand.COMMAND_WORD):
            toExecute = new ImportCommand(keywords);
            break;
        case (DisplaySelectedModuleListCommand.COMMAND_WORD):
            toExecute = singleWordCommand(keywords, new DisplaySelectedModuleListCommand(keywords));
            break;
        default:
            throw new YamomException("Cannot process the command");
        }
        return toExecute;
    }

    private static Command getCommand(String[] keywords) throws YamomException {
        return new GetModuleCommand(keywords);
    }

    /**
     * Checks to see if the user supplied moduleCode is part of a valid module code.
     *
     * @param moduleCode The user supplied moduleCode.
     * @return If the user supplied a valid partial module code.
     */
    public static boolean isPartialModuleCode(String moduleCode) {
        Pattern pattern = Pattern.compile("[a-z]{0,3}\\d{0,4}[a-z]?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(moduleCode);
        return (matcher.find() && matcher.group().length() == moduleCode.length());
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

    public static boolean isValidModuleCode(String moduleCode) {
        return isModuleCode(moduleCode) || isPartialModuleCode(moduleCode);
    }

    public static boolean isTwoWordsCommand(String[] keywords) {
        return keywords.length == 2;
    }

    public static boolean isOneWordCommand(String[] keywords) {
        return keywords.length == 1;
    }

    public static boolean isValidTwoWordCommand(String[] keywords) {
        return isTwoWordsCommand(keywords) && isValidModuleCode(keywords[1]);
    }

    public static Command searchCommand(String userInput) {
        return new SearchModuleCommand(userInput);
    }

    /**
     * Checks if the user entered a valid search or add or delete command in the
     * format
     * "search|add|delete MODULE_CODE".
     * 
     * @param keywords contains the user input split by spaces
     * @param command  the command that the user wants to execute
     * @return type of command
     * @throws YamomException Contains a string that specifies the type of incorrect user input.
     */
    public static Command moduleRelatedCommand(String[] keywords, Command command) throws YamomException {
        if (isValidTwoWordCommand(keywords)) {
            return command;
        }
        String errorMessage;
        if (isOneWordCommand(keywords)) {
            errorMessage = "Your command is incomplete.";
        } else if (isTwoWordsCommand(keywords) && !isValidModuleCode(keywords[1])) {
            errorMessage = "Module is invalid!"
                    + System.lineSeparator()
                    + "Please enter a valid module code." + System.lineSeparator()
                    + "Each module of study has a unique module code consisting of a two- "
                    + "or three-letter prefix that generally denotes the discipline,"
                    + "and four digits, the first of which indicates the level of the module " + System.lineSeparator()
                    + "(e.g., 1000 indicates a Level 1 module and 2000, a Level 2 module).";
        } else {
            errorMessage = "Unknown command, try again";
        }

        throw new YamomException(errorMessage);
    }

    public static Command singleWordCommand(String[] keywords, Command command) throws YamomException {
        if (isOneWordCommand(keywords)) {
            return command;
        } else {
            throw new YamomException("0 arguments expected");
        }
    }

    public static Command selectSemesterCommand(String[] keywords, Command command) throws YamomException {
        if (isValidSemester(keywords) || isValidSpecialTerm(keywords)) {
            return command;
        } else {
            throw new YamomException("Not a valid semester");
        }
    }

    public static boolean isValidSemester(String[] keywords) {
        int semesterInput = Integer.parseInt(keywords[1]);
        return semesterInput > 0 && semesterInput <= 4;
    }

    /**
     * Checks for valid special term 1 and 2. Spaces and capitalization does not matter.
     * If the user entered a valid special term, the keyword at index 1 will be replaced
     * with the system understood semester number for special term (3/4) for the
     * {@link #selectSemesterCommand(String[], Command)} to correctly instantiate
     * the semester number.
     *
     * @param keywords Contains the user input split by spaces.
     * @return If the user entered a valid special term.
     */
    public static boolean isValidSpecialTerm(String[] keywords) {
        StringBuilder semester = new StringBuilder();
        for (int i = 1; i < keywords.length; i++) {
            semester.append(keywords[i]);
        }
        semester = new StringBuilder(semester.toString().toUpperCase());
        if (semester.toString().matches("ST1|ST2|SPECIALTERM1|SPECIALTERM2")) {
            int correctSemester = Integer.parseInt(semester.substring(semester.length() - 1)) + 2;
            keywords[1] = String.valueOf(correctSemester);
            return  true;
        }
        return false;
    }

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
