package seedu.duke.data.transaction;

import java.time.LocalDate;

/**
 * Represents the expense of the user added to the application.
 * Records the amount, category and the date of spending.
 */
public class Expense extends Transaction {
    public Expense(String description, int amount, String category, LocalDate date) {
        super(description, amount, category, date);
    }
}
