package seedu.duke.common;

/**
 * Provides enum variables for storing custom program error messages.
 */
public enum ErrorMessages {
    ERROR_GLOBAL_INVALID_COMMAND("Invalid command, please enter <help> for the command guide."),
    ERROR_GLOBAL_DUPLICATE_TAG("Duplicate tag(s) detected, please enter <help> for the command guide."),
    ERROR_GLOBAL_MISSING_TAG("Mandatory tag(s) missing, please enter <help> for the command guide."),
    ERROR_GLOBAL_MISSING_TAG_YEAR("Month tag must be accompanied by a year tag, please enter <help> for the "
            + "command guide."),
    ERROR_GLOBAL_MISSING_TAG_PERIODNUMBER("Period and number tags must be provided together, "
            + "please enter <help> for the command guide."),
    ERROR_GLOBAL_UNSUPPORTED_TAG("Not supported tag(s) detected, please enter <help> for the command guide."),
    ERROR_GLOBAL_UNSUPPORTED_TAG_COMBINATION("These tag(s) and/or parameter(s) are incorrectly used, please enter "
            + "<help> for the command guide."),
    ERROR_GLOBAL_EMPTY_PARAMETER("Parameter behind tag(s) is found to be empty, "
            + "please enter <help> for the command guide."),
    ERROR_GLOBAL_INVALID_INDEX("The index of the transaction does not exist, "
            + "please enter <help> for the command guide."),
    ERROR_GLOBAL_INVALID_MONTH("Invalid month! (Note: Month should be between 1 to 12) "
            + "Please enter <help> for the command guide."),
    ERROR_GLOBAL_INVALID_YEAR("Invalid year! (Note: Year should be 1000 - 9999) "
            + "Please enter <help> for the command guide."),
    ERROR_GLOBAL_INVALID_PERIOD("Type of period given is invalid, please enter <help> for the command guide."),
    ERROR_GLOBAL_INVALID_NUMBER("Number for period stats given is invalid, "
            + "please enter <help> for the command guide."),
    ERROR_GLOBAL_NUMBER_NOT_NUMERIC("Non-integer detected, please enter <help> for the command guide."),
    ERROR_GLOBAL_INDEX_NOT_NUMERIC("Non-numerical index detected, please enter <help> for the command guide."),
    ERROR_TRANSACTION_INVALID_AMOUNT("Invalid amount! (Note: Amount must be positive integer, $10000000 or less only) "
            + "Please enter <help> for the command guide."),
    ERROR_TRANSACTION_INVALID_CATEGORY("Invalid category, please enter <help> for the command guide."),
    ERROR_TRANSACTION_INVALID_DATE("Invalid date, please enter <help> for the command guide."),
    ERROR_TRANSACTION_INVALID_TYPE("Type of transaction given is invalid, please enter <help> for the command guide."),
    ERROR_STATS_COMMAND_INVALID_TYPE("Type of statistics given is invalid, please enter <help> for the command guide."),
    ERROR_STATS_INVALID_NUMBER("Invalid number! (Note: Stats number must between 1 - 100) "
            + "Please enter <help> for the command guide."),
    ERROR_UNKNOWN_HELP_OPTION("The parameter used for help option is unknown, "
            + "please enter <help> for the command guide."),
    ERROR_UNKNOWN_HELP_COMMAND_WORD("The command word queried is not a valid command, "
            + "please enter <help> to list all available commands."),
    ERROR_STORAGE_TRANSACTION_CORRUPTED("Transaction values corrupted."
            + " To PREVENT DATA OVERWRITE, please STOP the program and EDIT your duke.txt file correctly."),
    ERROR_STORAGE_BUDGET_CORRUPTED("Budget parameter is invalid or missing."
            + " To PREVENT DATA OVERWRITE, please STOP the program and EDIT your duke.txt file correctly."),
    ERROR_STORAGE_WRITE("Unable to write to Duke.txt. Please save your current Duke.txt file and restart Moolah"),
    ERROR_MAXIMUM_TRANSACTION_COUNT_REACHED("Unable to add transaction. "
            + "The maximum allowed transaction size (1000000) has been reached."),

    ERROR_INVALID_BUDGET("Invalid budget amount! (Note: Budget must be a positive whole number of valid range)"
            + " Please enter <help> for the command guide."),
    ERROR_DUPLICATE_BUDGET("Provided budget is the same as the originally set value."),

    ERROR_EDIT_TAGS_EMPTY("Your tags are empty. Please enter <help> for the command guide."),

    ERROR_EDIT_UNCHANGED("Your tags inputted does not edit the transaction.");

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