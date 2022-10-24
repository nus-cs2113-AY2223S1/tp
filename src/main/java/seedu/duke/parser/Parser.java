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

public class Parser {
    public static Command parse(String userInput) throws YamomException {
        String[] keywords = userInput.split("\\s+");

        Command toExecute;
        switch (keywords[0]) {
        case (SearchModuleCommand.COMMAND_WORD):
            toExecute = new SearchModuleCommand(userInput);
            break;
        case (GetModuleCommand.COMMAND_WORD):
            toExecute = moduleRelatedCommand(keywords, new GetModuleCommand(keywords));
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
            toExecute = nusmodCommand(keywords, new ImportCommand(keywords));
            break;
        case (DisplaySelectedModuleListCommand.COMMAND_WORD):
            toExecute = singleWordCommand(keywords, new DisplaySelectedModuleListCommand(keywords));
            break;
        default:
            throw new YamomException("Cannot process the command");
        }
        return toExecute;
    }

    public static boolean isPartialModuleCode(String moduleCode) {
        Pattern pattern = Pattern.compile("[a-z]{0,3}\\d{0,4}[a-z]?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(moduleCode);
        return (matcher.find() && matcher.group().length() == moduleCode.length());
    }

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

    public static boolean isValidModuleCode(String moduleCode) {
        return isModuleCode(moduleCode) || isPartialModuleCode(moduleCode);
    }

    private static boolean isValidTwoWordCommand(String[] keywords) {
        return isTwoWordsCommand(keywords) && isValidModuleCode(keywords[1]);
    }

    private static boolean isValidSemester(String[] keywords) {
        int semesterInput = Integer.parseInt(keywords[1]);
        return semesterInput > 0 && semesterInput <= 4;
    }

    /**
     * Checks if the user entered a valid search or add or delete command in the
     * format
     * "search|add|delete MODULE_CODE".
     * 
     * @param keywords contains the user input split by spaces
     * @param command  the command that the user wants to execute
     * @return type of command
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

    public static Command nusmodCommand(String[] keywords, Command command) throws YamomException {
        if (isTwoWordsCommand(keywords)) {
            return command;
        } else {
            throw new YamomException("no NUSMOD link given");
        }
    }

    public static Command singleWordCommand(String[] keywords, Command command) throws YamomException {
        if (isOneWordCommand(keywords)) {
            return command;
        } else {
            throw new YamomException("0 arguments expected");
        }
    }

    public static Command selectSemesterCommand(String[] keywords, Command command) throws YamomException {
        if (isValidSemester(keywords)) {
            return command;
        } else {
            throw new YamomException("Not a valid semester");
        }
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
