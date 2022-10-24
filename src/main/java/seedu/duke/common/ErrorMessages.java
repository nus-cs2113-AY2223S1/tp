package seedu.duke.common;

/**
 * Provides enum variables for storing custom program error messages.
 */
public enum ErrorMessages {
    ERROR_GLOBAL_INVALID_COMMAND("Invalid command, please enter <help> if you need the list of commands."),
    ERROR_GLOBAL_DUPLICATE_TAG("Duplicate tag(s) detected, please check your input!"),
    ERROR_GLOBAL_MISSING_TAG("Mandatory tag(s) missing, please check your input!"),
    ERROR_GLOBAL_UNSUPPORTED_TAG("Not supported tag(s) detected, please check your input!"),
    ERROR_GLOBAL_EMPTY_PARAMETER("Parameter behind tag(s) is found to be empty, please check your input!"),
    ERROR_GLOBAL_INVALID_INDEX("Invalid index, please ensure your index is correct!"),
    ERROR_GLOBAL_INVALID_MONTH("Invalid month, please check your input! Note: Month should be between 1 to 12."),
    ERROR_GLOBAL_INVALID_YEAR("Invalid year, please check your input! Note: Year should be 1000 onwards."),
    ERROR_GLOBAL_INVALID_PERIOD("Type of period given is invalid, please check your input!"),
    ERROR_GLOBAL_INVALID_NUMBER("Number for period stats given is invalid, please check your input!"),
    ERROR_GLOBAL_NUMBER_NOT_NUMERIC("Non-integer detected, please enter a numerical integer!"),
    ERROR_TRANSACTION_INVALID_AMOUNT("Invalid amount, "
            + "please ensure your amount is in positive numerals ($10000000 or less) only!"),
    ERROR_TRANSACTION_INVALID_CATEGORY("Invalid category, please ensure your category is correct!"),
    ERROR_TRANSACTION_INVALID_DATE("Invalid date, please ensure your date format is correct!"),
    ERROR_TRANSACTION_INVALID_TYPE("Type of transaction given is invalid, please check your input!"),
    ERROR_FIND_COMMAND_MISSING_KEYWORDS("Keyword(s) for your search expression missing, please check your input!"),
    ERROR_STATS_COMMAND_INVALID_TYPE("Type of statistics given is invalid, please check your input!"),
    ERROR_UNKNOWN_HELP_OPTION("The parameter used for help option is unknown, please check your input!"),
    ERROR_STORAGE_FILE_CORRUPTED("Duke.txt corrupted. "
            + "To preserve data, please STOP the program and edit your data file correctly."),
    ERROR_STORAGE_WRITE("Unable to write to Duke.txt. Please save your current Duke.txt file and restart Moolah"),
    ERROR_MAXIMUM_TRANSACTION_COUNT_REACHED("Unable to add transaction. "
            + "The maximum allowed transaction size (1000000) has been reached."),

    ERROR_INVALID_BUDGET("Invalid budget amount, please ensure your amount is in positive whole number"
            + " of valid range only!"),
    ERROR_DUPLICATE_BUDGET("Provided budget is the same as the originally set value.");

    //@@author chydarren
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