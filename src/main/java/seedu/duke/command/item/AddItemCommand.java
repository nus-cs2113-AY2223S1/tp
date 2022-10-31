package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.exception.DuplicateException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.exception.InvalidPriceException;
import seedu.duke.exception.InvalidUserException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.ui.Ui;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NAME_LENGTH_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_SAME_ITEM_NAME_AND_PRICE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CATEGORY_INDEX_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_OUT_OF_RANGE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_FORMAT_INVALID;



//@@author bdthanh

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
     * @param parts           The parts from user input
     * @param userList        The list of users to work with
     * @param itemList        The list of items to work with
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
     * Checks if an item name is valid or not.
     *
     * @param itemName The input item name
     * @param price    price of item already listed with the same name
     * @return true If that user do not have any item with the same name and price
     * @throws DuplicateException If that user have item with the same name and price
     */
    private boolean isValidName(String itemName, Double price)
            throws DuplicateException, InvalidUserException {
        if (itemName.length() > 20) {
            throw new InvalidUserException(MESSAGE_NAME_LENGTH_INVALID);
        }
        try {
            Item item = itemList.getItemByName(itemName);
            if (item.getPricePerDay() == price) {
                throw new DuplicateException(MESSAGE_SAME_ITEM_NAME_AND_PRICE);
            }
            return true;
        } catch (ItemNotFoundException e) {
            return true;
        }
    }

    /**
     * Checks if a user is valid or not.
     *
     * @param userId The input name of owner
     * @return true If that username can be found in user list
     * @throws UserNotFoundException If that user cannot be found in the list
     */
    private boolean isValidOwner(String userId) throws UserNotFoundException {
        try {
            userList.getUserById(userId);
            return true;
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    /**
     * Checks if a categoryNumber is valid or not.
     *
     * @param categoryNumber The input category number
     * @return true If that number can be parsed
     */
    private boolean isValidCategoryNumber(String categoryNumber) {
        try {
            Integer.parseInt(categoryNumber);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_CATEGORY_INDEX_FORMAT_INVALID);
        }
    }

    /**
     * Checks if a price is valid or not.
     *
     * @param price The input price
     * @return true If that number can be parsed and has correct format
     * @throws InvalidPriceException If price value is less than 0
     */
    private boolean isValidPrice(String price) throws InvalidPriceException {
        try {
            if (Double.parseDouble(price) < 0 || Double.parseDouble(price) > 10000) {
                throw new InvalidPriceException(MESSAGE_PRICE_OUT_OF_RANGE);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_PRICE_FORMAT_INVALID);
        }
    }

    /**
     * Check if all args is valid or not.
     *
     * @param args The array of input args
     * @return true If they are all valid
     * @throws UserNotFoundException If that user cannot be found in the list
     * @throws DuplicateException    If that user have item with the same name
     * @throws InvalidPriceException If price value is less than 0
     */
    private boolean areValidArgs(String[] args)
            throws UserNotFoundException, DuplicateException, InvalidPriceException, InvalidUserException {
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        return isValidName(args[NAME_INDEX], Double.parseDouble(args[PRICE_INDEX]))
                && isValidCategoryNumber(args[CATEGORY_INDEX]) && isValidPrice(args[PRICE_INDEX])
                && isValidOwner(args[OWNER_INDEX]);
    }

    /**
     * Executes AddItemCommand.
     *
     * @return false
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     * @throws UserNotFoundException    If that user cannot be found in the list
     * @throws DuplicateException       If that user have item with the same name
     * @throws InvalidPriceException    If price value is less than 0
     */
    public boolean executeCommand() throws InvalidArgumentException, UserNotFoundException,
            DuplicateException, InvalidPriceException, InvalidCategoryException, InvalidUserException {
        String[] args = getArgsAddItemCmd();
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        if (areValidArgs(args)) {
            String name = args[NAME_INDEX];
            int categoryNumber = Integer.parseInt(args[CATEGORY_INDEX]);
            double price = Double.parseDouble(args[PRICE_INDEX]);
            String ownerId = args[OWNER_INDEX];
            Item item = new Item(name, categoryNumber, price, ownerId);
            this.itemList.addItem(item);
            Ui.addItemMessage(item, itemList.getListSize(), transactionList);
        }
        return false;
    }
}
