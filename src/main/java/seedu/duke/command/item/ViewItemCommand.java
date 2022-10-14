package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;

import static seedu.duke.exception.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_INVALID_PARTS;

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
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    public String getArgsViewItemCmd() throws InvalidArgumentException {
        String arg;
        if (parts[0].startsWith("i")) {
            arg = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
        }
        return arg;
    }

    private boolean isValidItem(String itemName) throws ItemNotFoundException {
        try {
            itemList.getItemByName(itemName);
            return true;
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException(e.getMessage());
        }
    }

    public boolean executeCommand() throws ItemNotFoundException, InvalidArgumentException {
        String itemName = getArgsViewItemCmd();
        if (isValidItem(itemName)) {
            Item item = this.itemList.getItemByName(itemName);
            Ui.viewItemMessage(item, transactionList);
        }
        return false;
    }
}
