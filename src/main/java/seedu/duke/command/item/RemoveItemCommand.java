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

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

//@@author bdthanh

/**
 * A representation of a command to remove an item.
 */
public class RemoveItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;
    private final TransactionList transactionList;

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
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    /**
     * Gets arg values from the given part.
     *
     * @return An array of arg values
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     */
    private String[] getArgsRemoveItemCmd() throws InvalidArgumentException {
        String[] args = new String[1];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals("i")) {
                args[0] = CommandParser.getArgValue(part);
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
        assert args.length == 1 : "Args length is invalid";
        String itemId = args[0];
        Item item = itemList.getItemById(itemId);
        itemList.deleteItem(itemId, transactionList);
        Ui.deleteItemMessage(item, itemList.getListSize(), transactionList);
        return false;
    }
}
