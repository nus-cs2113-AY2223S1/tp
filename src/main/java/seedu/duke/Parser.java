package seedu.duke;


import seedu.duke.command.*;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Parser {

    private static Logger logger = Logger.getLogger("Parser");

    public static Command parse(String input) {
        String userCommand = input.split(" ")[0];
        logger.log(Level.FINE, "command: " + userCommand);
        String arguments = input.substring(userCommand.length()).trim();
        logger.log(Level.FINE, "arguments:" + arguments);
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
            return new HelpCommand();
        case "remove":
            return new RemoveExerciseCommand(arguments);
        default:
            return new InvalidCommand();
        }
    }

    public static String[] getArgumentList(String arguments) {
        assert (arguments != null);
        String[] argumentList = arguments.split("\\s*/\\s*");
        return argumentList;
    }
}
