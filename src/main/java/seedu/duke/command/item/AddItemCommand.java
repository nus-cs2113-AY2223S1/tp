package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.exception.DuplicateException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidPriceException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.ui.Ui;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_SAME_OWNER;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NUMBER_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_LESS_THAN_ZERO;

//@@author bdthanh

/**
 * A representation of a command to add a new item.
 */
public class AddItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final UserList userList;
    private final TransactionList transactionList;

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
        if (parts.length != 4) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    /**
     * Gets arg values from the given part.
     *
     * @return An array of arg values
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     */
    private String[] getArgsAddItemCmd() throws InvalidArgumentException {
        String[] args = new String[4];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals("n")) {
                args[0] = CommandParser.getArgValue(part);
            } else if (delimiter.equals("c")) {
                args[1] = CommandParser.getArgValue(part);
            } else if (delimiter.equals("p")) {
                args[2] = CommandParser.getArgValue(part);
            } else if (delimiter.equals("o")) {
                args[3] = CommandParser.getArgValue(part);
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
     * @param owner    The input owner name
     * @return true If that user do not have any item with the same name
     * @throws DuplicateException If that user have item with the same name
     */
    private boolean isValidName(String itemName, String owner) throws DuplicateException {
        try {
            Item item = itemList.getItemByName(itemName);
            if (item.getOwnerId().equals(owner)) {
                throw new DuplicateException(MESSAGE_SAME_OWNER);
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
            throw new NumberFormatException(MESSAGE_NUMBER_FORMAT_INVALID);
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
            if (Double.parseDouble(price) < 0) {
                throw new InvalidPriceException(MESSAGE_PRICE_LESS_THAN_ZERO);
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
            throws UserNotFoundException, DuplicateException, InvalidPriceException {
        assert args.length == 4 : "Args length is invalid";
        return isValidName(args[0], args[3]) && isValidCategoryNumber(args[1])
                && isValidPrice(args[2]) && isValidOwner(args[3]);
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
            DuplicateException, InvalidPriceException {
        String[] args = getArgsAddItemCmd();
        assert args.length == 4 : "Args length is invalid";
        if (areValidArgs(args)) {
            String name = args[0];
            int categoryNumber = Integer.parseInt(args[1]);
            double price = Double.parseDouble(args[2]);
            String ownerId = args[3];
            Item item = new Item(name, categoryNumber, price, ownerId);
            this.itemList.addItem(item);
            Ui.addItemMessage(item, itemList.getListSize(), transactionList);
        }
        return false;
    }
}
