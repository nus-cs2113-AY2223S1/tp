package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;

public class ViewItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final TransactionList transactionList;



    public ViewItemCommand(String[] parts, ItemList itemList,
                           TransactionList transactionList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException();
        }
    }

    public String getArgsViewItemCmd() throws InvalidArgumentException {
        String arg;
        if (parts[0].startsWith("i")) {
            arg = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException("Please input item name in the correct format");
        }
        return arg;
    }

    private boolean isValidItem(String itemId) throws ItemNotFoundException {
        try {
            itemList.getItemById(itemId);
            return true;
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException(e.getMessage());
        }
    }

    public boolean executeCommand() throws ItemNotFoundException, InvalidArgumentException {
        String itemName = getArgsViewItemCmd();
        if (isValidItem(itemName)) {
            Item item = this.itemList.getItemById(itemName);
            Ui.viewItemMessage(item, transactionList);
        }
        return false;
    }
}
