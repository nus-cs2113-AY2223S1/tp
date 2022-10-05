package seedu.duke.common;

/**
 * Provides enum variables for storing custom program information messages.
 */
public enum InfoMessages {
    MESSAGE_INFO_DIVIDER("____________________________________________________________"),
    MESSAGE_INFO_GREET("Hello! I'm your Moolah Manager!"),
    MESSAGE_INFO_HELP_PROMPT("Enter <help> if you need the list of commands."),
    MESSAGE_INFO_HELP_GREET("I'm here to help. Here are the possible commands!"),
    MESSAGE_INFO_LIST_EMPTY("There are no records of your transactions found."),
    MESSAGE_INFO_LIST("Below are the records of your transactions:"),
    MESSAGE_INFO_TRANSACTION_COUNT("You have %d transactions in your transaction history."),
    MESSAGE_INFO_EXIT("Goodbye and see you soon."),

    MESSAGE_INFO_WARNING("MOOOOOO.... Are you sure you want to proceed with this command? Please enter Y to confirm.");
    public final String message;

    /**
     * Instantiates a new information message when user initialises a new instance of this enum.
     *
     * @param message A string containing the message.
     */
    InfoMessages(String message) {
        this.message = message;
    }

    /**
     * Gets the information message as a string.
     *
     * @return A string containing the message.
     */
    public String toString() {
        return message;
    }
}