package seedu.duke.data.transaction;

public class Transaction {
    private String description;
    private int amount;
    private String category; //category of income or expense
    private int day;
    private int month;
    private int year;

    public Transaction(String description, int amount, String category, int day, int month, int year) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}