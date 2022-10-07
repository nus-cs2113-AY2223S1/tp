package seedu.duke.item;

import seedu.duke.Ui;
import seedu.duke.exception.DukeException;

import java.util.ArrayList;

public class ItemList {
    private final Ui ui = new Ui();
    private final ArrayList<Item> itemList;

    public ItemList(ArrayList<Item> fileItems) { //store files from data.txt
        this.itemList = fileItems;
    }

    /**
     * Adds the newly created task to the task list.
     * @param item is the new task the user has created
     */
    public void addItem(Item item) {
        itemList.add(item);
        ui.addItemMessage(item, itemList);
    }

    public void deleteItem(int index) {
        Item item = itemList.get(index - 1);
        itemList.remove(index - 1);
        ui.deleteItemMessage(item, itemList);
    }

    public void markAvailable(Item item) {
        int index = itemList.indexOf(item);
        getItem(index).setAsAvailable();
    }

    public void markUnavailable(Item item) {
        int index = itemList.indexOf(item);
        getItem(index).setAsNotAvailable();
    }

    public int getListSize() {
        return itemList.size();
    }

    public Item getItem(int index) {
        return itemList.get(index - 1);
    }

    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    public void showList() {
        ui.showList(itemList);
    }
}
