package seedu.duke;

import seedu.duke.command.*;

public class Parser {

    public static Command parse(String input) {
        String userCommand = input.split(" ")[0];
        String arguments = input.substring(userCommand.length() + 1);
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
