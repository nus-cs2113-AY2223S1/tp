package seedu.duke.command;

import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;

public class ViewItemCommand extends Command {
    private final String[] args;
    private final ItemList itemList;

    public ViewItemCommand(String[] args, ItemList itemList) throws InsufficientArgumentsException {
        this.args = args;
        this.itemList = itemList;
        if (args.length != 1) {
            throw new InsufficientArgumentsException();
        }
    }

    public boolean executeCommand() {
        Item item = this.itemList.getItem(args[DEFAULT_FIRST_INDEX]);
        System.out.println(item);
        return false;
    }
}
