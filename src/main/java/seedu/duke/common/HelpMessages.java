package seedu.duke.common;

import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.BudgetCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.StatsCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.PurgeCommand;

import static seedu.duke.common.InfoMessages.LINE_SEPARATOR;

/**
 * Provides enum variables for storing help messages across all command classes.
 */
public enum HelpMessages {
    // The description for the usage of command
    COMMAND_DESCRIPTION_ADD("Add a new transaction entry, which could be either an \"expense\" or \"income\" into "
            + "the transactions list."),
    COMMAND_DESCRIPTION_BUDGET("Set the amount for monthly budget, with a value from 1 to 10^13 (Ten Trillion)."),
    COMMAND_DESCRIPTION_BYE("Exit the application."),
    COMMAND_DESCRIPTION_DELETE("Delete a transaction entry from the unfiltered list of transactions."),
    COMMAND_DESCRIPTION_EDIT("Edit a transaction entry from the unfiltered list of transactions."),
    COMMAND_DESCRIPTION_FIND("Find a specific or multiple transactions based on any keyword that have been "
            + "specified."),
    COMMAND_DESCRIPTION_HELP("Display basic or detailed help information explaining the commands available in the "
            + "application."),
    COMMAND_DESCRIPTION_LIST("List all or some transactions based on selection by the ascending order of date of"
            + " transaction." + LINE_SEPARATOR
            + "If tag filters are used, the transactions retrieved from the records must match all the filter tags"
            + LINE_SEPARATOR + "that have been specified in order to be recognized as a valid record."),
    COMMAND_DESCRIPTION_PURGE("Delete all transaction entries from the list of transactions. User must enter 'Y' "
            + "to confirm the purge."),
    COMMAND_DESCRIPTION_STATS("View financial insights such as categorical savings and periodic expenditure based on "
            + "the transaction entries in the application."),

    // The guiding information for the usage of command
    COMMAND_USAGE_ADD("add t/TYPE c/CATEGORY a/AMOUNT d/DATE i/DESCRIPTION"),
    COMMAND_USAGE_BUDGET("budget b/BUDGET"),
    COMMAND_USAGE_BYE("bye"),
    COMMAND_USAGE_DELETE("delete e/ENTRY"),
    COMMAND_USAGE_EDIT("edit e/ENTRY [t/TYPE] [c/CATEGORY] [a/AMOUNT] [d/DATE] [i/DESCRIPTION]"),
    COMMAND_USAGE_FIND("find k/KEYWORD"),
    COMMAND_USAGE_HELP("help [o/detailed] [q/COMMAND]"),
    COMMAND_USAGE_LIST(LINE_SEPARATOR
            + "\t(1) Basic listing: list [t/TYPE] [c/CATEGORY] [d/DATE]" + LINE_SEPARATOR
            + "\t(2) Listing on specific month or year: list [t/TYPE] [c/CATEGORY] [d/DATE] [m/MONTH] y/YEAR"
            + LINE_SEPARATOR
            + "\t(3) Listing on last N days, weeks or months: list [t/TYPE] [c/CATEGORY] [d/DATE] p/PERIOD n/NUMBER"),
    COMMAND_USAGE_PURGE("purge"),
    COMMAND_USAGE_STATS(LINE_SEPARATOR
            + "\t(1) Categorical savings: stats s/categorical_savings" + LINE_SEPARATOR
            + "\t(2) Monthly expenditure: stats s/monthly_expenditure" + LINE_SEPARATOR
            + "\t(3) Insights on specific month or year: stats s/time_insights y/YEAR [m/MONTH]" + LINE_SEPARATOR
            + "\t(4) Insights on last N days, weeks or months: stats s/time_insights p/PERIOD n/NUMBER"),

    // The formatting information for the parameters used by the command
    COMMAND_PARAMETERS_AMOUNT("- AMOUNT: The amount for the transaction. It is a positive whole number ranging "
            + "from 1 to 10000000 (Ten Million)."),
    COMMAND_PARAMETERS_BUDGET("- BUDGET: An estimate of expense for every month. It is a "
            + "positive whole number ranging from 1 to 10^13 (Ten Trillion)."),
    COMMAND_PARAMETERS_CATEGORY("- CATEGORY: A category for the transaction. "
            + "It can be any word without numeral, symbol or spacing."),
    COMMAND_PARAMETERS_COMMAND("- COMMAND: A case-insensitive command word to search for."),
    COMMAND_PARAMETERS_DATE("- DATE: The date when the transaction took place on. It must be in ddMMyyyy "
            + "format, e.g. 29102022."),
    COMMAND_PARAMETERS_DESCRIPTION("- DESCRIPTION: More information regarding the transaction. "
            + "It can be any word without any spacing."),
    COMMAND_PARAMETERS_DETAILED("- detailed: A detailed version of the guide."),
    COMMAND_PARAMETERS_ENTRY("- ENTRY: A list entry value for the transaction. It is a positive whole number "
            + "ranging from 1 to 1000000."),
    COMMAND_PARAMETERS_KEYWORD("- KEYWORD: A case-insensitive word that matches the partial or the full description "
            + "of a transaction entry. It can be any word without any spacing."),
    COMMAND_PARAMETERS_MONTH("- MONTH: The month which the transaction falls on. It is a positive whole number ranging "
            + "from 1 to 12, where 1 represents January." + LINE_SEPARATOR
            + "\t\t<!> - MONTH parameter must be used together with the YEAR parameter."),
    COMMAND_PARAMETERS_NUMBER("- NUMBER: The last N number of days, weeks or months. "
            + "It is a positive whole number ranging from 1 to 100." + LINE_SEPARATOR
            + "\t\t<!> - NUMBER parameter must be used together with the PERIOD parameter."),
    COMMAND_PARAMETERS_PERIOD("- PERIOD: The period which the transaction falls on. "
            + "It should either be days, weeks or months." + LINE_SEPARATOR
            + "\t\t<!> - PERIOD parameter must be used together with the NUMBER parameter."),

