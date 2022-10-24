package seedu.duke;

import java.util.Map;

public class CommandStructure {

    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_PAIR = "pair";
    public static final String COMMAND_UNPAIR = "unpair";
    public static final String COMMAND_CHECK = "check";
    public static final String COMMAND_EXIT  = "quit";
    public static final String COMMAND_FIND = "find";
    public static final String NAME_FLAG = "n/";
    public static final String CONTACT_NUMBER_FLAG = "c/";
    public static final String ADDRESS_FLAG = "a/";
    public static final String TYPE_FLAG = "t/";
    public static final String PRICE_FLAG = "p/";
    public static final String EMAIL_FLAG = "e/";
    public static final String BUDGET_FLAG = "b/";
    public static final String FIND_FLAGS = "f/";
    
    public static final String PROPERTY_FLAG = "-property";
    public static final String CLIENT_FLAG = "-client";
    public static final String EVERYTHING_FLAG = "-everything";

    public static final String[] ADD_PROPERTY_FLAGS = {"n/", "a/", "p/", "t/"};
    public static final String[] ADD_CLIENT_FLAGS = {"n/", "c/", "e/", "b/"};
    public static final String[] DELETE_PROPERTY_FLAGS = {"ip/"};
    public static final String[] DELETE_CLIENT_FLAGS = {"ic/"};
    public static final String[] PAIR_FLAGS = {"ip/", "ic/"};
    public static final String[] UNPAIR_FLAGS = {"ip/", "ic/"};
    public static final String[] CHECK_PROPERTY_FLAGS = {"ip/"};
    public static final String[] CHECK_CLIENT_FLAGS = {"ic/"};

    //@@author OVReader
    // Unit-Type Command Labels
    // HDB Labels
    public static final String UNIT_TYPE_ONE   = "HDB 2";
    public static final String UNIT_TYPE_TWO   = "HDB 3";
    public static final String UNIT_TYPE_THREE = "HDB 4";
    public static final String UNIT_TYPE_FOUR  = "HDB 5";
    public static final String UNIT_TYPE_FIVE  = "HDB 3Gen";
    public static final String UNIT_TYPE_SIX   = "HDB ExFlat";
    public static final String UNIT_TYPE_SEVEN = "HDB DBSS";
    public static final String UNIT_TYPE_EIGHT = "HDB ExMsn";
    public static final String UNIT_TYPE_NINE  = "HDB Jumbo";
    public static final String UNIT_TYPE_TEN   = "HDB TH";

    // Condominium Label
    public static final String UNIT_TYPE_ELEVEN = "Condo";

    // Penthouse Label
    public static final String UNIT_TYPE_TWELVE = "PH";

    // Landed Property Labels
    public static final String UNIT_TYPE_THIRTEEN = "LP TH";
    public static final String UNIT_TYPE_FOURTEEN = "LP SDH";
    public static final String UNIT_TYPE_FIFTEEN  = "LP BGL";

    // Actual Unit-Type
    // HDB
    public static final String ACTUAL_UNIT_TYPE_ONE   = "HDB 2-Room Flexi";
    public static final String ACTUAL_UNIT_TYPE_TWO   = "HDB 3-Room";
    public static final String ACTUAL_UNIT_TYPE_THREE = "HDB 4-Room";
    public static final String ACTUAL_UNIT_TYPE_FOUR  = "HDB 5-Room";
    public static final String ACTUAL_UNIT_TYPE_FIVE  = "HDB 3Gen";
    public static final String ACTUAL_UNIT_TYPE_SIX   = "HDB Executive Flat";
    public static final String ACTUAL_UNIT_TYPE_SEVEN = "HDB Design, Build and Sell Scheme (DBSS) Flat";
    public static final String ACTUAL_UNIT_TYPE_EIGHT = "HDB Executive Maisonette";
    public static final String ACTUAL_UNIT_TYPE_NINE  = "HDB Jumbo Flat";
    public static final String ACTUAL_UNIT_TYPE_TEN   = "HDB Terrace House";

    // Condominium
    public static final String ACTUAL_UNIT_TYPE_ELEVEN = "Condominium";

    // Penthouse
    public static final String ACTUAL_UNIT_TYPE_TWELVE = "Penthouse";

    // Landed Property
    public static final String ACTUAL_UNIT_TYPE_THIRTEEN = "LP Terrace House";
    public static final String ACTUAL_UNIT_TYPE_FOURTEEN = "LP Semi-Detached House";
    public static final String ACTUAL_UNIT_TYPE_FIFTEEN  = "LP Bungalow";
    //@@author
}
