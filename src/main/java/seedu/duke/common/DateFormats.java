package seedu.duke.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@@author wcwy

/**
 * Provides enum variables for the approved date formats for input and output.
 */
public enum DateFormats {
    DATE_INPUT_PATTERN("ddMMyyyy"),
    DATE_OUTPUT_PATTERN("MMM dd yyyy"),
    DATE_MONTH_PATTERN("MMM yyyy"),
    DATE_STORAGE_OUTPUT_PATTERN("yyyy-MM-dd");

    public final String message;

    /**
     * Instantiates a new date format when application initialises a new instance of this enum.
     *
     * @param message A string containing the date format.
     */
    DateFormats(String message) {
        this.message = message;
    }

    /**
     * Gets the date format as a string.
     *
     * @return A string containing the date format.
     */
    public String toString() {
        return message;
    }

    /**
     * Retrieves a formatted string containing the month and year of a date.
     *
     * @param date Date of the transaction to be considered for the budget.
     * @return A string containing the formatted output for month and year.
     */
    public static String retrieveFormattedMonthAndYear(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_MONTH_PATTERN.toString());
        return date.format(formatter);
    }
}