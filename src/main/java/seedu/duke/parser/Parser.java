package seedu.duke.parser;

import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteModuleCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.IncompleteCommand;
import seedu.duke.command.InvalidModuleCommand;
import seedu.duke.command.SearchModuleCommand;
import seedu.duke.command.UnknownCommand;
import seedu.duke.command.ViewTimetableCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static Command parse(String userInput) {
        String[] keywords = userInput.split("\\s+");
        switch (keywords[0]) {
        case ("search"):
            return searchAddDeleteCommand(keywords, new SearchModuleCommand());
        case ("add"):
            return searchAddDeleteCommand(keywords, new AddModuleCommand());
        case ("delete"):
            return searchAddDeleteCommand(keywords, new DeleteModuleCommand());
        case ("view"):
            return viewHelpCommand(keywords, new ViewTimetableCommand());
        case ("help"):
            return viewHelpCommand(keywords, new HelpCommand());
        default:
            return new UnknownCommand();
        }
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

    public static Command searchAddDeleteCommand(String[] keywords, Command command) {
        boolean isValidCommand = isTwoWordsCommand(keywords) && isModuleCode(keywords[1]);
        if (isValidCommand) {
            return command;
        } else {
            return determineWrongCommand(keywords);
        }
    }

    private static Command determineWrongCommand(String[] keywords) {
        if (!isTwoWordsCommand(keywords)) {
            return new IncompleteCommand();
        } else if (!isModuleCode(keywords[1])) {
            return new InvalidModuleCommand();
        } else {
            return new UnknownCommand();
        }
    }

    public static Command viewHelpCommand(String[] keywords, Command command) {
        if (isOneWordCommand(keywords)) {
            return command;
        } else {
            return new UnknownCommand();
        }
    }
}
