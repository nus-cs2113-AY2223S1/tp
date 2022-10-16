package seedu.duke.item;

import seedu.duke.exception.InvalidItemException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.transaction.TransactionList;

import java.util.ArrayList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_UNAVAILABLE;

public class ItemList {
    private final ArrayList<Item> itemList;

    public ItemList() { // store files from data.txt
        // this.itemList = fileItems;
        this.itemList = new ArrayList<>();
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

    public void deleteItem(String itemId, TransactionList transactionList)
            throws ItemNotFoundException, InvalidItemException {
        Item item = getItemById(itemId);
        if (item.isAvailable(transactionList)) {
            itemList.remove(item);
        } else {
            throw new InvalidItemException(MESSAGE_ITEM_UNAVAILABLE);
        }
    }

    public Item getItemById(String id) throws ItemNotFoundException {
        for (Item item : this.itemList) {
            if (id.equals(item.getItemId())) {
                return item;
            }
        }
        throw new ItemNotFoundException(MESSAGE_ITEM_NOT_FOUND);
    }

    public Item getItemByName(String name) throws ItemNotFoundException {
        for (Item item : this.itemList) {
            if (name.equals(item.getName())) {
                return item;
            }
        }
        throw new ItemNotFoundException(MESSAGE_ITEM_NOT_FOUND);
    }

    public ItemList getItemsByKeyword(String keyword) throws ItemNotFoundException{
        ItemList returnList = new ItemList();
        for (Item item : this.itemList) {
            if ((item.getName()).contains(keyword)) {
                returnList.addItem(item);
            }
        }
        if(returnList.getListSize() == 0){
            throw new ItemNotFoundException(MESSAGE_ITEM_NOT_FOUND);
        }
        return returnList;
    }

    public int getListSize() {
        return itemList.size();
    }

    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    public boolean hasThisLender(String username, TransactionList transactionList) {
        for (Item item : itemList) {
            if (item.getOwnerId().equals(username) && !item.isAvailable(transactionList)) {
                return true;
            }
        }
        return false;
    }

    public void deleteAllItemOfAnUser(String username, TransactionList transactionList) {
        itemList.removeIf(item -> item.getOwnerId().equals(username) && item.isAvailable(transactionList));
    }

    public String toString(TransactionList transactionList) {
        StringBuilder listString = new StringBuilder();
        if (itemList.size() == 0) {
            listString.append("There is no item in your list right now");
        } else {
            listString.append("Here are ").append(itemList.size()).append(" item(s) in your list:");
        }
        int index = 1;
        for (Item item : itemList) {
            listString.append('\n').append("   ").append(index++).append(". ").append(item.toString(transactionList));
        }
        return String.valueOf(listString);
    }
}
