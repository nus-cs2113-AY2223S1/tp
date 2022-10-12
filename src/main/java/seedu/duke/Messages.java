package seedu.duke;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_WELCOME = "Welcome to Property Manager! How may I help you?";

    public static final String MESSAGE_PROPERTY_ADDED = "Adding a property with the following information:";

    public static final String MESSAGE_CLIENT_ADDED = "Adding a client with the following information:";

    public static final String MESSAGE_CLIENT_DELETED = "Deleting a client with the following information:";

    public static final String MESSAGE_PAIRED = "Pairing the following client and property: ";

    public static final String MESSAGE_UNPAIRED = "Unpairing the following client and property: ";

    public static final String MESSAGE_EXISTING_PAIR = "OOPS!! This property is currently rented by a tenant, "
            + "try pairing with another property";

    public static final String MESSAGE_NO_EXISTING_PAIR = "OOPS!! This property is not being rented by a tenant. "
            + "Unpair unsuccessful";


    /* Add Property/Client Related Error Messages */

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


    /* Delete Client Related Error Messages */

    public static final String MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_DELETE = "OOPS!!! To delete, "
            + "please specify sub-command type.\n"
            + "For client: add -client\n"
            + "For property: add -property";

    public static final String MESSAGE_EMPTY_DELETE_DESCRIPTION = "OOPS!!! The index for delete cannot be empty.";

    public static final String MESSAGE_INVALID_CLIENT_INDEX = "OOPS!!! Please enter a valid client index.";

    public static final String MESSAGE_EMPTY_CLIENT_INDEX = "OOPS!!! The client index to delete cannot be empty.";


    /* Pair/Unpair Related Error Messages */

    public static final String MESSAGE_EMPTY_COMMAND_PAIR_UNPAIR = "OOPS!!! The description of a pair/unpair message "
            + "cannot be empty";

    public static final String MESSAGE_NOT_VALID_INDEX = "OOPS!!! Please enter an index that appears within the "
            + "property list or client list";

    public static final String MESSAGE_NOT_INTEGER = "OOPS!!! Please enter an integer";

    public static final String MESSAGE_PAIR_UNPAIR_WRONG_FORMAT = "OOPS!!! To pair or unpair, it requires "
            + "the following format and details as shown below.";

    public static final String MESSAGE_PAIR_UNPAIR_INPUT_EXAMPLE = "Format:\n"
            + "  pair ip/PROPERTY_INDEX ic/CLIENT_INDEX\n"
            + "  unpair ip/PROPERTY_INDEX ic/CLIENT_INDEX\n"
            + "Examples:\n"
            + "  pair ip/1 ic/5\n"
            + "  unpair ip/2 ic/1";
    public static final String MESSAGE_INCORRECT_LIST_DETAILS = "OOPS!!! Please enter either -client or -property to list clients and" +
            " properties respectively";


    public static final String MESSAGE_TRY_AGAIN = "Please try again.";
}
