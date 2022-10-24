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
}
