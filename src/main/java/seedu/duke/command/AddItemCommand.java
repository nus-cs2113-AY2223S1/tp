package seedu.duke.command;

import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;

public class AddItemCommand extends Command {
    private final String[] args;
    private final ItemList itemList;

    public AddItemCommand(String[] args, ItemList itemList) throws InsufficientArgumentsException {
        this.args = args;
        this.itemList = itemList;
        if (args.length != 4) {
            throw new InsufficientArgumentsException();
        }
    }

    public boolean executeCommand() {
        // String name, int categoryNumber, double price, String ownerId
        String name = this.args[0];
        int categoryNumber = Integer.parseInt(this.args[1]);
        double price = Double.parseDouble(this.args[2]);
        String ownerId = this.args[3];
        this.itemList.addItem(new Item(name, categoryNumber, price, ownerId));
        System.out.println(this.itemList.getListSize());
        return false;
    }
}
