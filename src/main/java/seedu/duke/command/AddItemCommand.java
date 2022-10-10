package seedu.duke.command;

import seedu.duke.exception.DuplicateException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.user.UserList;

public class AddItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final UserList userList;

    public AddItemCommand(String[] parts, UserList userList, ItemList itemList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        this.userList = userList;
        if (parts.length != 4) {
            throw new InsufficientArgumentsException();
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
                throw new InvalidArgumentException("One of the parts is in incorrect format");
            }
        }
        return args;
    }

    private boolean isValidName(String itemName) throws DuplicateException {
        try {
            itemList.getItemById(itemName);
            throw new DuplicateException("This itemName has been taken");
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
            throw new NumberFormatException("Number should only contain digit 0-9");
        }
    }

    private boolean isValidPrice(String price) {
        try {
            Double.parseDouble(price);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Price is a float, check your format");
        }
    }


    private boolean areValidArgs(String[] args) throws UserNotFoundException, DuplicateException {
        return isValidName(args[0]) && isValidCategoryNumber(args[1])
                && isValidPrice(args[2]) && isValidOwner(args[3]);
    }

    public boolean executeCommand() throws InvalidArgumentException, UserNotFoundException, DuplicateException {
        // String name, int categoryNumber, double price, String ownerId
        String[] args = getArgsAddItemCmd();
        if (areValidArgs(args)) {
            String name = args[0];
            int categoryNumber = Integer.parseInt(args[1]);
            double price = Double.parseDouble(args[2]);
            String ownerId = args[3];
            Item item = new Item(name, categoryNumber, price, ownerId);
            this.itemList.addItem(item);
            //ui.confirmAddItem(item);
        }
        return false;
    }
}
