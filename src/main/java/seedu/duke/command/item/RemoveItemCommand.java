package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidItemException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;

import static seedu.duke.exception.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_INVALID_PARTS;

public class RemoveItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final TransactionList transactionList;

    public RemoveItemCommand(String[] parts, ItemList itemList, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    public String[] getArgsRemoveItemCmd() throws InvalidArgumentException {
        String[] args = new String[1];
        for (String part : parts) {
            if (part.startsWith("i")) {
                args[0] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }

        }
        return args;
    }

    public boolean executeCommand()
            throws InvalidArgumentException, ItemNotFoundException, InvalidItemException {
        String[] args = getArgsRemoveItemCmd();
        String itemId = args[0];
        Item item = itemList.getItemById(itemId);
        itemList.deleteItem(itemId, transactionList);
        Ui.deleteItemMessage(item, itemList.getListSize());
        return false;
    }
}
