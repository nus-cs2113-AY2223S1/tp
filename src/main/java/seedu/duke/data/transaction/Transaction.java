package seedu.duke.data.transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static seedu.duke.common.DateFormats.DATE_OUTPUT_PATTERN;
import static seedu.duke.common.InfoMessages.DOLLAR_SIGN;

/**
 * Represents a transaction made by the user, which could be either an income or an expense.
 */
public abstract class Transaction {
    //@@author chydarren
    private static final String PREFIX_CATEGORY = "[";
    private static final String POSTFIX_CATEGORY = "]";
    private static final String SYMBOL_PIPE = "|";
    private static final String TEXT_AT = "at";
    private static final String TEXT_DESCRIPTION = "Description:";

    //@@author chinhan99
    private String category;
    private String description;
    private int amount;
    private LocalDate date;

    private String type;

    public Transaction(String description, int amount, String category, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public abstract String getType();

    public abstract String getIcon();

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    //@@author wcwy

    /**
     * Returns the date of transaction in MMM dd yyyy format.
     *
     * @return Formatted date of the transaction
     */
    public String printFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_OUTPUT_PATTERN.toString());
        return date.format(formatter);
    }

    public String printFormattedCategory() {
        return PREFIX_CATEGORY + category + POSTFIX_CATEGORY;
    }

    @Override
    public String toString() {
        return String.format("%s %s%d %s %s %s %s %s", printFormattedCategory(), DOLLAR_SIGN,
                amount, TEXT_AT, printFormattedDate(), SYMBOL_PIPE, TEXT_DESCRIPTION, description);
    }

    public void setType(String type) {
        this.type = type;
    }
}