package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.item.ItemList;
import seedu.duke.item.Item;


public class RemoveItemCommand extends Command {
    private final String[] args;
    private final ItemList itemList;

    public RemoveItemCommand(String[] args, ItemList itemList)
            throws InsufficientArgumentsException {
        this.args = args;
        this.itemList = itemList;
        if (args.length != 1) {
            throw new InsufficientArgumentsException();
        }
    }

    public boolean executeCommand() {
        String itemId = this.args[0];
        this.itemList.deleteItem(itemId);
        Item item = itemList.getItemById(itemId);
        Ui.deleteItemMessage(item, itemList.getListSize());
        return false;
    }
}
