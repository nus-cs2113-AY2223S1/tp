package seedu.duke;

public abstract class Item {
    public String name;
    public String id;
    public String owner;
    public int price;
    //    public enum category;
    public boolean isAvailable;

    public Item(String name, int price, boolean status, String owner) {
        this.name = name;
        this.price = price;
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

    public String getStatus() {
        return (this.isAvailable ? "YES" : "NO");
    }

    public void updatePrice(int newPrice) {
        this.price = newPrice;
    }



    public abstract String fileFormat();

}