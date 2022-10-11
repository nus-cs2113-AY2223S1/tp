package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.CommandType;
import seedu.duke.command.CreateCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.exceptions.InvalidUserCommandException;

public class CommandParser {
    public static Command getUserCommand(String userInput) throws InvalidUserCommandException {
        String[] userInputTokenized = userInput.split(" +");
        if (userInputTokenized.length < 1) {
            throw new InvalidUserCommandException("Error! Missing command. "
                    + "Please follow the command format provided!");
        }
        String userInputCommand = userInputTokenized[0];
        switch (userInputCommand) {
        case "/create":
            if (!isValidCreateCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid create command. "
                        + "Please follow the command format provided");
            }
            CreateCommand newCreateCommand = new CreateCommand(userInputTokenized, CommandType.CREATE);
            return newCreateCommand;
        case "/add":
            if (!isValidAddCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid add command. "
                        + "Please follow the command format provided");
            }
            AddCommand newAddCommand = new AddCommand(userInputTokenized, CommandType.ADD);
            return newAddCommand;
        case "/view":
            if (!isValidViewCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid view command. "
                        + "Please follow the command format provided");
            }
            ViewCommand newViewCommand = new ViewCommand(userInputTokenized, CommandType.VIEW);
            return newViewCommand;
        case "/delete":
            if (!isValidDeleteCommand(userInputTokenized)) {
                throw new InvalidUserCommandException("Error! Invalid delete command. "
                        + "Please follow the command format provided");
            }
            boolean isDeleteModule = userInputTokenized.length == 3;
            DeleteCommand newDeleteCommand = new DeleteCommand(userInputTokenized, CommandType.DELETE, isDeleteModule);
            return newDeleteCommand;
        default:
            throw new InvalidUserCommandException("Error! Unidentified command. "
                    + "Please follow the command format provided!");
        }
    }

    private static boolean isValidViewCommand(String[] parameters) {
        if (!parameters[1].startsWith("u/") && !parameters[1].startsWith("ALL") && !parameters[1].startsWith("MODULES")
                || parameters.length > 2) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isValidAddCommand(String[] parameters) {
        if (parameters.length == 3 && parameters[2].startsWith("m/") && parameters[1].startsWith("u/")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValidDeleteCommand(String[] parameters) {
        if (!parameters[1].startsWith("u/") || parameters.length > 3) {
            return false;
        } else if (parameters.length == 3 && !parameters[2].startsWith("m/")) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isValidCreateCommand(String[] parameters) {
        if (parameters.length > 2 || !parameters[1].startsWith("u/")) {
            return false;
        } else {
            return true;
        }
    }
}
