package seedu.duke;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String EXCEPTION = "EXCEPTION";
    //@@author zoranabc201
    public static final String LINE_BREAK = "----------------------------------------------------------------------"
            + "----------";
    public static final String CLIENT = "Client:";
    public static final String PROPERTY = "Property:";
    public static final String CLIENTS = "Clients:";
    public static final String PROPERTIES = "Properties:";
    public static final String PAIRS = "Pairs:";
    //@@author
    public static final String INDENT = "        ";
    //@@author zoranabc201
    public static final String THERE_IS = "There is ";
    public static final String THERE_ARE = "There are ";
    public static final String PROPERTY_SINGULAR = " property ";
    public static final String PROPERTIES_PLURAL = " properties ";
    public static final String CLIENT_SINGULAR = " client ";
    public static final String CLIENTS_PLURAL = " clients ";
    public static final String PAIR_SINGULAR = " pair ";
    public static final String PAIRS_PLURAL = " pairs ";
    public static final String IN_THIS_LIST = "in this list";
    //@@author

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

    public static final String MESSAGE_CHECK_PROPERTY = "Showing check results for this property:";

    public static final String MESSAGE_CHECK_PROPERTY_RESULT = "Here are the tenants renting this property:";


    /* General Error Messages */

    public static final String MESSAGE_EMPTY_DESCRIPTION = "OOPS!!! The description for this command cannot be empty."
            + "\n";

    public static final String MESSAGE_MISSING_SUB_COMMAND_TYPE = "OOPS!!! Please specify sub-command type.\n"
            + "For client: <command> -client\n"
            + "For property: <command> -property\n";

    public static final String MESSAGE_INVALID_INDEX = "OOPS!!! Please enter index(es) that appears within the "
            + "property list or client list\n";

    public static final String MESSAGE_NOT_INTEGER = "OOPS!! Please enter a positive integer as index\n";

    public static final String MESSAGE_NOT_INTEGER_INSTANCES = "The following input(s) are not integers:\n";

    public static final String MESSAGE_WRONG_FLAG_ORDER = "OOPS!! Wrong flag order.\n";

    public static final String MESSAGE_MISSING_FLAG = "OOPS!! You are missing the following flag(s):\n";

    public static final String MESSAGE_EXTRA_ARGUMENTS = "OOPS!! The following argument(s) is/are not recognised:\n";



    //@@author OVReader
    // Add Property/Client Related Error Messages
    public static final String MESSAGE_ADD_PROPERTY_WRONG_FORMAT = "OOPS!!! To add a property, it requires "
            + "the following format and details:\n"
            + "Format: add -property n/NAME a/ADDRESS p/PRICE t/TYPE\n"
            + "Example: add -property n/Bob Tan Bee Bee a/25 Lower Kent Ridge Rd, S119081 "
            + "p/1000 t/LP BGL";

    public static final String MESSAGE_ADD_CLIENT_WRONG_FORMAT = "OOPS!!! To add a client, it requires "
            + "the following format and details:\n"
            + "Format: add -client n/NAME c/CONTACT_NUMBER e/EMAIL "
            + "b/BUDGET_MONTH\n"
            + "Example: add -client n/Doja Cat c/93437878 e/doja88@example.com b/2000\n"
            + "Note: Email is optional";

    public static final String MESSAGE_INVALID_SINGAPORE_ADDRESS = "OOPS!!! The address provided is invalid. "
            + "To add a property, A valid Singapore address must be provided\nwith the following "
            + "format and details:\n"
            + LINE_BREAK
            + "\n  Format:\n"
            + INDENT + "[BLOCK NUMBER] [STREET NAME], S[POSTAL CODE]\n"
            + INDENT + "[BLOCK NUMBER] [STREET NAME] #[unit level]-[unit number], S[POSTAL CODE]\n"
            + INDENT + "[BLOCK NUMBER] [STREET NAME] #[unit level]-[unit number] [building name], S[POSTAL CODE]\n"
            + LINE_BREAK
            + "\n  Example:\n"
            + INDENT + "60 Aria Street, S602580\n"
            + INDENT + "101 Marlow Street #12-05, S059020\n"
            + INDENT + "101 Marlow Street #12-05 Clife Parkview, S059020\n"
            + LINE_BREAK
            + "\n  Note:\n"
            + INDENT + "1. Format requires single space between [DETAILS] (space sensitive).\n"
            + INDENT + "2. [DETAIL] must be provided; [detail] is optional.\n"
            + INDENT + "3. For landed property, treat [Block Number] as its unit number.\n"
            + INDENT + "4. Any deviation from format will lead to invalid address.\n"
            + LINE_BREAK;

    public static final String MESSAGE_INVALID_PRICE_FORMAT = "OOPS!!! Please only enter positive integer "
            + "for renting price per month of property";

    public static final String MESSAGE_INVALID_UNIT_TYPE_LABEL = "OOPS!!! Please enter one of the following "
            + "valid unit type labels as shown below (Case-Sensitive & Space-Sensitive):\n"
            + "Format: t/<label>\n"
            + LINE_BREAK
            + "\nHDB Labels:\n"
            + "  <HDB 2> for HDB 2-Room Flexi\n"
            + "  <HDB 3> for HDB 3-Room\n"
            + "  <HDB 4> for HDB 4-Room\n"
            + "  <HDB 5> for HDB 5-Room\n"
            + "  <HDB 3Gen> for HDB Third-Generation\n"
            + "  <HDB ExFlat> for HDB Executive Flat\n"
            + "  <HDB DBSS> for HDB Design, Build and Sell Scheme (DBSS) Flat\n"
            + "  <HDB ExMsn> for HDB Executive Maisonette\n"
            + "  <HDB Jumbo> for HDB Jumbo Flat\n"
            + "  <HDB TH> for HDB Terrace House\n"
            + LINE_BREAK
            + "\nCondominium Label:\n"
            + "  <Condo> for Condominium\n"
            + LINE_BREAK
            + "\nPenthouse Label:\n"
            + "  <PH> for Penthouse\n"
            + LINE_BREAK
            + "\nLanded Property Labels\n"
            + "  <LP TH> for LP Terrace House\n"
            + "  <LP SDH> for LP Semi-Detached House\n"
            + "  <LP BGL> for LP Bungalow\n"
            + LINE_BREAK
            + "\nNote: Only unit type labels (Singapore-Based) are accepted by the program";

    public static final String MESSAGE_ADDRESS_FORMAT_UNIT_TYPE_MISMATCH = "OOPS!!! There seem to be a mismatch"
            + " between address format and unit type provided.\n"
            + "Certain unit types will require specific address format. Please refer to the description below:\n"
            + "  1. Unit type with <LP> must not have #[unit level]-[unit number] in address.\n"
            + "     Format:\n"
            + "       [BLOCK NUMBER] [STREET NAME], S[POSTAL CODE]\n"
            + "  2. Unit type without <LP> must have #[unit level]-[unit number] in address.\n"
            + "     Format:\n"
            + "       [BLOCK NUMBER] [STREET NAME] #[unit level]-[unit number], S[POSTAL CODE]\n"
            + "       [BLOCK NUMBER] [STREET NAME] #[unit level]-[unit number] [building name], S[POSTAL CODE]\n"
            + "Note: HDB Terrace House (special case) is not restricted by any format.";

    public static final String MESSAGE_INVALID_CONTACT_NUMBER = "OOPS!!! Please enter a valid Singapore Contact Number "
            + "(No extension)";

    public static final String MESSAGE_INVALID_EMAIL = "OOPS!!! Please enter a valid Email Address";

    public static final String MESSAGE_INVALID_BUDGET_FORMAT = "OOPS!!! Please only enter positive integer "
            + "for budget";

    public static final String MESSAGE_DUPLICATE_PROPERTY = "OOPS!!! There is already an existing property with the"
            + " same address.";

    public static final String MESSAGE_DUPLICATE_CLIENT = "OOPS!!! There is already an existing client with the"
            + " same name/contact number/email.";
    //@@author


    /* Delete Property/Client Related Error Messages */

    //@@author FeliciaBeatrice
    public static final String MESSAGE_DELETE_CLIENT_WRONG_FORMAT = "OOPS!!! Please use this format to delete client:\n"
            + "delete -client i/CLIENT_INDEX\n";

    public static final String MESSAGE_DELETE_PROPERTY_WRONG_FORMAT = "OOPS!!! Please use this format to delete "
            + "property\n"
            + "delete -property i/PROPERTY_INDEX\n";
    //@@author

    //@@author ngdeqi
    /* Pair/Unpair Related Error Messages */
    public static final String MESSAGE_PAIR_WRONG_FORMAT = "To pair, please follow the following format:\n"
            + "  pair ip/PROPERTY_INDEX ic/CLIENT_INDEX\n"
            + "Example:\n"
            + "  pair ip/1 ic/5\n";

    public static final String MESSAGE_UNPAIR_WRONG_FORMAT = "To unpair, please follow the following format:\n"
            + "  unpair ip/PROPERTY_INDEX ic/CLIENT_INDEX\n"
            + "Example:\n"
            + "  unpair ip/2 ic/1\n";


    public static final String MESSAGE_CLIENT_ALREADY_PAIRED = "OOPS!! This client is currently renting a property, "
            + "try pairing with another client.\n";

    public static final String MESSAGE_EXISTING_PAIR = "OOPS!! This client and this property are already paired "
            + "together. You don't need to pair them again.\n";

    public static final String MESSAGE_BUDGET_EXCEEDED = "OOPS!! The rental price exceeds the client's budget. "
            + "Pair unsuccessful.\n";

    public static final String MESSAGE_BUDGET_CLIENT = "The client's name and budget:\n";

    public static final String MESSAGE_BUDGET_PROPERTY = "The property's name and rental price:\n";

    public static final String MESSAGE_NO_EXISTING_PAIR = "OOPS!! This property is not being rented by the tenant. "
            + "Unpair unsuccessful.\n";
    //@@author



    /* Check property/client related Error Messages */


    //@@author FeliciaBeatrice
    public static final String MESSAGE_CHECK_CLIENT = "Showing check results for this client:";

    public static final String MESSAGE_CHECK_CLIENT_RESULT = "Here is the property this client is renting:";

    public static final String MESSAGE_CHECK_CLIENT_NO_PAIR = "There are no pairings involving this client.";

    public static final String MESSAGE_CHECK_CLIENT_WRONG_FORMAT = "OOPS!! To check client, please use the "
            + "following format:\n"
            + "  check -client i/INDEX\n"
            + "Example:\n"
            + "  check -client i/5\n";
    //@@author

    public static final String MESSAGE_CHECK_PROPERTY_WRONG_FORMAT = "OOPS!! To check property, please use the "
            + "following format:\n"
            + "  check -property i/INDEX\n"
            + "Example:\n"
            + "  check -property i/5\n";

    public static final String MESSAGE_CHECK_EXTRA_ARGUMENTS = "OOPS!! The following argument(s) is/are not "
            + "recognised:\n";


    /* List related Error Messages */
    
    public static final String MESSAGE_LIST_CLIENTS_WITH_TAGS_ASSERT = "List clients with tags - commandFlags "
            + "taking illegal values";
            
    public static final String MESSAGE_LIST_PROPERTIES_WITH_TAGS_ASSERT = "List properties with tags - commandFlags "
            + "taking illegal values";


    /* List related Error Messages */
    //@@author zoranabc201

    public static final String MESSAGE_INCORRECT_LIST_DETAILS = "OOPS!!! Please enter -client"
            + " to list clients, -property to list properties, -pair to list pairs and -everything to list"
            + " everything";

    public static final String MESSAGE_BYE_PARAMETERS_PRESENT = "Please type bye without any parameters"
            + " if you would like to quit";

    public static final String MESSAGE_INCORRECT_PROPERTY_LIST_FLAG = "Please type the following "
            + "after list -property to display - "
            + "\na/ for property addresses"
            + "\nt/ for property types"
            + "\nn/ for property owner names"
            + "\np/ for property prices"
            + "\n-short to display addresses, types and prices";

    public static final String MESSAGE_INCORRECT_CLIENT_LIST_FLAG = "Please type the following "
            + "after list -client to display - "
            + "\nn/ for client names"
            + "\nc/ for client contact numbers"
            + "\ne/ for client emails"
            + "\nb/ for client budgets"
            + "\n-short to display names and budgets";

    public static final String MESSAGE_INCORRECT_PAIR_LIST_FLAG = "Please type - "
            + "\nlist -pair to list all pairs with all their information"
            + "\nlist -pair -short to list all pairs with shortened information";
    public static final String MESSAGE_BYE = "Goodbye :). See you soon!";
    //@@author


    //@@author wilsonngja
    /* Error message for Find Function */
    public static final String EMPTY_FIND_DESCRIPTION = "It seems like your description is empty.\n"
            + "Please ensure you enter the description after the command."
            + "\nIt's in the format of:\nfind -<CLIENT/PROPERTY> f/QUERY_TEXT\n";

    public static final String INCORRECT_NUMBER_OF_FLAG = "Please ensure that you only have 1 flag and that "
            + "should be 'f/'\nFor example: find -<CLIENT/PROPERTY> f/QUERY_TEXT\n";

    public static final String MESSAGE_NO_CLIENT_MATCHES = "There is no client that fits within your query.";

    public static final String MESSAGE_NO_PROPERTY_MATCHES = "There is no property that fits within your query.";

    public static final String MESSAGE_NO_FIND_CLIENT_TAG = "Seems like your tag is missing.\nPlease ensure you"
            + " entered 'f/' after the subcommand."
            + "\nFor example:\nfind -client f/Bobby";

    public static final String MESSAGE_NO_FIND_PROPERTY_TAG = "Seems like your tag is missing.\nPlease ensure you"
            + " enter 'f/' after the subcommand."
            + "\nFor example:\nfind -property f/Bobby";


    /* Message on Storage */
    public static final String INVALID_CLIENT_FORMATTING = "There are one or more errors in the formatting of Client"
            + " in client.txt.\nThese entries will not be added to the Client List.\n";

    public static final String INVALID_PROPERTY_FORMATTING = "There are one or more errors in the formatting of"
            + "Property in property.txt.\nThese entries will not be added to the Property List.\n";

    public static final String INVALID_PAIRING_FORMATTING = "There are one or more errors in the formatting of "
            + "Pairings in pair.txt.\nThese entries will not be added to the Pairing List.\n";

    public static final String INVALID_CLIENT_ENTRIES = "There are one or more invalid entries while adding to Client"
            + " List. These entries will not be added to the Client List.\n";

    public static final String INVALID_PROPERTY_ENTRIES = "There are one or more invalid entries while adding to"
            + " Property List.\nThese entries will not be added to the property list.\n";

    public static final String MESSAGE_NO_PROPERTY_FILE = "Property file does not exist.";

    public static final String MESSAGE_NO_CLIENT_FILE = "Client file not found.";

    public static final String MESSAGE_NO_PAIRING_FILE = "Pairing file does not exist.";



    /* Message for Help Command */
    public static final String INVALID_HELP_COMMAND = "It seems like you have entered more than what is expected.\n"
            + "Please only enter 'help' to see the list of commands available.";

    public static final String LIST_HELP_COMMAND = LINE_BREAK + "\nLIST COMMAND\n" + LINE_BREAK + "\nlist -client\n"
            + "list -property\n" + "list -everything\n" + "list -client <TAG>\n" + "list -property <TAG>\n"
            + "where <TAG> is replaced by the tag you would like " + "to look into. \nFor example: list -client n/\n"
            + LINE_BREAK;

    public static final String ADD_HELP_COMMAND = "\nADD COMMAND\n" + LINE_BREAK + "\nadd -client n/<CLIENT_NAME> "
            + "c/<CONTACT_NUMBER> e/<EMAIL> b/<BUDGET>\nadd -property n/<LANDLORD_NAME> a/<ADDRESS> p/<RENTAL_PRICE>"
            + " t/<UNIT_TYPE>\nwhere the tags (represented by brackets)  are replaced as accordingly\n"
            + "Note that email address is optional\n" + LINE_BREAK;

    public static final String DELETE_HELP_COMMAND = "\nDELETE COMMAND\n" + LINE_BREAK + "\ndelete -client i/<INDEX>"
            + "\ndelete -property i/<INDEX>\nwhere <INDEX> represents the index of entity to be deleted.\n"
            + LINE_BREAK;

    public static final String PAIR_UNPAIR_HELP_COMMAND = "\nPAIR/UNPAIR COMMAND\n" + LINE_BREAK + "\npair ip/<INDEX>"
            + " ic/<INDEX>\nunpair ip/<INDEX> ic/<INDEX>\nwhere <INDEX> represents the index of property and client"
            + " to pair together with.\n" + LINE_BREAK;

    public static final String CHECK_HELP_COMMAND = "\nCHECK COMMAND\n" + LINE_BREAK + "\ncheck -client i/<INDEX>"
            + "\ncheck -property i/<INDEX>\nwhere <INDEX> refers to the index of the client or property to check "
            + "for.\n" + LINE_BREAK;

    public static final String FIND_HELP_COMMAND = "\nFIND COMMAND\n" + LINE_BREAK + "\nfind -client f/<QUERY_TEXT>\n"
            + "find -property f/<QUERY_TEXT>\nwhere <QUERY_TEXT> refers to the text to search for in either client "
            + "or property.\n";

    public static final String HELP_COMMAND_PRINT_LIST = "The list of available commands are stated below:\n\n"
            + LIST_HELP_COMMAND + ADD_HELP_COMMAND + DELETE_HELP_COMMAND + PAIR_UNPAIR_HELP_COMMAND
            + CHECK_HELP_COMMAND + FIND_HELP_COMMAND + LINE_BREAK;

    //@@author
}
