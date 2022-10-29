package seedu.duke.item;

import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.id.IdGenerator;
import seedu.duke.transaction.TransactionList;

//@@author jingwei55
public class Item {
    private final String itemId;
    private final String name;
    private final String ownerId;
    private final double pricePerDay;
    private final Category.Categories category;

    public Item(String name, int categoryNumber, double price, String ownerId) throws InvalidCategoryException {
        this.itemId = IdGenerator.generateId();
        this.name = name;
        this.pricePerDay = price;
        this.ownerId = ownerId;
        this.category = Category.mapCategory(categoryNumber);
    }

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

    public String getStatus(TransactionList transactionList) {
        return (this.isAvailable(transactionList) ? "YES" : "NO");
    }

    public String toString(TransactionList transactionList) {
        String itemId = "ItemId: " + this.itemId + "\n";
        String itemIcon =
                "[" + (isAvailable(transactionList) ? "Available" : "On loan") + "] ";
        String itemName = "   Item name: " + name + "\n";
        String itemCategory = "   Category: " + category.toString() + "\n";
        String itemOwner = "   Owner: " + getOwnerId() + "\n";
        String itemPrice = "   PricePerDay: $" + pricePerDay;
        return itemIcon + itemId + itemName + itemCategory + itemOwner + itemPrice;
    }

    public String convertItemToFileFormat() {
        String separator = " | ";
        int checkSum = toString(new TransactionList()).length();
        return itemId + separator + name + separator + pricePerDay + separator + ownerId
                + separator + Category.setCategory(category) + separator + checkSum;
    }
}
