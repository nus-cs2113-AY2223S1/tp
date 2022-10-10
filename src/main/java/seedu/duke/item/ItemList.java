package seedu.duke.item;

import java.util.logging.Logger;

import java.util.logging.Level;

import seedu.duke.Ui;
import seedu.duke.exception.InvalidItemException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.transaction.Transaction;

import java.util.ArrayList;

public class ItemList {
    private static final Logger logger = Logger.getLogger("Foo");
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
        logger.log(Level.INFO, "getting item from item list");
        for (Item item : itemList) {
            if (id.equals(item.getItemId())) {
                assert id.equals(item.getItemId()) : "equals function not working";
                logger.log(Level.INFO, "Item found");
                return item;
            }
        }
        // exception if item not found
        logger.log(Level.WARNING, "Item not found error", new NullPointerException());
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
}
