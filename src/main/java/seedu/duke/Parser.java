package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.GreetCommand;
import seedu.duke.command.HelpCommand;

public class Parser {

    public static Command parse(String input) {
        String userCommand = input.split(" ")[0];
        String arguments = input.substring(userCommand.length());
        switch (userCommand) {
        case "greet":
            return new GreetCommand();
        case "help":
            return new HelpCommand();
        case "exit":
            return new ExitCommand();
        default:
            return new HelpCommand();
        }
    }
}
