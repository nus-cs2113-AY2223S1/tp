package seedu.duke.item;

import seedu.duke.Ui;
import seedu.duke.exception.ItemNotFoundException;

import java.util.ArrayList;

public class ItemList {
    private final Ui ui = new Ui();
    private final ArrayList<Item> itemList;

    public ItemList() { // store files from data.txt
        // this.itemList = fileItems;
        this.itemList = new ArrayList<Item>();
    }

    /**
     * Adds the newly created task to the task list.
     *
     * @param item is the new task the user has created
     */
    public void addItem(Item item) {
        itemList.add(item);
        ui.addItemMessage(item, itemList);
    }

    public void getItemOfAnUser(String userId) {
        itemList.stream().filter(t -> (t.getOwnerId().equals(userId))).forEach(System.out::println);
    }

    public Item getItem(String id) throws NullPointerException {
        for (Item item : itemList) {
            if (id.equals(item.getItemId())) {
                return item;
            }
        }
        // exception if item not found
        return null;
    }

    public void getItemOfSpecificCategory(int categoryNumber) {
        itemList.stream().filter(t -> (Category.setCategory(t.getCategory()) == categoryNumber))
                .forEach(System.out::println);
    }

    public void deleteItem(String itemId) {
        boolean found = false;
        for (Item item : itemList) {
            if (itemId.equals(item.getItemId())) {
                found = true;
                itemList.remove(item);
                ui.deleteItemMessage(item, itemList);
            }
        }
        if (!found) {
            System.out.println("There is no such item! Nothing to delete, aborting...\n");
        }
    }

    public Item getItemById(String id) throws ItemNotFoundException {
        for (Item item : this.itemList) {
            if (id.equals(item.getItemId())) {
                return item;
            }
        }
        throw new ItemNotFoundException("This item cannot be find in the list");
    }

    public void markAvailable(String id) throws ItemNotFoundException {
        getItemById(id).setAsAvailable();
    }

    public void markUnavailable(String id) throws ItemNotFoundException {
        getItemById(id).setAsNotAvailable();
    }

    public int getListSize() {
        return itemList.size();
    }

    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    public String showList() {
        StringBuilder listOfUsers = new StringBuilder();
        for (Item item : this.itemList) {
            listOfUsers.append(item.toString()).append(System.lineSeparator());
        }
        return listOfUsers.toString();
    }
}
