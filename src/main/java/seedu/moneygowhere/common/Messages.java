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
}
