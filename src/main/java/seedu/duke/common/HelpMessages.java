package seedu.duke.common;

/**
 * Provides enum variables for storing help messages across all command classes.
 */
public enum HelpMessages {
    // The description for the usage of command
    COMMAND_DESCRIPTION_ADD("Add a new transaction entry, which could be either an \"expense\" or \"income\" into "
            + "the transactions list."),
    COMMAND_DESCRIPTION_BUDGET("Set the amount for monthly budget, with a value from 1 to 10^13 (Ten Trillion)."),
    COMMAND_DESCRIPTION_BYE("Exit the application."),
    COMMAND_DESCRIPTION_DELETE("Delete a transaction entry from the list of transactions."),
    COMMAND_DESCRIPTION_EDIT("Edit a transaction entry from the list of transactions."),
    COMMAND_DESCRIPTION_FIND("Find a specific or multiple transactions based on any keywords that have been "
            + "specified."),
    COMMAND_DESCRIPTION_HELP("Display basic or detailed help information explaining the commands available in the "
            + "application."),
    COMMAND_DESCRIPTION_LIST("List all or some transactions based on selection. If tag filters are used, the "
            + "transactions retrieved from the records must match all the filter tags that have been specified in "
            + "order to be recognized as a valid record. The m/MONTH and y/YEAR tags should not be used together "
            + "with p/PERIOD and n/NUMBER tags."),
    COMMAND_DESCRIPTION_PURGE("Delete all transaction entries from the list of transactions."),
    COMMAND_DESCRIPTION_STATS("View financial insights such as categorical savings and periodic expenditure based on "
            + "the transaction entries in the application. The m/MONTH and y/YEAR tags should not be used together "
            + "with p/PERIOD and n/NUMBER tags."),

    // The guiding information for the usage of command
    COMMAND_USAGE_ADD("Usage: add t/TYPE c/CATEGORY a/AMOUNT d/DATE i/DESCRIPTION"),
    COMMAND_USAGE_BUDGET("Usage: budget b/BUDGET"),
    COMMAND_USAGE_BYE("Usage: bye"),
    COMMAND_USAGE_DELETE("Usage: delete e/ENTRY"),
    COMMAND_USAGE_EDIT("Usage: edit e/ENTRY [t/TYPE] [c/CATEGORY] [a/AMOUNT] [d/DATE] [i/DESCRIPTION]"),
    COMMAND_USAGE_FIND("Usage: find KEYWORDS"),
    COMMAND_USAGE_HELP("Usage: help [o/detailed]"),
    COMMAND_USAGE_LIST("Usage: list [t/TYPE] [c/CATEGORY] [d/DATE] [m/MONTH] [y/YEAR] [p/PERIOD] [n/NUMBER]"),
    COMMAND_USAGE_PURGE("Usage: purge"),
    COMMAND_USAGE_STATS("Usage: stats s/STATS_TYPE [m/MONTH] [y/YEAR] [p/PERIOD] [n/NUMBER]"),

    // The formatting information for the parameters used by the command
    COMMAND_PARAMETERS_DETAILED("- detailed: A detailed version of the guide."),
    COMMAND_PARAMETERS_TYPE("- TYPE: The type of transaction. It should either be \"expense\" or \"income\"."),
    COMMAND_PARAMETERS_CATEGORY("- CATEGORY: A category for the transaction. It is a one-word parameter "
            + "flexibly defined by the user. No numeral, symbol or spacing is allowed."),
    COMMAND_PARAMETERS_AMOUNT("- AMOUNT: The amount for the transaction. It is a positive whole number ranging "
            + "from 1 to 10000000. No alphabet, symbol or spacing is allowed."),
    COMMAND_PARAMETERS_DATE("- DATE: The date when the transaction took place on. It must be in ddMMyyyy "
            + "format, e.g. 29102022."),
    COMMAND_PARAMETERS_DESCRIPTION("- DESCRIPTION: More information regarding the transaction. It is a one-word"
            + " parameter defined by the user without any spacing."),
    COMMAND_PARAMETERS_ENTRY("- ENTRY: A list entry value for the transaction. It is a positive whole number "
            + "ranging from 1 to 1000000."),
    COMMAND_PARAMETERS_KEYWORDS("- KEYWORDS: A string that represents a single or a group of words used to find "
            + "matching transactions. Spacing is allowed."),
    COMMAND_PARAMETERS_BUDGET("- BUDGET: An estimate of income or expenditure for a set period of time. It is a "
            + "positive whole number that is from 1 to 10^13 (Ten Trillion). No alphabet, symbol or spacing is "
            + "allowed."),
    COMMAND_PARAMETERS_STATS_TYPE("- STATS_TYPE: The type of statistics. It can be \"categorical_savings\", "
            + "\"monthly_expenditure\", or \"time_insights\"."),
    COMMAND_PARAMETERS_YEAR("- YEAR: The year which the transaction falls on. It must be in yyyy format and "
            + "only year 1000 and onwards are accepted."),
    COMMAND_PARAMETERS_MONTH("- MONTH: The month which the transaction falls on. It is in numerical form, i.e. "
            + "from 1 to 12, where 1 represents January. This parameter must be used together with the YEAR "
            + "parameter."),
    COMMAND_PARAMETERS_PERIOD("- PERIOD: The period which the transaction falls on. It should either be weeks or "
            + "months. This parameter must be used together with the NUMBER parameter."),
    COMMAND_PARAMETERS_NUMBER("- NUMBER: The last N number of weeks or months. It is a positive whole number that "
            + "is from 1 to 100. This parameter must be used together with the PERIOD parameter.");

    //@@author chydarren
    public final String message;

    /**
     * Instantiates a new information message when user initialises a new instance of this enum.
     *
     * @param message A string containing the message.
     */
    HelpMessages(String message) {
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