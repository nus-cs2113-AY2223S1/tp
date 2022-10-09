package seedu.duke.item;

import seedu.duke.Ui;
import seedu.duke.item.Item;

import java.util.ArrayList;

public class ItemList {
    private final Ui ui = new Ui();
    private final ArrayList<Item> items;

    public ItemList(ArrayList<Item> fileItems) { //store files from data.txt
        this.items = fileItems;
    }

    /**
     * Adds the newly created task to the task list.
     * @param item is the new task the user has created
     */
    public void addItem(Item item) {
        items.add(item);
        ui.addItemMessage(item, items);
    }

    public void deleteItem(int index) {
        Item item = items.get(index - 1);
        items.remove(index - 1);
        ui.deleteItemMessage(item, items);
    }

    public int getListSize() {
        return items.size();
    }

    public Item getItem(int index) {
        return items.get(index - 1);
    }

    public ArrayList<Item> getItemList() {
        return this.items;
    }

    public void showList() {
        ui.showItemsList(items);
    }
}
