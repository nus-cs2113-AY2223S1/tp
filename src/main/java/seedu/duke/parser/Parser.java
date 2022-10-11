package seedu.duke.parser;

import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteModuleCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.IncompleteCommand;
import seedu.duke.command.InvalidModuleCommand;
import seedu.duke.command.UnknownCommand;
import seedu.duke.command.ViewTimetableCommand;
import seedu.duke.command.SearchModuleCodeCommand;
import seedu.duke.command.SearchModuleNameCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static Command parse(String userInput) {
        String[] keywords = userInput.split("\\s+");
        switch (keywords[0]) {
        case ("search"):
            return searchCommand(keywords);
        case ("add"):
            return addDeleteCommand(keywords, new AddModuleCommand(keywords));
        case ("delete"):
            return addDeleteCommand(keywords, new DeleteModuleCommand(keywords));
        case ("view"):
            return viewHelpExitCommand(keywords, new ViewTimetableCommand(keywords));
        case ("help"):
            return viewHelpExitCommand(keywords, new HelpCommand(keywords));
        case ("bye"):
            return viewHelpExitCommand(keywords, new ExitCommand(keywords));
        default:
            return new UnknownCommand(keywords);
        }
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

    public static boolean isMultiWordsCommand(String[] keywords) {
        return keywords.length > 2;
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

    private static boolean containsValidModuleCode(String[] keywords) {
        for (int i = 1; i < keywords.length; i++) {
            if (isValidModuleCode(keywords[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidTwoWordCommand(String[] keywords) {
        return isTwoWordsCommand(keywords) && isValidModuleCode(keywords[1]);
    }

    public static Command searchCommand(String[] keywords) {
        if (isMultiWordsCommand(keywords) && !containsValidModuleCode(keywords)) {
            return new SearchModuleNameCommand(keywords);
        } else if (isValidTwoWordCommand(keywords)) {
            return new SearchModuleCodeCommand(keywords);
        } else {
            return determineWrongCommand(keywords);
        }
    }

    /**
     * Checks if the user entered a valid search or add or delete command in the format
     * "search|add|delete MODULE_CODE".
     * @param keywords contains the user input split by spaces
     * @param command  the command that the user wants to execute
     * @return type of command
     */
    public static Command addDeleteCommand(String[] keywords, Command command) {
        if (isValidTwoWordCommand(keywords)) {
            return command;
        } else {
            // System.out.println("Invalid module code");
            return determineWrongCommand(keywords);
        }
    }

    private static Command determineWrongCommand(String[] keywords) {
        if (isOneWordCommand(keywords)) {
            return new IncompleteCommand(keywords);
        } else if (isTwoWordsCommand(keywords) && !isValidModuleCode(keywords[1])) {
            return new InvalidModuleCommand(keywords);
        } else {
            return new UnknownCommand(keywords);
        }
    }

    public static Command viewHelpExitCommand(String[] keywords, Command command) {
        if (isOneWordCommand(keywords)) {
            return command;
        } else {
            return new UnknownCommand(keywords);
        }
    }
}
