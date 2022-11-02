package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.exception.DuplicateException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.exception.InvalidItemException;
import seedu.duke.exception.InvalidPriceException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.ui.Ui;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;


// @@author bdthanh

/**
 * A representation of a command to add a new item.
 */
public class AddItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final UserList userList;
    private final TransactionList transactionList;
    private static final String NAME_DELIMITER = "n";
    private static final String CATEGORY_DELIMITER = "c";
    private static final String PRICE_DELIMITER = "p";
    private static final String OWNER_DELIMITER = "o";
    private static final int NUMBER_OF_ARGS = 4;
    private static final int NAME_INDEX = 0;
    private static final int CATEGORY_INDEX = 1;
    private static final int PRICE_INDEX = 2;
    private static final int OWNER_INDEX = 3;

    /**
     * Constructor for AddItemCommand.
     *
     * @param parts The parts from user input
     * @param userList The list of users to work with
     * @param itemList The list of items to work with
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public AddItemCommand(String[] parts, UserList userList, ItemList itemList,
            TransactionList transactionList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        this.userList = userList;
        this.transactionList = transactionList;
        if (parts.length != NUMBER_OF_ARGS) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }

    /**
     * Gets arg values from the given part.
     *
     * @return An array of arg values
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     */
    private String[] getArgsAddItemCmd() throws InvalidArgumentException {
        String[] args = new String[NUMBER_OF_ARGS];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals(NAME_DELIMITER)) {
                args[NAME_INDEX] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(CATEGORY_DELIMITER)) {
                args[CATEGORY_INDEX] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(PRICE_DELIMITER)) {
                args[PRICE_INDEX] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(OWNER_DELIMITER)) {
                args[OWNER_INDEX] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return args;
    }

    /**
     * Executes AddItemCommand.
     *
     * @return false
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     * @throws UserNotFoundException If that user cannot be found in the list
     * @throws DuplicateException If that user have item with the same name
     * @throws InvalidPriceException If price value is less than 0
     */
    public boolean executeCommand() throws InvalidArgumentException,
            UserNotFoundException, DuplicateException, InvalidPriceException,
            InvalidCategoryException, InvalidItemException {
        String[] args = getArgsAddItemCmd();
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        itemList.checkValidArgsForItem(userList, args);
        String name = args[NAME_INDEX];
        int categoryNumber = Integer.parseInt(args[CATEGORY_INDEX]);
        double price = Double.parseDouble(args[PRICE_INDEX]);
        String ownerId = args[OWNER_INDEX];
        Item item = new Item(name, categoryNumber, price, ownerId);
        this.itemList.addItem(item);
        Ui.addItemMessage(item, itemList.getListSize(), transactionList);
        return false;
    }
}
