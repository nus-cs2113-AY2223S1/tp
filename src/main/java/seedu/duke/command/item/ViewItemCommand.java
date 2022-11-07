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

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

// @@author jingwei55
public class ViewItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final TransactionList transactionList;
    private static final String ITEM_ID_DELIM = "i";

    /**
     * Constructor for ViewItemCommand.
     *
     * @param parts The parts from user input
     * @param itemList The list of items to work with
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public ViewItemCommand(String[] parts, ItemList itemList, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }

    private String getArgsViewItemCmd() throws InvalidArgumentException {
        String arg;
        String delimiter = CommandParser.getArgsDelimiter(parts[0]);
        if (delimiter.equals(ITEM_ID_DELIM)) {
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

    /**
     * Executes ViewItemComand.
     * 
     * @return false
     * @throws InvalidArgumentException If any argument is invalid
     * @throws ItemNotFoundException If given item id is invalid
     */
    public boolean executeCommand() throws ItemNotFoundException, InvalidArgumentException {
        String itemId = getArgsViewItemCmd();
        if (isValidItem(itemId)) {
            Item item = this.itemList.getItemById(itemId);
            Ui.viewItemMessage(item, transactionList);
        }
        return false;
    }
}
