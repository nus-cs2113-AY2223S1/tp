package seedu.duke;

import java.util.List;

public class ItemList {
    private final Ui ui = new Ui();
    private final List<Item> items;
    //    private final Ui ui = new Ui();

    public ItemList(List<Item> fileItems, List<Item> items) {
        this.items = items;
        items = fileItems;
    }

    /**
     * Adds the newly created task to the task list.
     * @param item is the new task the user has created
     */
    public void addItem(Item item) {
        items.add(item);
        ui.addItemMessage(item, items);
    }

    public void delete(int index) {
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

    public List<Item> getItemList() {
        return this.items;
    }

    public void showList() {
        ui.showList(items);
    }
}
