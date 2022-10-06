package seedu.duke;

public abstract class Item {
    public String name;
    public String id;
    public String owner;
    public int pricePerDay;
    //    public enum category;
    public boolean isAvailable;

    enum Category {
        SPORTS_EQUIPMENT,
        TEXTBOOKS_AND_NOTES,
        ELECTRICAL_APPLIANCES,
        OTHERS
    }

    public Item(String name, int price, boolean status, String owner) {
        this.name = name;
        this.pricePerDay = price;
        this.isAvailable = status;
        this.owner = owner;
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

    public int setCategory(Category category) {
        switch (category) {
        case SPORTS_EQUIPMENT:
            return 0;
        case TEXTBOOKS_AND_NOTES:
            return 1;
        case ELECTRICAL_APPLIANCES:
            return 2;
        default:
            return 3;
        }
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