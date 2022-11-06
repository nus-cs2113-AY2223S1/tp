package seedu.duke.item;

import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.id.IdGenerator;
import seedu.duke.transaction.TransactionList;

// @@author jingwei55
public class Item {
    private final String itemId;
    private final String name;
    private final String ownerId;
    private final double pricePerDay;
    private final Category.Categories category;

    /**
     * Default constructor for Item.
     * 
     * @param name The name of item
     * @param categoryNumber The category number of an item
     * @param price The price of an item
     * @param ownerId The ownerId of an item
     * @throws InvalidCategoryException If given category number is invalid
     */
    public Item(String name, int categoryNumber, double price, String ownerId)
            throws InvalidCategoryException {
        this.itemId = IdGenerator.generateId();
        this.name = name;
        this.pricePerDay = price;
        this.ownerId = ownerId;
        this.category = Category.mapCategory(categoryNumber);
    }

    /**
     * Overloaded constructor for Item.
     * 
     * @param itemId The id of item
     * @param name The name of item
     * @param categoryNumber The category number of an item
     * @param price The price of an item
     * @param ownerId The ownerId of an item
     * @throws InvalidCategoryException If given category number is invalid
     */
    public Item(String itemId, String name, int categoryNumber, double price, String ownerId)
            throws InvalidCategoryException {
        this.itemId = itemId;
        this.name = name;
        this.pricePerDay = price;
        this.ownerId = ownerId;
        this.category = Category.mapCategory(categoryNumber);
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public Category.Categories getCategory() {
        return category;
    }

    public Item updatePrice(double newPricePerDay) throws InvalidCategoryException {
        return new Item(this.itemId, this.name, Category.setCategory(this.category), newPricePerDay,
                this.ownerId);
    }

    public boolean isAvailable(TransactionList transactionList) {
        return !transactionList.hasThisItemBeingBorrowed(itemId);
    }

    /**
     * Gives a readable string representation of an item.
     * 
     * @param transactionList A list of all transactions
     * @return A string representation of an item
     */
    public String toString(TransactionList transactionList) {
        String itemId = "ItemId: " + this.itemId + "\n";
        String itemIcon = "[" + (isAvailable(transactionList) ? "Available" : "On loan") + "] ";
        String itemName = "   Item name: " + name + "\n";
        String itemCategory = "   Category: " + category.toString() + "\n";
        String itemOwner = "   Owner: " + getOwnerId() + "\n";
        String itemPrice = "   PricePerDay: $" + String.format("%.2f", pricePerDay);
        return itemIcon + itemId + itemName + itemCategory + itemOwner + itemPrice;
    }

    /**
     * Converts and Item to a suitable format for file storage.
     * 
     * @return A suitable string representation of an item for storage
     */
    public String convertItemToFileFormat() {
        String separator = " | ";
        return name + separator + Category.setCategory(category) + separator
                + pricePerDay + separator + ownerId + separator + itemId;
    }
}
