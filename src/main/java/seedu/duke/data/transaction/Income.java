package seedu.duke.data.transaction;

import java.time.LocalDate;

/**
 * Represents the income of the user added to the application.
 * Records the amount, category and date of the income.
 */
public class Income extends Transaction {
    //@@author wcwy
    public static final String TRANSACTION_NAME = "income";
    private static final String ICON_INCOME = "[+]";

    public Income(String description, int amount, String category, LocalDate date) {
        super(description, amount, category, date);
    }

    public String getType() {
        return TRANSACTION_NAME;
    }

    public String getIcon() {
        return ICON_INCOME;
    }

    @Override
    public String toString() {
        return getIcon() + super.toString();
    }
}
