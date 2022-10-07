package seedu.duke.common;

/**
 * Provides enum variables for storing custom program error messages.
 */
public enum ErrorMessages {
    ERROR_INVALID_COMMAND("Invalid command, please enter <help> if you need the list of commands."),
    ERROR_ADD_COMMAND_AMOUNT_NOT_NUMERIC("Non-Numeric input detected, please enter a numerical amount!"),
    ERROR_ADD_COMMAND_INVALID_CATEGORY("Invalid category, please ensure your category is correct!"),
    ERROR_ADD_COMMAND_INVALID_DATE("Invalid date, please ensure your date format is correct!"),
    ERROR_ADD_COMMAND_INVALID_INDEX("Invalid index, please ensure your index is correct!"),
    ERROR_ADD_COMMAND_INVALID_TYPE("Type of transaction given is invalid, please check your input!"),
    ERROR_ADD_COMMAND_MISSING_TAG("Mandatory tag(s) missing, please check your input!"),
    ERROR_FIND_COMMAND_MISSING_KEYWORDS("Keyword(s) for your search expression missing, please check your input!");

    public final String message;

    /**
     * Instantiates a new error message when user initialises a new instance of this enum.
     *
     * @param message A string containing the message.
     */
    ErrorMessages(String message) {
        this.message = message;
    }

    /**
     * Gets the error message as a string.
     *
     * @return A string containing the message.
     */
    public String toString() {
        return message;
    }
}