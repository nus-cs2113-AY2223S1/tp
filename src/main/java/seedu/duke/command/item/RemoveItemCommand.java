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

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

//@@author bdthanh

/**
 * A representation of a command to remove an item.
 */
public class RemoveItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final TransactionList transactionList;
    private static final String ITEM_ID_DELIMITER = "i";
    private static final int NUMBER_OF_ARGS = 1;
    private static final int ID_INDEX = 0;

    /**
     * Constructor for RemoveItemCommand.
     *
     * @param parts           The parts from user input
     * @param itemList        The list of items to work with
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public RemoveItemCommand(String[] parts, ItemList itemList, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        this.transactionList = transactionList;
        if (parts.length != NUMBER_OF_ARGS) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }

    /**
     * Gets arg values from the given part.
     *
     * @return An array of arg values
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     */
    private String[] getArgsRemoveItemCmd() throws InvalidArgumentException {
        String[] args = new String[NUMBER_OF_ARGS];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals(ITEM_ID_DELIMITER)) {
                args[ID_INDEX] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }

        }
        return args;
    }

    /**
     * Executes RemoveItemCommand.
     *
     * @return false
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     * @throws ItemNotFoundException    If the item cannot be found in the list
     * @throws InvalidItemException     If the item is unavailable
     */
    public boolean executeCommand()
            throws InvalidArgumentException, ItemNotFoundException, InvalidItemException {
        String[] args = getArgsRemoveItemCmd();
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        String itemId = args[ID_INDEX];
        Item item = itemList.getItemById(itemId);
        itemList.deleteItem(itemId, transactionList);
        Ui.deleteItemMessage(item, itemList.getListSize(), transactionList);
        return false;
    }
}
