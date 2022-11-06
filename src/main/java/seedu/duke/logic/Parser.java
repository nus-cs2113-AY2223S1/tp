package seedu.duke.logic;

import seedu.duke.exception.IllegalValueException;
import seedu.duke.logic.command.AddCommand;
import seedu.duke.logic.command.Command;
import seedu.duke.logic.command.ExitCommand;
import seedu.duke.logic.command.FindCommand;
import seedu.duke.logic.command.GreetCommand;
import seedu.duke.logic.command.HelpCommand;
import seedu.duke.logic.command.InvalidCommand;
import seedu.duke.logic.command.MarkCommand;
import seedu.duke.logic.command.RemoveCommand;
import seedu.duke.logic.command.SetCommand;
import seedu.duke.logic.command.ViewCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

;

public class Parser {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static Command parse(String input) {
        input = input.trim();
        String userCommand = getUserCommand(input);
        LOGGER.info("command: " + userCommand);
        String arguments = getArgumentSubString(input, userCommand);
        LOGGER.info("arguments:" + arguments);
        switch (userCommand) {
        case "greet":
            return new GreetCommand();
        case "exit":
            return new ExitCommand(arguments);
        case "set":
            return new SetCommand(arguments, true);
        case "add":
            return new AddCommand(arguments, true, false);
        case "view":
            return new ViewCommand(arguments);
        case "mark":
            return new MarkCommand(arguments);
        case "remove":
            return new RemoveCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        case "help":
            return new HelpCommand(arguments);
        default:
            return new InvalidCommand();
        }
    }

    public static String getUserCommand(String input) {
        String userCommand = input.split(" ")[0];
        return userCommand;
    }

    public static String getArgumentSubString(String input, String userCommand) {
        return input.substring(userCommand.length()).trim();
    }

    public static int getArgumentsCount(String arguments) {
        int argumentsCount = (int) arguments.chars().filter(character -> character == '/').count();
        return argumentsCount;
    }

    public static String[] getArgumentList(String arguments) {
        assert (arguments != null);
        String[] argumentList = arguments.toLowerCase().split("\\s*/\\s*");
        return argumentList;
    }

    public static String getClassType(String[] argumentList) {
        return argumentList[0];
    }

    public static String getDateNoDateTracker(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(input, formatter);
        return formatter.format(localDate);
    }

    public static LocalDate parseDate(String input, int monthsToAdd) throws IllegalValueException {
        try {
            LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("d-M-yyyy"));
            if (date.isAfter(LocalDate.now().plusMonths(monthsToAdd))) {
                throw new IllegalValueException("Date is too far in the future");
            }
            return date;
        } catch (DateTimeParseException e) {
            throw new IllegalValueException("Date is in the wrong format or invalid."
                    + " Please follow the dd-MM-yyyy format");
        }
    }
}
