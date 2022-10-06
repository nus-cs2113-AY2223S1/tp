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

    public List<Item> getItemList() {
        return this.items;
    }

    public void showList() {
        ui.showList(items);
    }
}
