package seedu.duke;

public abstract class Item {
    public String name;
    public String id;
    public String owner;
    public String createdAt;
    public float pricePerDay;
    public boolean isAvailable;
    public Category category;

    public Item(Category category, String name, float price, boolean status, String owner, String createdAt) {
        this.name = name;
        this.pricePerDay = price;
        this.isAvailable = status;
        this.owner = owner;
        this.createdAt = createdAt;
        this.category = category;
    }

    public void setLoanStatus(boolean status) {
        isAvailable = status;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setId(String putId) {
        this.id = putId;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return this.name + " loaned for: " + pricePerDay;
    }

    public String getStatus() {
        return (this.isAvailable ? "YES" : "NO");
    }

    public void updatePrice(int newPrice) {
        this.pricePerDay = newPrice;
    }

    public abstract String fileFormat();

}