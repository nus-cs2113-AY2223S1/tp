package seedu.duke.exception;

public class ExceptionMessages {
    //Later, all messages for exceptions will be put in here then import to the file where they are used
    //--> avoid magic string

    //User-related messages
    public static final String MESSAGE_USER_NOT_FOUND = "This user cannot be found in the list";
    public static final String MESSAGE_USERNAME_TAKEN = "This username has been taken";
    public static final String MESSAGE_USER_AGE_INVALID = "Age should only contain digit 0-9";
    public static final String MESSAGE_CONTACT_LENGTH_INVALID = "Contact number length must have length of 8";
    public static final String MESSAGE_CONTACT_FORMAT_INVALID = "Contact number should only contain digit 0-9";
    public static final String MESSAGE_USER_LENDING = "This user is currently lending something";
    public static final String MESSAGE_USER_BORROWING = "This user is currently borrowing something";

    //Item-related messages
    public static final String MESSAGE_ITEM_UNAVAILABLE = "This item is currently unavailable";
    public static final String MESSAGE_ITEM_NOT_FOUND = "This item cannot be found in the list";
    public static final String MESSAGE_ITEM_NAME_TAKEN = "This itemName has been taken";
    public static final String MESSAGE_PRICE_FORMAT_INVALID = "Price is a float, check your format";
    public static final String MESSAGE_PRICE_LESS_THAN_ZERO = "Price must be greater than 0";

    //Tx-related messages
    public static final String MESSAGE_TX_NOT_FOUND = "This transaction cannot be found in the list";
    public static final String MESSAGE_SELF_BORROWER = "Borrower cannot borrow items from his/herself";

    //Other messages
    public static final String MESSAGE_INVALID_PARTS = "One of the parts is in incorrect format";
    public static final String MESSAGE_NUMBER_FORMAT_INVALID = "Number should only contain digit 0-9";
    public static final String MESSAGE_DATE_FORMAT_INVALID = "The date format is incorrect(YYYY-MM-DD)";
    public static final String MESSAGE_INSUFFICIENT_ARGUMENTS = "Not enough arguments for this commands to execute";
    public static final String MESSAGE_COMMAND_UNRECOGNIZABLE = "This command is unrecognizable!!!\n"
            + "Please use help command to check";
}
