package seedu.moneygowhere.common;

/**
 * Defines the messages used by program.
 */
public class Messages {
    public static final String CONSOLE_MESSAGE_GREETING = "Your MoneyGoWhere? Let me help you find it.";
    public static final String CONSOLE_MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String CONSOLE_ERROR_COMMAND_NOT_FOUND = "The command entered is invalid.";
    public static final String CONSOLE_MESSAGE_COMMAND_ADD_EXPENSE_SUCCESS = "The expense was added successfully.";
    public static final String CONSOLE_ERROR_COMMAND_ADD_EXPENSE_INVALID = ""
            + "The arguments entered are invalid. "
            + "SYNTAX: Add-Expense -n NAME -a AMOUNT [-d "
            + Configurations.CONSOLE_INTERFACE_DATE_TIME_INPUT_FORMAT
            + "] [-t DESCRIPTION] [-c CATEGORY]";
    public static final String CONSOLE_ERROR_COMMAND_VIEW_EXPENSE_INVALID = ""
            + "The arguments entered are invalid. "
            + "SYNTAX: View-Expense [-e EXPENSE_NUMBER]";
    public static final String CONSOLE_MESSAGE_COMMAND_DELETE_EXPENSE_SUCCESS = "The expense was deleted successfully.";
    public static final String CONSOLE_ERROR_COMMAND_DELETE_EXPENSE_INVALID = ""
            + "The arguments entered are invalid. "
            + "SYNTAX: Delete-Expense [-e EXPENSE_NUMBER]";
    public static final String CONSOLE_ERROR_COMMAND_SORT_EXPENSE_INVALID = ""
            + "The arguments entered are invalid. "
            + "SYNTAX: Sort-Expense [-t TYPE]";
    public static final String CONSOLE_MESSAGE_COMMAND_SORTED_EXPENSE_SUCCESS =
            "Your expenses have been sorted successfully";
}
