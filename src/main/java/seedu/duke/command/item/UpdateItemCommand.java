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

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_OUT_OF_RANGE;

//@@author winston-lim
public class UpdateItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final TransactionList transactionList;

    public UpdateItemCommand(String[] parts, ItemList itemList, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        this.transactionList = transactionList;
        // Requires itemId and at least one property to change
        // For now, we support only updating price. This can be updated in the future to support any
        // field.
        if (parts.length != 2) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    public String[] getArgsUpdateItemCmd() throws InvalidArgumentException {
        String[] args = new String[this.parts.length];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals("i")) {
                args[0] = CommandParser.getArgValue(part);
            } else if (delimiter.equals("p")) {
                args[1] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return args;
    }

    private boolean isValidId(String itemId) throws ItemNotFoundException {
        try {
            itemList.getItemById(itemId);
            return true;
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException(MESSAGE_ITEM_NOT_FOUND);
        }
    }

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

    private boolean areValidArgs(String[] args)
            throws ItemNotFoundException, InvalidPriceException {
        return isValidId(args[0]) && isValidPrice(args[1]);
    }

    public boolean executeCommand()
            throws InvalidArgumentException, ItemNotFoundException, InvalidPriceException, InvalidCategoryException {
        String[] args = getArgsUpdateItemCmd();
        if (areValidArgs(args)) {
            String itemId = args[0];
            double price = Double.parseDouble(args[1]);
            Item updatedItem = this.itemList.updateItemPrice(itemId, price);
            Ui.updateItemMessage(updatedItem, transactionList);
        }
        return false;
    }
}
