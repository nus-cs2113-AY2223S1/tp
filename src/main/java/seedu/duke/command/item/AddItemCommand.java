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

import static seedu.duke.exception.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_ITEM_NAME_TAKEN;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_NUMBER_FORMAT_INVALID;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_PRICE_FORMAT_INVALID;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_PRICE_LESS_THAN_ZERO;

public class AddItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final UserList userList;
    private final TransactionList transactionList;

    public AddItemCommand(String[] parts, UserList userList, ItemList itemList, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        this.userList = userList;
        this.transactionList = transactionList;
        if (parts.length != 4) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    public String[] getArgsAddItemCmd() throws InvalidArgumentException {
        String[] args = new String[4];
        for (String part : parts) {
            if (part.startsWith("n")) {
                args[0] = CommandParser.getArgValue(part);
            } else if (part.startsWith("c")) {
                args[1] = CommandParser.getArgValue(part);
            } else if (part.startsWith("p")) {
                args[2] = CommandParser.getArgValue(part);
            } else if (part.startsWith("o")) {
                args[3] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return args;
    }

    private boolean isValidName(String itemName) throws DuplicateException {
        try {
            itemList.getItemById(itemName);
            throw new DuplicateException(MESSAGE_ITEM_NAME_TAKEN);
        } catch (ItemNotFoundException e) {
            return true;
        }
    }

    private boolean isValidOwner(String userId) throws UserNotFoundException {
        try {
            userList.getUserById(userId);
            return true;
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    private boolean isValidCategoryNumber(String categoryNumber) {
        try {
            Integer.parseInt(categoryNumber);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_NUMBER_FORMAT_INVALID);
        }
    }

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


    private boolean areValidArgs(String[] args)
            throws UserNotFoundException, DuplicateException, InvalidPriceException {
        return isValidName(args[0]) && isValidCategoryNumber(args[1])
                && isValidPrice(args[2]) && isValidOwner(args[3]);
    }

    public boolean executeCommand()
            throws InvalidArgumentException, UserNotFoundException, DuplicateException, InvalidPriceException {
        String[] args = getArgsAddItemCmd();
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
