package seedu.duke.transaction;

/**
 * Represents the expense of the user added to the application.
 * Records the amount, category and the date of spending.
 */
public class Expense extends Transaction {
    public Expense(String description, int amount, String category, int day, int month, int year) {
        super(description, amount, category, day, month, year);
    }
}
