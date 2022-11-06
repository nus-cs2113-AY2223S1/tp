package seedu.duke;

public class CommandStructure {

    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_PAIR = "pair";
    public static final String COMMAND_UNPAIR = "unpair";
    public static final String COMMAND_CHECK = "check";
    public static final String COMMAND_EXIT  = "quit";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_HELP = "help";

    //@@author zoranabc201
    public static final int START_INDEX = 0;
    public static final int ONE_ITEM_IN_LIST = 1;
    public static final String NAME_FLAG = "n/";
    public static final String PAIR_FLAG = "-pair";
    public static final String CONTACT_NUMBER_FLAG = "c/";
    public static final String ADDRESS_FLAG = "a/";
    public static final String TYPE_FLAG = "t/";
    public static final String PRICE_FLAG = "p/";
    public static final String EMAIL_FLAG = "e/";
    public static final String BUDGET_FLAG = "b/";
    public static final String PROPERTY_FLAG = "-property";
    public static final String CLIENT_FLAG = "-client";
    public static final String EVERYTHING_FLAG = "-everything";
    public static final String SHORT_FLAG = "-short";
    //@@author

    public static final String FIND_FLAGS = "f/";

    public static final String[] ADD_PROPERTY_FLAGS = {"n/", "a/", "p/", "t/"};
    public static final String[] ADD_CLIENT_FLAGS = {"n/", "c/", "e/", "b/"};
    public static final String[] DELETE_CLIENT_FLAGS = {"i/"};
    public static final String[] DELETE_PROPERTY_FLAGS = {"i/"};
    public static final String[] PAIR_FLAGS = {"ip/", "ic/"};
    public static final String[] UNPAIR_FLAGS = {"ip/", "ic/"};
    public static final String[] CHECK_PROPERTY_FLAGS = {"i/"};
    public static final String[] CHECK_CLIENT_FLAGS = {"i/"};

    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;

    //@@author OVReader
    // Unit-Type Command Labels
    // HDB Labels
    public static final String UNIT_TYPE_2ROOM_SHORT = "HDB 2";
    public static final String UNIT_TYPE_3ROOM_SHORT = "HDB 3";
    public static final String UNIT_TYPE_4ROOM_SHORT = "HDB 4";
    public static final String UNIT_TYPE_5ROOM_SHORT = "HDB 5";
    public static final String UNIT_TYPE_3GEN = "HDB 3Gen";
    public static final String UNIT_TYPE_EXECUTIVE_FLAT_SHORT = "HDB ExFlat";
    public static final String UNIT_TYPE_DBSS_SHORT = "HDB DBSS";
    public static final String UNIT_TYPE_MAISONETTE_SHORT = "HDB ExMsn";
    public static final String UNIT_TYPE_JUMBO_SHORT = "HDB Jumbo";
    public static final String UNIT_TYPE_HDB_TERRENCE_SHORT = "HDB TH";

    // Condominium Label
    public static final String UNIT_TYPE_CONDO_SHORT = "Condo";

    // Penthouse Label
    public static final String UNIT_TYPE_PENTHOUSE_SHORT = "PH";

    // Landed Property Labels
    public static final String UNIT_TYPE_LANDED_TERRENCE_SHORT = "LP TH";
    public static final String UNIT_TYPE_SEMI_DETEACHED_SHORT = "LP SDH";
    public static final String UNIT_TYPE_BUNGALOW_SHORT = "LP BGL";

    // Actual Unit-Type
    // HDB
    public static final String ACTUAL_UNIT_TYPE_2ROOM = "HDB 2-Room Flexi";
    public static final String ACTUAL_UNIT_TYPE_3ROOM = "HDB 3-Room";
    public static final String ACTUAL_UNIT_TYPE_4ROOM = "HDB 4-Room";
    public static final String ACTUAL_UNIT_TYPE_5ROOM = "HDB 5-Room";
    public static final String ACTUAL_UNIT_TYPE_3GEN = "HDB 3Gen";
    public static final String ACTUAL_UNIT_TYPE_EXECUTIVE_FLAT = "HDB Executive Flat";
    public static final String ACTUAL_UNIT_TYPE_DBSS = "HDB Design, Build and Sell Scheme (DBSS) Flat";
    public static final String ACTUAL_UNIT_TYPE_MAISONETTE = "HDB Executive Maisonette";
    public static final String ACTUAL_UNIT_TYPE_JUMBO = "HDB Jumbo Flat";
    public static final String ACTUAL_UNIT_TYPE_TERRENCE = "HDB Terrace House";

    // Condominium
    public static final String ACTUAL_UNIT_TYPE_CONDO = "Condominium";

    // Penthouse
    public static final String ACTUAL_UNIT_TYPE_HDB_PENTHOUSE = "Penthouse";

    // Landed Property
    public static final String ACTUAL_UNIT_TYPE_LANDED_TERRENCE = "LP Terrace House";
    public static final String ACTUAL_UNIT_TYPE_SEMI_DETACHED = "LP Semi-Detached House";
    public static final String ACTUAL_UNIT_TYPE_BUNGALOW = "LP Bungalow";
    //@@author
}
