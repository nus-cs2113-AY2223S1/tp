package seedu.duke.parser;

import java.util.Arrays;
import java.util.List;

import seedu.duke.command.item.AddItemCommand;
import seedu.duke.command.item.FindItemCommand;
import seedu.duke.command.item.ListCategoriesCommand;
import seedu.duke.command.item.ListItemsCommand;
import seedu.duke.command.item.RemoveItemCommand;
import seedu.duke.command.item.SortItemCommand;
import seedu.duke.command.item.UpdateItemCommand;
import seedu.duke.command.item.ViewItemCommand;
import seedu.duke.command.transaction.AddTransactionCommand;
import seedu.duke.command.transaction.ListTransactionsCommand;
import seedu.duke.command.transaction.RemoveTransactionCommand;
import seedu.duke.command.transaction.UpdateTransactionCommand;
import seedu.duke.command.transaction.ViewTransactionCommand;
import seedu.duke.command.transaction.ViewTransactionsByStatusCommand;
import seedu.duke.command.transaction.ViewBorrowTransactionsByUserCommand;
import seedu.duke.command.transaction.ViewLendTransactionsByUserCommand;
import seedu.duke.command.Command;
import seedu.duke.command.exit.ExitCommand;
import seedu.duke.command.help.HelpCommand;
import seedu.duke.command.user.ViewUserCommand;
import seedu.duke.command.user.ViewUserLossCommand;
import seedu.duke.command.user.ViewUserGainCommand;
import seedu.duke.command.user.FindUserCommand;
import seedu.duke.command.user.AddUserCommand;
import seedu.duke.command.user.ListUsersCommand;
import seedu.duke.command.user.RemoveUserCommand;
import seedu.duke.command.user.ViewUserItemsCommand;
import seedu.duke.exception.CommandNotFoundException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.item.ItemList;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ARGUMENT_EMPTY;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_COMMAND_UNRECOGNIZABLE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CONTAIN_DATA_SEPARATOR;

// @@author winston-lim

/**
 * A class that parses and analyses the input string from the user.
 */
public class CommandParser {
    /*
     * Constants line separated by utility
     */
    private static final String DEFAULT_DELIMITER = " ";
    private static final String ARGS_DELIMITER = "/";
    private static final int COMMAND_INDEX = 0;
    private static final int ARGS_INDEX = 1;

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_LIST_USERS = "list-users";
    private static final String COMMAND_LIST_ITEMS = "list-items";
    private static final String COMMAND_LIST_TX = "list-tx";
    private static final String COMMAND_VIEW_USER = "view-user";
    private static final String COMMAND_FIND_USER = "find-user";
    private static final String COMMAND_VIEW_ITEM = "view-item";
    private static final String COMMAND_VIEW_TX = "view-tx";
    private static final String COMMAND_ADD_USER = "add-user";
    private static final String COMMAND_ADD_ITEM = "add-item";
    private static final String COMMAND_FIND_ITEM = "find-item";
    private static final String COMMAND_ADD_TX = "add-tx";
    private static final String COMMAND_REMOVE_USER = "remove-user";
    private static final String COMMAND_REMOVE_ITEM = "remove-item";
    private static final String COMMAND_REMOVE_TX = "remove-tx";
    private static final String COMMAND_FIND_TX = "find-tx";
    private static final String COMMAND_VIEW_BORROW_TX_BY_USER = "view-borrow-tx-by-user";
    private static final String COMMAND_VIEW_LEND_TX_BY_USER = "view-lend-tx-by-user";
    private static final String COMMAND_VIEW_USER_LOSS = "view-user-loss";
    private static final String COMMAND_VIEW_USER_GAIN = "view-user-gain";
    private static final String COMMAND_VIEW_USER_ITEMS = "view-user-items";
    private static final String COMMAND_SORT_ITEMS = "sort-items";
    private static final String COMMAND_LIST_CATEGORIES = "list-categories";
    private static final String COMMAND_UPDATE_ITEM = "update-item";
    private static final String COMMAND_UPDATE_TRANSACTION = "update-tx";


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
     * Gets all subsequent parts from user input, which are the arguments.
     *
     * @param input a single line of user input
     * @return String[] the arguments for a command
     */
    public static String[] getParts(String input) {
        return parseUserInput(input).get(ARGS_INDEX);
    }

    /**
     * Gets the argument value from the part given.
     *
     * @param part A String contains recognizer and the value
     * @return The argument value
     * @throws InvalidArgumentException If the value is empty
     */
    public static String getArgValue(String part) throws InvalidArgumentException {
        String[] splitPart = part.trim().split(DEFAULT_DELIMITER, 2);
        if (splitPart.length != 2) {
            throw new InvalidArgumentException(MESSAGE_ARGUMENT_EMPTY);
        }
        return splitPart[1].trim();
    }

    public static String getArgsDelimiter(String part) throws InvalidArgumentException {
        String[] splitPart = part.trim().split(DEFAULT_DELIMITER, 2);
        return splitPart[0].trim();
    }

    /**
     * Parses the command from user.
     *
     * @param input The input from user
     * @param userList The list of users
     * @param itemList The list of items
     * @param transactionList The list of transactions
     * @return Commands based on the command word
     * @throws CommandNotFoundException If the command is unrecognizable
     * @throws InsufficientArgumentsException If number of args in the commands is not enough.
     */
    public static Command createCommand(String input, UserList userList, ItemList itemList,
            TransactionList transactionList) throws CommandNotFoundException,
            InsufficientArgumentsException, InvalidArgumentException {
        String command = getCommand(input);
        String[] parts = getParts(input);
        if (isContainingDataSeparator(input)) {
            throw new InvalidArgumentException(MESSAGE_CONTAIN_DATA_SEPARATOR);
        }
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
            return new ViewUserCommand(parts, userList, itemList, transactionList);
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
        case COMMAND_VIEW_USER_LOSS:
            return new ViewUserLossCommand(parts, userList, transactionList);
        case COMMAND_VIEW_USER_GAIN:
            return new ViewUserGainCommand(parts, userList, transactionList);
        case COMMAND_VIEW_USER_ITEMS:
            return new ViewUserItemsCommand(parts, userList, itemList, transactionList);
        case COMMAND_REMOVE_ITEM:
            return new RemoveItemCommand(parts, itemList, transactionList);
        case COMMAND_REMOVE_TX:
            return new RemoveTransactionCommand(parts, transactionList);
        case COMMAND_FIND_TX:
            return new ViewTransactionsByStatusCommand(parts, transactionList);
        case COMMAND_VIEW_BORROW_TX_BY_USER:
            return new ViewBorrowTransactionsByUserCommand(parts, transactionList, userList);
        case COMMAND_VIEW_LEND_TX_BY_USER:
            return new ViewLendTransactionsByUserCommand(parts, transactionList, userList);
        case COMMAND_SORT_ITEMS:
            return new SortItemCommand(parts, itemList, transactionList);
        case COMMAND_LIST_CATEGORIES:
            return new ListCategoriesCommand();
        case COMMAND_FIND_ITEM:
            return new FindItemCommand(parts, itemList, transactionList);
        case COMMAND_FIND_USER:
            return new FindUserCommand(parts, userList);
        case COMMAND_UPDATE_ITEM:
            return new UpdateItemCommand(parts, itemList, transactionList);
        case COMMAND_UPDATE_TRANSACTION:
            return new UpdateTransactionCommand(parts, transactionList);

        default:
            throw new CommandNotFoundException(MESSAGE_COMMAND_UNRECOGNIZABLE);
        }
    }

    private static boolean isContainingDataSeparator(String input) {
        return input.contains("|");
    }
}