    COMMAND_PARAMETERS_TYPE("- TYPE: The type of transaction. It should either be \"expense\" or \"income\"."),
    COMMAND_PARAMETERS_YEAR("- YEAR: The year which the transaction falls on. It is a positive whole number ranging "
            + "from 0 to 9999, where 0 represents Year 0."),
    COMMAND_HEADER_USAGE("Usage: "),
    COMMAND_HEADER_USAGES("Usages:"),
    COMMAND_HEADER_COMMAND_WORD("Command Word: "),
    COMMAND_HEADER_PARAMETERS("Parameters information:"),
    COMMAND_HEADER_EMPTY_PARAMETER("Parameters information: -NIL-"),

    //@@author chinhan99
    ADD_COMMAND_PARAMETERS_INFO(COMMAND_HEADER_PARAMETERS.toString() + LINE_SEPARATOR
            + COMMAND_PARAMETERS_TYPE + LINE_SEPARATOR
            + COMMAND_PARAMETERS_CATEGORY + LINE_SEPARATOR
            + COMMAND_PARAMETERS_AMOUNT + LINE_SEPARATOR
            + COMMAND_PARAMETERS_DATE + LINE_SEPARATOR
            + COMMAND_PARAMETERS_DESCRIPTION),
    ADD_COMMAND_BASIC_HELP(COMMAND_HEADER_COMMAND_WORD + AddCommand.COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_ADD + LINE_SEPARATOR
            + COMMAND_HEADER_USAGE + COMMAND_USAGE_ADD + LINE_SEPARATOR),
    ADD_COMMAND_DETAILED_HELP(ADD_COMMAND_BASIC_HELP.toString() + ADD_COMMAND_PARAMETERS_INFO + LINE_SEPARATOR),

    //@@author wcwy
    BUDGET_COMMAND_PARAMETERS_INFO(COMMAND_HEADER_PARAMETERS.toString() + LINE_SEPARATOR
            + COMMAND_PARAMETERS_BUDGET),
    BUDGET_COMMAND_BASIC_HELP(COMMAND_HEADER_COMMAND_WORD + BudgetCommand.COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_BUDGET + LINE_SEPARATOR
            + COMMAND_HEADER_USAGE + COMMAND_USAGE_BUDGET + LINE_SEPARATOR),
    BUDGET_COMMAND_DETAILED_HELP(BUDGET_COMMAND_BASIC_HELP.toString() + BUDGET_COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR),

    //@@author paullowse
    BYE_COMMAND_BASIC_HELP(COMMAND_HEADER_COMMAND_WORD +  ByeCommand.COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_BYE + LINE_SEPARATOR
            + COMMAND_HEADER_USAGE + COMMAND_USAGE_BYE + LINE_SEPARATOR),
    BYE_COMMAND_DETAILED_HELP(BYE_COMMAND_BASIC_HELP.toString() + COMMAND_HEADER_EMPTY_PARAMETER + LINE_SEPARATOR),

    //@@author brian-vb
    DELETE_COMMAND_PARAMETERS_INFO(COMMAND_HEADER_PARAMETERS.toString() + LINE_SEPARATOR
            + COMMAND_PARAMETERS_ENTRY),
    DELETE_COMMAND_BASIC_HELP(COMMAND_HEADER_COMMAND_WORD + DeleteCommand.COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_DELETE + LINE_SEPARATOR
            + COMMAND_HEADER_USAGE + COMMAND_USAGE_DELETE + LINE_SEPARATOR),
    DELETE_COMMAND_DETAILED_HELP(DELETE_COMMAND_BASIC_HELP.toString() + DELETE_COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR),

