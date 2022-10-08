package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.GreetCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.command.SetCommand;
import seedu.duke.command.AddFoodCommand;
import seedu.duke.command.AddExerciseCommand;

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
        case "set":
            return new SetCommand(arguments);
        case "add":
            if (arguments.split(" ")[0] == "food") {
                return new AddFoodCommand(arguments);
            }
            if(arguments.split(" ")[0] == "exercise") {
                return new AddExerciseCommand(arguments);
            }
        case "view":
            return new ViewCommand(arguments);
        default:
            return new HelpCommand();
        }
    }

    public static String[] getArgumentList(String arguments) {
        return arguments.trim().split(" ");
    }

}
