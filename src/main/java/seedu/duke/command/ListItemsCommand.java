package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.item.ItemList;

public class ListItemsCommand extends Command {
    private final ItemList itemList;

    public ListItemsCommand(ItemList itemList) {
        this.itemList = itemList;
    }

    public boolean executeCommand() {
        Ui.printResponse(this.itemList.showList());
        return false;
    }
}
