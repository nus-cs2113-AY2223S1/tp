package seedu.duke.item;

import seedu.duke.Ui;
import seedu.duke.exception.InvalidItemException;
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
        assert itemList.size() != 0  : "item not added!";
    }

    public void getItemOfAnUser(String userId) {
        itemList.stream().filter(t -> (t.getOwnerId().equals(userId))).forEach(System.out::println);
    }

    public Item getItem(String id) throws NullPointerException {
        for (Item item : itemList) {
            if (id.equals(item.getItemId())) {
                assert id.equals(item.getItemId()) : "equals function not working";
                return item;
            }
        }
        return null;
    }

    public void getItemOfSpecificCategory(int categoryNumber) {
        itemList.stream().filter(t -> (Category.setCategory(t.getCategory()) == categoryNumber))
                .forEach(System.out::println);
    }

    public void deleteItem(String itemId) throws ItemNotFoundException, InvalidItemException {
        Item item = getItemById(itemId);
        if (item.isAvailable()) {
            itemList.remove(item);
        } else {
            throw new InvalidItemException("This item is currently unavailable");
        }
    }

    public Item getItemById(String id) throws ItemNotFoundException {
        for (Item item : this.itemList) {
            if (id.equals(item.getName())) {
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

    public boolean hasThisLender(String username) {
        for (Item item : itemList) {
            if (item.getOwnerId().equals(username) && !item.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public void deleteAllItemOfAnUser(String username) {
        itemList.removeIf(item -> item.getOwnerId().equals(username) && !item.isAvailable());
    }

    public String showList() {
        StringBuilder listOfUsers = new StringBuilder();
        for (Item item : this.itemList) {
            listOfUsers.append(item.toString()).append(System.lineSeparator());
        }
        return listOfUsers.toString();
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();
        if (itemList.size() == 0) {
            listString.append("There is no item in your list right now");
        } else {
            listString.append("Here are ").append(itemList.size()).append(" item(s) in your list:");
        }
        int index = 1;
        for (Item item : itemList) {
            listString.append('\n').append("   ").append(index++).append(". ").append(item);
        }
        return String.valueOf(listString);
    }
}
