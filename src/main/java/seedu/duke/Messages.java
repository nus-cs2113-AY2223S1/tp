package seedu.duke;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_CLIENT_ADDED = "Adding a client with the following information:";

    public static final String MESSAGE_EMPTY_ADD_DESCRIPTION = "OOPS!!! The description for add cannot be empty.";

    public static final String MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_ADD = "OOPS!!! To add, "
            + "please specify sub-command type.\n"
            + "For client: add -client\n"
            + "For property: add -property";

    public static final String MESSAGE_EMPTY_CLIENT_DESCRIPTION = "OOPS!!! The description of client cannot be empty.";

    public static final String MESSAGE_ADD_CLIENT_WRONG_FORMAT = "OOPS!!! To add a client, it requires "
            + "the following format and details as shown below.";

    public static final String MESSAGE_CLIENT_INPUT_EXAMPLE = "Format: add -client n/NAME c/CONTACT_NUMBER e/EMAIL "
            + "b/BUDGET_MONTH\n"
            + "Example: add -client n/Doja Cat c/93437878 e/doja88@example.com b/2000\n"
            + "Note: Email is optional";

    public static final String MESSAGE_TRY_AGAIN = "Please try again.";

    public static final String MESSAGE_INVALID_CONTACT_NUMBER = "OOPS!!! Please enter a valid Singapore Contact Number "
            + "(No extension)";

    public static final String MESSAGE_INVALID_EMAIL = "OOPS!!! Please enter a valid Email Address";

    public static final String MESSAGE_INVALID_BUDGET_FORMAT = "OOPS!!! Please enter positive number "
            + "(No letter/symbols, etc) for budget";

    public static final String MESSAGE_CLIENT_DELETED = "Deleting a client with the following information:";

    public static final String MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_DELETE = "OOPS!!! To delete, "
            + "please specify sub-command type.\n"
            + "For client: add -client\n"
            + "For property: add -property";
}
