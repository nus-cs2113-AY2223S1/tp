package seedu.duke.data.transaction;

import java.time.LocalDate;

public class Transaction {
    private String description;
    private int amount;
    private String category; // category of income or expense
    private LocalDate date;

    public Transaction(String description, int amount, String category, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
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

    @Override
    public String toString() {
        return "Date: " + date + " Category: " + category + " Description: " + description
                + " Amount: " + amount;
    }
}