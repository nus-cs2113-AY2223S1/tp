package seedu.duke.common;

/**
 * Provides enum variables for storing custom program error messages.
 */
public enum ErrorMessages {
    MESSAGE_ERROR_INVALID_COMMAND("Oops, you have entered an invalid command."),
    MESSAGE_ERROR_ADD_COMMAND_INVALID_TYPE("Type of transaction given is invalid, please check your input!"),
    MESSAGE_ERROR_ADD_COMMAND_MISSING_TAG("Mandatory tag(s) missing, please check your input!"),
    MESSAGE_ERROR_ADD_COMMAND_INVALID_CATEGORY("Invalid category, please ensure your category is correct!");

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