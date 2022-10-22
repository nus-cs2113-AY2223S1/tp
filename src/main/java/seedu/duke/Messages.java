package seedu.duke;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String EXCEPTION = "EXCEPTION";


    /* Confirmation Messages */

    public static final String MESSAGE_WELCOME = "Welcome to Property Manager! How may I help you?";

    public static final String MESSAGE_COMMAND_UNDEFINED = "OOPS!!! Command not recognised. Try again.";

    public static final String MESSAGE_NUMBER_OF_LIST_RESULTS = "Number of entries in the list: ";

    public static final String MESSAGE_PROPERTY_ADDED = "Adding a property with the following information:";

    public static final String MESSAGE_PROPERTY_DELETED = "Deleting property with the following information:";

    public static final String MESSAGE_CLIENT_ADDED = "Adding a client with the following information:";

    public static final String MESSAGE_CLIENT_DELETED = "Deleting client with the following information:";

    public static final String MESSAGE_PAIRED = "Pairing the following client and property: ";

    public static final String MESSAGE_UNPAIRED = "Unpairing the following client and property: ";

    public static final String MESSAGE_PAIRED_PROPERTIES_DELETED = "\nThe pairing this client has with "
            + "the following property will be deleted:";

    public static final String MESSAGE_PAIRED_CLIENTS_DELETED = "\nThe pairing(s) this property has with "
            + "the following client(s) will be deleted:";


    /* General Error Messages */

    public static final String MESSAGE_EMPTY_DESCRIPTION = "OOPS!!! The description for this command cannot be empty.";

    public static final String MESSAGE_MISSING_SUB_COMMAND_TYPE = "OOPS!!! Please specify sub-command type.\n"
            + "For client: <command> -client\n"
            + "For property: <command> -property";

    public static final String MESSAGE_INVALID_INDEX = "OOPS!!! Please enter an index that appears within the "
            + "property list or client list";

    public static final String MESSAGE_NOT_INTEGER = "OOPS!! Please enter a positive integer as index";


    /* Add Property/Client Related Error Messages */

    public static final String MESSAGE_ADD_PROPERTY_WRONG_FORMAT = "OOPS!!! To add a property, it requires "
            + "the following format and details:\n"
            + "Format: add -property n/NAME a/ADDRESS p/PRICE t/TYPE\n"
            + "Example: add -property n/Bob Tan Bee Bee a/25 Lower Kent Ridge Rd, Singapore 119081 "
            + "p/1000 t/HDB 3 Room";

    public static final String MESSAGE_ADD_CLIENT_WRONG_FORMAT = "OOPS!!! To add a client, it requires "
            + "the following format and details:\n"
            + "Format: add -client n/NAME c/CONTACT_NUMBER e/EMAIL "
            + "b/BUDGET_MONTH\n"
            + "Example: add -client n/Doja Cat c/93437878 e/doja88@example.com b/2000\n"
            + "Note: Email is optional";

    public static final String MESSAGE_INVALID_SINGAPORE_ADDRESS = "OOPS!!! The address provided is invalid. "
            + "To add a property, A valid Singapore address must be provided\nwith the following "
            + "format and details:\n"
            + "LANDED PROPERTY:\n  Format:  "
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


    /* Delete Property/Client Related Error Messages */

    public static final String MESSAGE_DELETE_CLIENT_WRONG_FORMAT = "OOPS!!! Please use this format to delete client:\n"
            + "delete -client ic/CLIENT_INDEX\n";

    public static final String MESSAGE_DELETE_PROPERTY_WRONG_FORMAT = "OOPS!!! Please use this format to delete "
            + "property\n"
            + "delete -property ip/PROPERTY_INDEX";


    /* Pair/Unpair Related Error Messages */

    public static final String MESSAGE_PAIR_WRONG_FORMAT = "OOPS!!! To pair, please follow the following format:\n"
            + "  pair ip/PROPERTY_INDEX ic/CLIENT_INDEX\n"
            + "Example:\n"
            + "  pair ip/1 ic/5\n";

    public static final String MESSAGE_UNPAIR_WRONG_FORMAT = "OOPS!!! To unpair, please follow the following format:\n"
            + "  unpair ip/PROPERTY_INDEX ic/CLIENT_INDEX\n"
            + "Example:\n"
            + "  unpair ip/2 ic/1";


    public static final String MESSAGE_CLIENT_ALREADY_PAIRED = "OOPS!! This client is currently renting a property, "
            + "try pairing with another client ";

    public static final String MESSAGE_EXISTING_PAIR = "OOPS!! This client and this property are already paired "
            + "together. You don't need to pair them again.";

    public static final String MESSAGE_BUDGET_EXCEEDED = "OOPS!! The rental price exceeds the client's budget. "
            + "Pair unsuccessful.";

    public static final String MESSAGE_NO_EXISTING_PAIR = "OOPS!! This property is not being rented by the tenant. "
            + "Unpair unsuccessful.";



    /* Check property/client related Error Messages */

    public static final String MESSAGE_CHECK_PROPERTY_RESULT = "Here are the tenants renting this property:";

    public static final String MESSAGE_CHECK_PROPERTY_WRONG_FORMAT = "OOPS!! To check property, please use the "
            + "following format:\n"
            + "  check -property ip/INDEX\n"
            + "Example:\n"
            + "  check -property ip/5";

    public static final String MESSAGE_TRY_AGAIN = "Please try again.";


    /* List related Error Messages */

    public static final String MESSAGE_INCORRECT_LIST_DETAILS = "OOPS!!! Please enter -client"
            + " to list clients, -property to list properties, and -everything to list"
            + " everything";

    public static final String LINE_BREAK = "----------------------------------------------------------------------"
            + "----------";

    public static final String MESSAGE_BYE_PARAMETERS_PRESENT = "Please type bye without any parameters"
            + " if you would like to quit";

    public static final String MESSAGE_INCORRECT_PROPERTY_LIST_FLAG = "Please type the following "
            + "after list -property to display - "
            + "\na/ for property address"
            + "\nt/ for property type"
            + "\nn/ for property owner name"
            + "\nt/ for property type";

    public static final String MESSAGE_INCORRECT_CLIENT_LIST_FLAG = "Please type the following "
            + "after list -client to display - "
            + "\nn/ for client name"
            + "\nc/ for client contact number"
            + "\ne/ for client email"
            + "\nb/ for client budget";
    public static final String MESSAGE_BYE = "Goodbye :). See you soon!";


    /* Error message for Find Function */
    public static final String MESSAGE_FIND_INVALID_FLAG = "OOPS!!! Please ensure you only have 'f/' flag.";

    public static final String MESSAGE_NO_MATCHES = "There is no client that fits within your query.";
}
