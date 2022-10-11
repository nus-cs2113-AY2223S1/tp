package seedu.duke;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_PROPERTY_ADDED = "Adding a property with the following information:";

    public static final String MESSAGE_CLIENT_ADDED = "Adding a client with the following information:";

    public static final String MESSAGE_EMPTY_ADD_DESCRIPTION = "OOPS!!! The description for add cannot be empty.";

    public static final String MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_ADD = "OOPS!!! To add, "
            + "please specify sub-command type.\n"
            + "For client: add -client\n"
            + "For property: add -property";

    public static final String MESSAGE_EMPTY_PROPERTY_DESCRIPTION = "OOPS!!! The description of property "
            + "cannot be empty.";

    public static final String MESSAGE_EMPTY_CLIENT_DESCRIPTION = "OOPS!!! The description of client cannot be empty.";

    public static final String MESSAGE_ADD_PROPERTY_WRONG_FORMAT = "OOPS!!! To add a property, it requires "
            + "the following format and details as shown below.";

    public static final String MESSAGE_PROPERTY_INPUT_EXAMPLE = "Format: add -property n/NAME a/ADDRESS p/PRICE t/TYPE"
            + "\nExample: add -property n/Bob Tan Bee Bee a/25 Lower Kent Ridge Rd, Singapore 119081 "
            + "p/1000 t/HDB 3 Room";

    public static final String MESSAGE_ADD_CLIENT_WRONG_FORMAT = "OOPS!!! To add a client, it requires "
            + "the following format and details as shown below.";

    public static final String MESSAGE_CLIENT_INPUT_EXAMPLE = "Format: add -client n/NAME c/CONTACT_NUMBER e/EMAIL "
            + "b/BUDGET_MONTH\n"
            + "Example: add -client n/Doja Cat c/93437878 e/doja88@example.com b/2000\n"
            + "Note: Email is optional";

    public static final String MESSAGE_TRY_AGAIN = "Please try again.";

    public static final String MESSAGE_INVALID_SINGAPORE_ADDRESS = "OOPS!!! The address provided is invalid. "
            + "To add a property, A valid Singapore address must be provided\nwith the following "
            + "format and details as shown below.";

    public static final String MESSAGE_VALID_SINGAPORE_ADDRESS_EXAMPLE = "LANDED PROPERTY:\n  Format:  "
            + "[Unit Number]<space>[Street Name],<space>Singapore<space>[Postal Code]\n"
            + "  Example: 60 Aria Street, Singapore 602580\n"
            + "BUILDINGS (e.g. HDBs, apartments, condominiums):\n"
            + "  Format (Without Building Name):\n  [Block Number]<space>[Street Name]<space>"
            + "#[Unit Level]-[Unit Number]{<space>[Building Name]},<space>Singapore<space>[Postal Code]\n"
            + "  Example: 101 Marlow Street #12-05, Singapore 059020\n"
            + "  Example (With Building Name): 101 Marlow Street #12-05 Clife Parkview, Singapore 059020\n"
            + "Note: Format is <space> sensitive; [Detail] must be provided; {Detail} is optional\n"
            + "Any deviation from format will lead to invalid address.";

    public static final String MESSAGE_INVALID_PRICE_FORMAT = "OOPS!!! Please enter positive number "
            + "(No letter/symbols, etc) for renting price per month for property";

    public static final String MESSAGE_INVALID_CONTACT_NUMBER = "OOPS!!! Please enter a valid Singapore Contact Number "
            + "(No extension)";

    public static final String MESSAGE_INVALID_EMAIL = "OOPS!!! Please enter a valid Email Address";

    public static final String MESSAGE_INVALID_BUDGET_FORMAT = "OOPS!!! Please enter positive number "
            + "(No letter/symbols, etc) for budget";
}
