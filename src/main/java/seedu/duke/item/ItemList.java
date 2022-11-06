package seedu.duke.item;

import seedu.duke.exception.DuplicateException;
import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.exception.InvalidItemException;
import seedu.duke.exception.InvalidPriceException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.UserList;

import java.math.BigDecimal;
import java.util.ArrayList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_UNAVAILABLE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NAME_LENGTH_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CATEGORY_INDEX_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_OUT_OF_RANGE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_TOO_MANY_DECIMALS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_DUPLICATE_ITEM_ID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_NOT_MATCHED;




// @@author jingwei55
public class ItemList {
    private final ArrayList<Item> itemList;
    private static final int NAME_INDEX = 0;
    private static final int CATEGORY_INDEX = 1;
    private static final int PRICE_INDEX = 2;
    private static final int OWNER_INDEX = 3;

    public ItemList() { // store files from data.txt
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
    }

    /**
     * Updates an item's price.
     *
     * @param itemId id of item to be updated
     * @param price  price to update
     * @return a new instance Item with updated fields
     * @throws ItemNotFoundException    If item id does not exist in item list
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
     * @param itemId          id of item to delete
     * @param transactionList list containing all transactions
     * @throws ItemNotFoundException If item id does not exist in item list
     * @throws InvalidItemException  If item cannot be deleted
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
     * @return Found item if it exists
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
     * Gets an item by keywords.
     *
     * @param keyword a (sub)string to query with
     * @return Found item if it exists
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
     * @param username        lender name to check with.
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
        for (Item item : itemList) {
            formattedString.append(item.convertItemToFileFormat()).append('\n');
        }
        return formattedString.toString();
    }

    //@@author bdthanh

    /**
     * Checks if an item name is valid or not.
     *
     * @param itemName The input item name
     * @throws InvalidItemException If item name is longer than 20 chars
     */
    private void checkValidName(String itemName) throws InvalidItemException {
        if (itemName.length() > 20) {
            throw new InvalidItemException(MESSAGE_NAME_LENGTH_INVALID);
        }
    }

    /**
     * Checks if a user is valid or not.
     *
     * @param userId The input name of owner
     * @throws UserNotFoundException If that user cannot be found in the list
     */
    private void checkValidOwner(UserList userList, String userId) throws UserNotFoundException {
        try {
            userList.getUserById(userId);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    /**
     * Checks if a categoryNumber is valid or not.
     *
     * @param categoryNumber The input category number
     */
    private void checkValidCategoryNumber(String categoryNumber) {
        try {
            Integer.parseInt(categoryNumber);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_CATEGORY_INDEX_FORMAT_INVALID);
        }
    }

    /**
     * Checks if a price is valid or not.
     *
     * @param price The input price
     * @throws InvalidPriceException If price value is out of range or if price has more than 2 decimal places
     */
    public void checkValidPrice(String price) throws InvalidPriceException {
        try {
            if (Double.parseDouble(price) < 0 || Double.parseDouble(price) > 10000) {
                throw new InvalidPriceException(MESSAGE_PRICE_OUT_OF_RANGE);
            } else if (BigDecimal.valueOf(Double.parseDouble(price)).scale() > 2) {
                throw new InvalidPriceException(MESSAGE_PRICE_TOO_MANY_DECIMALS);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_PRICE_FORMAT_INVALID);
        }
    }

    /**
     * Check if there is any item with the same ID.
     *
     * @param itemId The item ID to be checked
     * @throws DuplicateException If there is at least one item with the same ID
     */
    public void checkValidId(String itemId) throws DuplicateException {
        try {
            this.getItemById(itemId);
            throw new DuplicateException(MESSAGE_DUPLICATE_ITEM_ID);
        } catch (ItemNotFoundException e) {
            return;
        }
    }

    /**
     * Checks if an item name is valid or not.
     *
     * @param args The input args
     * @throws ItemNotFoundException If the item cannot be found
     */
    public void checkValidItem(String[] args) throws ItemNotFoundException {
        try {
            getItemById(args[0]);
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException(e.getMessage());
        }
    }

    private boolean hasThisItem(String itemId) {
        try {
            getItemById(itemId);
            return true;
        } catch (ItemNotFoundException e) {
            return false;
        }
    }

    /**
     * Checks if given owner and item name matches with item with that ID or not.
     *
     * @param itemId   The item ID to be checked
     * @param itemName The given item's name
     * @param owner    The given owner
     * @throws InvalidItemException  If they match
     * @throws ItemNotFoundException If item cannot be found in the list
     */
    public void checkNameOwnerOfItemMatching(String itemId, String itemName, String owner)
            throws InvalidItemException, ItemNotFoundException {
        if (hasThisItem(itemId)) {
            Item item = getItemById(itemId);
            boolean match = item.getOwnerId().equals(owner)
                    && item.getName().equals(itemName);
            if (!match) {
                throw new InvalidItemException(MESSAGE_ITEM_NOT_MATCHED);
            }
        }
    }

    /**
     * Check if all args is valid or not.
     *
     * @param args The array of input args
     * @throws UserNotFoundException If that user cannot be found in the list
     * @throws InvalidItemException  If item name is longer than 20 chars
     * @throws InvalidPriceException If price value is less than 0
     */
    public void checkValidArgsForItem(UserList userList, String[] args)
            throws UserNotFoundException, InvalidPriceException, InvalidItemException {
        checkValidName(args[NAME_INDEX]);
        checkValidCategoryNumber(args[CATEGORY_INDEX]);
        checkValidPrice(args[PRICE_INDEX]);
        checkValidOwner(userList, args[OWNER_INDEX]);
    }
}
