package seedu.duke.parser;

import java.util.Arrays;
import java.util.List;

import seedu.duke.command.item.AddItemCommand;
import seedu.duke.command.transaction.AddTransactionCommand;
import seedu.duke.command.user.AddUserCommand;
import seedu.duke.command.Command;
import seedu.duke.command.exit.ExitCommand;
import seedu.duke.command.help.HelpCommand;
import seedu.duke.command.item.ListItemsCommand;
import seedu.duke.command.transaction.ListTransactionsCommand;
import seedu.duke.command.user.ListUsersCommand;
import seedu.duke.command.item.RemoveItemCommand;
import seedu.duke.command.transaction.RemoveTransactionCommand;
import seedu.duke.command.user.RemoveUserCommand;
import seedu.duke.command.item.ViewItemCommand;
import seedu.duke.command.transaction.ViewTransactionCommand;
import seedu.duke.command.user.ViewUserCommand;
import seedu.duke.exception.CommandNotFoundException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.item.ItemList;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.UserList;

public class CommandParser {
    /*
     * Constants line separated by utility
     */
    private static final String DEFAULT_DELIMITER = " ";
    private static final String ARGS_DELIMITER = "/";
    private static final int COMMAND_INDEX = 0;
    // private static final int DEFAULT_FIRST_INDEX = 0;
    // private static final int DEFAULT_INDEX_INCREMENT = 1;
    private static final int ARGS_INDEX = 1;

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_LIST_USERS = "list-users";
    private static final String COMMAND_LIST_ITEMS = "list-items";
    private static final String COMMAND_LIST_TX = "list-tx";
    private static final String COMMAND_VIEW_USER = "view-user";
    private static final String COMMAND_VIEW_ITEM = "view-item";
    private static final String COMMAND_VIEW_TX = "view-tx";
    private static final String COMMAND_ADD_USER = "add-user";
    private static final String COMMAND_ADD_ITEM = "add-item";
    private static final String COMMAND_ADD_TX = "add-tx";
    private static final String COMMAND_REMOVE_USER = "remove-user";
    private static final String COMMAND_REMOVE_ITEM = "remove-item";
    private static final String COMMAND_REMOVE_TX = "remove-tx";


    /**
     * Parses a line of user input into a usable form.
     *
     * @param input a single line of user input.
     * @return A list of String[] where first index is command and second is arguments.
     */
    private static List<String[]> parseUserInput(String input) {
        String[] inputs = input.split(ARGS_DELIMITER);
        String[] command = Arrays.copyOfRange(inputs, COMMAND_INDEX, COMMAND_INDEX + 1);
        String[] args = Arrays.copyOfRange(inputs, COMMAND_INDEX + 1, inputs.length);
        return List.of(command, args);
    }

    /**
     * Gets the first word from user input, which is the command.
     *
     * @param input a single line of user input
     * @return String
     */
    public static String getCommand(String input) {
        return parseUserInput(input).get(COMMAND_INDEX)[COMMAND_INDEX].trim();
    }

    /**
     * Gets all subsequent words from user input, which are the arguments.
     *
     * @param input a single line of user input
     * @return String[] the arguments for a command
     */
    public static String[] getParts(String input) {
        return parseUserInput(input).get(ARGS_INDEX);
    }

    public static String getArgValue(String part) throws InvalidArgumentException {
        String[] splitPart = part.split(DEFAULT_DELIMITER, 2);
        if (splitPart.length == 1) {
            throw new InvalidArgumentException("The value cannot be empty");
        }
        return splitPart[1].trim();
    }

    public static Command createCommand(String input, UserList userList, ItemList itemList,
                                        TransactionList transactionList)
            throws CommandNotFoundException, InsufficientArgumentsException {
        String command = getCommand(input);
        String[] parts = getParts(input);
        // assert that command exists
        switch (command) {
        case COMMAND_EXIT:
            return new ExitCommand();
        case COMMAND_HELP:
            return new HelpCommand();
        case COMMAND_LIST_USERS:
            return new ListUsersCommand(userList);
        case COMMAND_LIST_ITEMS:
            return new ListItemsCommand(itemList, transactionList);
        case COMMAND_LIST_TX:
            return new ListTransactionsCommand(transactionList);
        case COMMAND_VIEW_USER:
            return new ViewUserCommand(parts, userList);
        case COMMAND_VIEW_ITEM:
            return new ViewItemCommand(parts, itemList, transactionList);
        case COMMAND_VIEW_TX:
            return new ViewTransactionCommand(parts, transactionList);
        case COMMAND_ADD_USER:
            return new AddUserCommand(parts, userList);
        case COMMAND_ADD_ITEM:
            return new AddItemCommand(parts, userList, itemList, transactionList);
        case COMMAND_ADD_TX:
            return new AddTransactionCommand(parts, userList, itemList, transactionList);
        case COMMAND_REMOVE_USER:
            return new RemoveUserCommand(parts, userList, itemList, transactionList);
        case COMMAND_REMOVE_ITEM:
            return new RemoveItemCommand(parts, itemList, transactionList);
        case COMMAND_REMOVE_TX:
            return new RemoveTransactionCommand(parts, transactionList);
        default:
            throw new CommandNotFoundException("This command is unrecognizable!!!\n"
                    + "Please use help command to check");
        }
    }
}
