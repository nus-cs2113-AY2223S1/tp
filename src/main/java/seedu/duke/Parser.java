package seedu.duke;

import seedu.duke.command.SetCommand;
import seedu.duke.command.Command;
import seedu.duke.command.GreetCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.AddExerciseCommand;
import seedu.duke.command.AddFoodCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ViewCommand;

public class Parser {

    public static Command parse(String input) {
        String userCommand = input.split(" ")[0];
        String arguments = input.substring(userCommand.length()).trim();
        switch (userCommand) {
        case "greet":
            return new GreetCommand();
        case "help":
            return new HelpCommand();
        case "exit":
            return new ExitCommand();
        case "set":
            return new SetCommand(arguments);
        case "add":
            if (arguments.split(" ")[0].equals("food")) {
                return new AddFoodCommand(arguments);
            } else if (arguments.split(" ")[0].equals("exercise")) {
                return new AddExerciseCommand(arguments);
            }
            return new InvalidCommand();
        case "view":
            return new ViewCommand(arguments);
        default:
            return new InvalidCommand();
        }
    }

    public static String[] getArgumentList(String arguments) {
        return arguments.trim().split(" ");
    }

}