    //@@author brian-vb
    EDIT_COMMAND_PARAMETERS_INFO(COMMAND_HEADER_PARAMETERS.toString() + LINE_SEPARATOR
            + COMMAND_PARAMETERS_ENTRY + LINE_SEPARATOR
            + COMMAND_PARAMETERS_TYPE + LINE_SEPARATOR
            + COMMAND_PARAMETERS_CATEGORY + LINE_SEPARATOR
            + COMMAND_PARAMETERS_AMOUNT + LINE_SEPARATOR
            + COMMAND_PARAMETERS_DATE + LINE_SEPARATOR
            + COMMAND_PARAMETERS_DESCRIPTION),
    EDIT_COMMAND_BASIC_HELP(COMMAND_HEADER_COMMAND_WORD + EditCommand.COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_EDIT + LINE_SEPARATOR
            + COMMAND_HEADER_USAGE + COMMAND_USAGE_EDIT + LINE_SEPARATOR),
    EDIT_COMMAND_DETAILED_HELP(EDIT_COMMAND_BASIC_HELP.toString() + EDIT_COMMAND_PARAMETERS_INFO + LINE_SEPARATOR),

    //@@author chydarren
    FIND_COMMAND_PARAMETERS_INFO(COMMAND_HEADER_PARAMETERS.toString() + LINE_SEPARATOR
            + COMMAND_PARAMETERS_KEYWORD),
    FIND_COMMAND_BASIC_HELP(COMMAND_HEADER_COMMAND_WORD + FindCommand.COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_FIND + LINE_SEPARATOR
            + COMMAND_HEADER_USAGE + COMMAND_USAGE_FIND + LINE_SEPARATOR),
    FIND_COMMAND_DETAILED_HELP(FIND_COMMAND_BASIC_HELP.toString() + FIND_COMMAND_PARAMETERS_INFO + LINE_SEPARATOR),

    //@@author wcwy
    HELP_COMMAND_PARAMETERS_INFO(COMMAND_HEADER_PARAMETERS.toString() + LINE_SEPARATOR
            + COMMAND_PARAMETERS_DETAILED + LINE_SEPARATOR
            + COMMAND_PARAMETERS_COMMAND),
    HELP_COMMAND_BASIC_HELP(COMMAND_HEADER_COMMAND_WORD + HelpCommand.COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_HELP + LINE_SEPARATOR
            + COMMAND_HEADER_USAGE + COMMAND_USAGE_HELP + LINE_SEPARATOR),
    HELP_COMMAND_DETAILED_HELP(HELP_COMMAND_BASIC_HELP.toString() + HELP_COMMAND_PARAMETERS_INFO + LINE_SEPARATOR),

    //@@author chydarren
    LIST_COMMAND_PARAMETERS_INFO(COMMAND_HEADER_PARAMETERS.toString() + LINE_SEPARATOR
            + COMMAND_PARAMETERS_TYPE + LINE_SEPARATOR
            + COMMAND_PARAMETERS_CATEGORY + LINE_SEPARATOR
            + COMMAND_PARAMETERS_DATE + LINE_SEPARATOR
            + COMMAND_PARAMETERS_MONTH + LINE_SEPARATOR
            + COMMAND_PARAMETERS_YEAR + LINE_SEPARATOR
            + COMMAND_PARAMETERS_PERIOD + LINE_SEPARATOR
            + COMMAND_PARAMETERS_NUMBER),
    LIST_COMMAND_BASIC_HELP(COMMAND_HEADER_COMMAND_WORD + ListCommand.COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_LIST + LINE_SEPARATOR
            + COMMAND_HEADER_USAGES + COMMAND_USAGE_LIST + LINE_SEPARATOR),
    LIST_COMMAND_DETAILED_HELP(LIST_COMMAND_BASIC_HELP.toString() + LIST_COMMAND_PARAMETERS_INFO + LINE_SEPARATOR),

    //@@author brian-vb
    PURGE_COMMAND_BASIC_HELP(COMMAND_HEADER_COMMAND_WORD + PurgeCommand.COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_PURGE + LINE_SEPARATOR
            + COMMAND_HEADER_USAGE + COMMAND_USAGE_PURGE + LINE_SEPARATOR),
    PURGE_COMMAND_DETAILED_HELP(PURGE_COMMAND_BASIC_HELP.toString() + COMMAND_HEADER_EMPTY_PARAMETER + LINE_SEPARATOR),

    //@@author paullowse
    STATS_COMMAND_PARAMETERS_INFO(COMMAND_HEADER_PARAMETERS.toString() + LINE_SEPARATOR
            + COMMAND_PARAMETERS_MONTH + LINE_SEPARATOR
            + COMMAND_PARAMETERS_YEAR + LINE_SEPARATOR
            + COMMAND_PARAMETERS_PERIOD + LINE_SEPARATOR
            + COMMAND_PARAMETERS_NUMBER),
    STATS_COMMAND_BASIC_HELP(COMMAND_HEADER_COMMAND_WORD + StatsCommand.COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_STATS + LINE_SEPARATOR
            + COMMAND_HEADER_USAGES + COMMAND_USAGE_STATS + LINE_SEPARATOR),
    STATS_COMMAND_DETAILED_HELP(STATS_COMMAND_BASIC_HELP.toString() + STATS_COMMAND_PARAMETERS_INFO + LINE_SEPARATOR);

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