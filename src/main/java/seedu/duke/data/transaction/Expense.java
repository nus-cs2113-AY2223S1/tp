package seedu.duke.data.transaction;

import java.time.LocalDate;

/**
 * Represents the expense of the user added to the application.
 * Records the amount, category and the date of spending.
 */
public class Expense extends Transaction {
    //@@author wcwy
    public static final String TRANSACTION_NAME = "expense";
    private static String ICON_EXPENSE = "[-]";

    public Expense(String description, int amount, String category, LocalDate date) {
        super(description, amount, category, date);
    }

    public String getIcon() {
        return ICON_EXPENSE;
    }

    @Override
    public String toString() {
        return getIcon() + super.toString();
    }
}
