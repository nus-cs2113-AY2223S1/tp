package seedu.duke.command.item;


import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.exception.InvalidPriceException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.ui.Ui;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;

import java.math.BigDecimal;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_OUT_OF_RANGE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_TOO_MANY_DECIMALS;

// @@author winston-lim
public class UpdateItemCommand extends Command {
    private static final String ITEM_ID_DELIMITER = "i";
    private static final String ITEM_PRICE_DELIMITER = "p";
    private static final int REQUIRED_PARTS_COUNT = 2;
    private static final int ITEM_ID_INDEX = 0;
    private static final int ITEM_PRICE_INDEX = 1;
    private static final int MIN_PRICE = 0;
    private static final int MAX_PRICE = 10000;
    private final String[] parts;
    private final ItemList itemList;
    private final TransactionList transactionList;

    /**
     * Constructor for UpdateItemCommand.
     *
     * @param parts The parts from user input
     * @param itemList The list of items to work with
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public UpdateItemCommand(String[] parts, ItemList itemList, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        this.transactionList = transactionList;
        // Requires itemId and at least one property to change
        // For now, we support only updating price. This can be updated in the future to support any
        // field.
        if (parts.length != REQUIRED_PARTS_COUNT) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }


    /**
     * Get args from given part.
     * 
     * @return String[] An array of arg values
     * @throws InvalidArgumentException If any part cannot be parsed
     */
    public String[] getArgsUpdateItemCmd() throws InvalidArgumentException {
        String[] args = new String[this.parts.length];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals(ITEM_ID_DELIMITER)) {
                args[ITEM_ID_INDEX] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(ITEM_PRICE_DELIMITER)) {
                args[ITEM_PRICE_INDEX] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return args;
    }


    /**
     * Checks if an item id is valid or not.
     * 
     * @param itemId String value of given item id
     * @return true If item is valid
     * @throws ItemNotFoundException If item does not exist
     */
    private boolean isValidId(String itemId) throws ItemNotFoundException {
        try {
            itemList.getItemById(itemId);
            return true;
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException(MESSAGE_ITEM_NOT_FOUND);
        }
    }


    /**
     * Check if given price is valid or not.
     * 
     * @param price String value of given price
     * @return true if price is valid
     * @throws InvalidPriceException If price is out of range(0, 10000) or price has too many decimals
     */
    private boolean isValidPrice(String price) throws InvalidPriceException {
        try {
            if (Double.parseDouble(price) < MIN_PRICE || Double.parseDouble(price) > MAX_PRICE) {
                throw new InvalidPriceException(MESSAGE_PRICE_OUT_OF_RANGE);
            } else if (BigDecimal.valueOf(Double.parseDouble(price)).scale() > 2) {
                throw new InvalidPriceException(MESSAGE_PRICE_TOO_MANY_DECIMALS);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_PRICE_FORMAT_INVALID);
        }
    }


    /**
     * Check if given args are valid or not.
     * 
     * @param args The array of input arguments
     * @return true if all arguments are valid
     * @throws ItemNotFoundException If given item id is not valid
     * @throws InvalidPriceException If given price is not valid
     */
    private boolean areValidArgs(String[] args)
            throws ItemNotFoundException, InvalidPriceException {
        return isValidId(args[ITEM_ID_INDEX]) && isValidPrice(args[ITEM_PRICE_INDEX]);
    }


    /**
     * Executes UpdateItemCommand.
     * 
     * @return false
     * @throws InvalidArgumentException If any argument is invalid
     * @throws ItemNotFoundException If given item id is invalid
     * @throws InvalidPriceException If given price is invalid
     * @throws InvalidCategoryException If category of item is invalid
     */
    public boolean executeCommand() throws InvalidArgumentException, ItemNotFoundException,
            InvalidPriceException, InvalidCategoryException {
        String[] args = getArgsUpdateItemCmd();
        if (areValidArgs(args)) {
            String itemId = args[ITEM_ID_INDEX];
            double price = Double.parseDouble(args[ITEM_PRICE_INDEX]);
            Item updatedItem = this.itemList.updateItemPrice(itemId, price);
            Ui.updateItemMessage(updatedItem, transactionList);
        }
        return false;
    }
}
