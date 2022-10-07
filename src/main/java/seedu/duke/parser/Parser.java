package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.DeleteModuleCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.SearchModulesCommand;
import seedu.duke.command.ViewTimetableCommand;
import seedu.duke.command.UnknownCommand;

public class Parser {
    public static Command parse(String userInput) {
        String[] keywords = userInput.split("\\s+");
        switch (keywords[0]) {
        case ("search"):
            return new SearchModulesCommand();
        case ("add"):
            return new AddModuleCommand();
        case ("delete"):
            return new DeleteModuleCommand();
        case ("view"):
            return new ViewTimetableCommand();
        case ("help"):
            return new HelpCommand();
        default:
            return new UnknownCommand();
        }
    }
}
