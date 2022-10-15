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

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

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

    private String getArgsViewItemCmd() throws InvalidArgumentException {
        String arg;
        if (parts[0].startsWith("i")) {
            arg = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
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
        String itemId = getArgsViewItemCmd();
        if (isValidItem(itemId)) {
            Item item = this.itemList.getItemById(itemId);
            Ui.viewItemMessage(item, transactionList);
        }
        return false;
    }
}
