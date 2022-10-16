package seedu.duke.exception.message;

/**
 * A class to store the Exception Messages.
 */
public class ExceptionMessages {
    // User-related messages
    public static final String MESSAGE_USER_NOT_FOUND = "This user cannot be found in the list";
    public static final String MESSAGE_USERNAME_TAKEN = "This username has been taken";
    public static final String MESSAGE_USER_AGE_INVALID = "Age should only contain digit 0-9";
    public static final String MESSAGE_USER_AGE_OUT_OF_RANGE =
            "Age must in the range from 10 to 100";
    public static final String MESSAGE_CONTACT_LENGTH_INVALID =
            "Contact number length must have length of 8";
    public static final String MESSAGE_CONTACT_FORMAT_INVALID =
            "Contact number should only contain digit 0-9";
    public static final String MESSAGE_USER_LENDING = "This user is currently lending something";
    public static final String MESSAGE_USER_BORROWING =
            "This user is currently borrowing something";

    // Item-related messages
    public static final String MESSAGE_ITEM_UNAVAILABLE = "This item is currently unavailable";
    public static final String MESSAGE_ITEM_NOT_FOUND = "This item cannot be found in the list";
    public static final String MESSAGE_SAME_OWNER = "User already has this item listed!";
    public static final String MESSAGE_PRICE_FORMAT_INVALID = "Price is a float, check your format";
    public static final String MESSAGE_PRICE_LESS_THAN_ZERO = "Price must be greater than 0";
    public static final String MESSAGE_SORT_MODE_INVALID = "Mode of sorting is invalid";
    public static final String MESSAGE_PRICE_BOUNDARIES_INVALID =
            "Minimum price cannot be more than maximum price";

    // Tx-related messages
    public static final String MESSAGE_TX_NOT_FOUND =
            "This transaction cannot be found in the list";
    public static final String MESSAGE_SELF_BORROWER =
            "Borrower cannot borrow items from his/herself";
    public static final String MESSAGE_DURATION_INVALID = "Duration cannot be less than 0";

    // Other messages
    public static final String MESSAGE_INVALID_PARTS = "One of the parts is in incorrect format";
    public static final String MESSAGE_NUMBER_FORMAT_INVALID =
            "Number should only contain digit 0-9";
    public static final String MESSAGE_DATE_FORMAT_INVALID =
            "The date format is incorrect(YYYY-MM-DD)";
    public static final String MESSAGE_CREATED_DATE_RANGE_INVALID =
            "The created date must be before today";
    public static final String MESSAGE_INSUFFICIENT_ARGUMENTS =
            "The number of arguments for this command is incorrect";
    public static final String MESSAGE_COMMAND_UNRECOGNIZABLE =
            "This command is unrecognizable!!!\n" + "Please use help command to check";
    public static final String MESSAGE_FILE_NOT_FOUND = "OOPS!!! I cannot find your file";
    public static final String MESSAGE_STORE_INVALID =
            "Something went wrong when storing your tasks";
}
