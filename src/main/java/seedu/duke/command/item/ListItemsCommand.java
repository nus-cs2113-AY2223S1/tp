package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;
import seedu.duke.item.ItemList;
import seedu.duke.transaction.TransactionList;

public class ListItemsCommand extends Command {
    private final ItemList itemList;
    private final TransactionList transactionList;

    public ListItemsCommand(ItemList itemList, TransactionList transactionList) {
        this.itemList = itemList;
        this.transactionList = transactionList;
    }

    public boolean executeCommand() {
        Ui.printResponse(itemList.toString(transactionList));
        return false;
    }
}
