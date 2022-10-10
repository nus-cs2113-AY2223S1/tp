package seedu.duke;

import seedu.duke.command.AddExerciseCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.GreetCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.MarkCommand;
import seedu.duke.command.SetCommand;
import seedu.duke.command.ViewCommand;

public class Parser {

    public static Command parse(String input) {
        String userCommand = input.split(" ")[0];
        String arguments = input.substring(userCommand.length()).trim();
        switch (userCommand) {
        case "greet":
            return new GreetCommand();
        case "exit":
            return new ExitCommand();
        case "set":
            return new SetCommand(arguments);
        case "add":
            return new AddExerciseCommand(arguments);
        case "view":
            return new ViewCommand(arguments);
        case "mark":
            return new MarkCommand(arguments);
        case "help":
        default:
            return new HelpCommand();
        }
    }

    public static String[] getArgumentList(String arguments) {
        return arguments.trim().split(" ");
    }

}
