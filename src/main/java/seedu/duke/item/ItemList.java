package seedu.duke.item;

import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.exception.InvalidItemException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.transaction.TransactionList;

import java.util.ArrayList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_UNAVAILABLE;

// @@author jingwei55
public class ItemList {
    private final ArrayList<Item> itemList;

    public ItemList() { // store files from data.txt
        // this.itemList = fileItems;
        this.itemList = new ArrayList<>();
    }

    public ItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    /**
     * Adds the newly created task to the task list.
     *
     * @param item is the new task the user has created
     */
    public void addItem(Item item) {
        itemList.add(item);
        assert itemList.size() != 0 : "item not added!";
    }

    /**
     * Updates an item's price.
     * 
     * @param itemId id of item to be updated
     * @param price price to update
     * @return a new instance Item with updated fields
     * @throws ItemNotFoundException If item id does not exist in item list
     * @throws InvalidCategoryException If category does not exist
     */
    public Item updateItemPrice(String itemId, double price)
            throws ItemNotFoundException, InvalidCategoryException {
        for (int i = 0; i < this.itemList.size(); ++i) {
            Item item = this.itemList.get(i);
            if (item.getItemId().equals(itemId)) {
                Item updatedItem = item.updatePrice(price);
                this.itemList.set(i, updatedItem);
                return updatedItem;
            }
        }
        throw new ItemNotFoundException(MESSAGE_ITEM_NOT_FOUND);
    }

    /**
     * Deletes an item.
     * 
     * @param itemId id of item to delete
     * @param transactionList list containing all transactions
     * @throws ItemNotFoundException If item id does not exist in item list
     * @throws InvalidItemException If item cannot be deleted
     */
    public void deleteItem(String itemId, TransactionList transactionList)
            throws ItemNotFoundException, InvalidItemException {
        Item item = getItemById(itemId);
        if (item.isAvailable(transactionList)) {
            itemList.remove(item);
        } else {
            throw new InvalidItemException(MESSAGE_ITEM_UNAVAILABLE);
        }
    }

    /**
     * Gets an item by its id.
     * 
     * @param id id to search item list with
     * @return Found item if it exist
     * @throws ItemNotFoundException If no such item exists in item list
     */
    public Item getItemById(String id) throws ItemNotFoundException {
        for (Item item : this.itemList) {
            if (id.equals(item.getItemId())) {
                return item;
            }
        }
        throw new ItemNotFoundException(MESSAGE_ITEM_NOT_FOUND);
    }

    /**
     * Gets an item by its name.
     * 
     * @param name name to search item list with
     * @return Found item if it exist
     * @throws ItemNotFoundException If no such item exists in item list
     */
    public Item getItemByName(String name) throws ItemNotFoundException {
        for (Item item : this.itemList) {
            if (name.equals(item.getName())) {
                return item;
            }
        }
        throw new ItemNotFoundException(MESSAGE_ITEM_NOT_FOUND);
    }

    /**
     * Gets an item by keywords.
     * 
     * @param keyword a (sub)string to query with
     * @return Found item if it exist
     * @throws ItemNotFoundException If no such item exists in item list
     */
    public ItemList getItemsByKeyword(String keyword) throws ItemNotFoundException {
        ItemList returnList = new ItemList();
        for (Item item : this.itemList) {
            if ((item.getName()).toLowerCase().contains(keyword.toLowerCase())) {
                returnList.addItem(item);
            }
        }
        if (returnList.getListSize() == 0) {
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

    /**
     * Checks if an item has a given lender.
     * 
     * @param username lender name to check with.
     * @param transactionList list containing all transactions
     * @return true if a lender has borrowed this item
     */
    public boolean hasThisLender(String username, TransactionList transactionList) {
        for (Item item : itemList) {
            if (item.getOwnerId().equals(username) && !item.isAvailable(transactionList)) {
                return true;
            }
        }
        return false;
    }

    public void deleteAllItemOfAnUser(String username, TransactionList transactionList) {
        itemList.removeIf(
            item -> item.getOwnerId().equals(username) && item.isAvailable(transactionList));
    }

    /**
     * Computes a readable string representation of this list.
     * 
     * @param transactionList list containing all transactions
     * @return A string representation of transaction list
     */
    public String toString(TransactionList transactionList) {
        StringBuilder listString = new StringBuilder();
        if (itemList.size() == 0) {
            listString.append("There is no item in the list right now");
        } else {
            listString.append("Here are ").append(itemList.size()).append(" item(s) in the list:");
        }
        int index = 1;
        for (Item item : itemList) {
            listString.append('\n').append(index++).append(". ")
                    .append(item.toString(transactionList));
        }
        return String.valueOf(listString);
    }

    /**
     * Computes a suitable string representation of list for file storage.
     * 
     * @return A string representation of list for file storage
     */
    public String convertItemListToFileFormat() {
        StringBuilder formattedString = new StringBuilder();
        int checkSum = itemList.size();
        formattedString.append(checkSum).append('\n');
        for (Item item : itemList) {
            formattedString.append(item.convertItemToFileFormat()).append('\n');
        }
        return formattedString.toString();
    }
}
