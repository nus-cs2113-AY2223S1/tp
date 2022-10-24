package seedu.duke.common;

/**
 * Provides enum variables for the approved date formats for input and output.
 */
public enum DateFormats {
    //@@author wcwy
    DATE_INPUT_PATTERN("ddMMyyyy"),
    DATE_OUTPUT_PATTERN("MMM dd yyyy"),
    MONTH_YEAR_OUTPUT_PATTERN("MMM yyyy"),
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
}