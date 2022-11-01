package seedu.duke.exception.message;

// @@author bdthanh

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
    public static final String MESSAGE_CONTACT_DUPLICATE =
            "Contact number already exists! Please use another phone number.";
    public static final String MESSAGE_USER_LENDING = "This user is currently lending something";
    public static final String MESSAGE_USER_BORROWING =
            "This user is currently borrowing something";

    // Item-related messages
    public static final String MESSAGE_ITEM_UNAVAILABLE = "This item is currently unavailable";
    public static final String MESSAGE_DUPLICATE_ITEM_ID = "There are two or more items with the same ID";
    public static final String MESSAGE_ITEM_NOT_MATCHED
            = "Item's name or owner do not match with the item with given ID";
    public static final String MESSAGE_DUPLICATE_TRANSACTION_ID = "There are two or more transactions with the same ID";
    public static final String MESSAGE_ITEM_NOT_FOUND = "This item cannot be found in the list";
    public static final String MESSAGE_PRICE_FORMAT_INVALID = "Price is a float, check your format";
    public static final String MESSAGE_PRICE_OUT_OF_RANGE =
            "Price must be in range from 0 to 10000";
    public static final String MESSAGE_SORT_MODE_INVALID = "Mode of sorting is invalid";
    public static final String MESSAGE_PRICE_BOUNDARIES_INVALID =
            "Minimum price cannot be more than maximum price";
    public static final String MESSAGE_CATEGORY_INVALID =
            "Category index is invalid\nPlease use list-categories"
                    + " to check the index of your chosen categories";

    // Tx-related messages
    public static final String MESSAGE_TX_NOT_FOUND =
            "This transaction cannot be found in the list";
    public static final String MESSAGE_SELF_BORROWER =
            "Borrower cannot borrow items from his/herself";
    public static final String MESSAGE_DURATION_INVALID =
            "Duration cannot be less than 0 or more than 1461 days (4 years)";
    public static final String MESSAGE_STATUS_INVALID =
            "The status argument is invalid (only 2 status are accepted: finished or unfinished)";
    public static final String MESSAGE_ITEM_TRANSACTION_OVERLAP =
            "Your item was/is unavailable during the input period (overlap with transaction: ";
    public static final String MESSAGE_ITEM_UPDATE_TRANSACTION_OVERLAP =
            "Your item was/is unavailable during the update period (overlap with transaction: ";

    // Other messages
    public static final String MESSAGE_INVALID_PARTS =
            "One or more of the parts is in incorrect format. "
                    + "Check the command format by using the 'help' command!";
    public static final String MESSAGE_NUMBER_FORMAT_INVALID =
            "Number should only contain digit 0-9";
    public static final String MESSAGE_CATEGORY_INDEX_FORMAT_INVALID =
            "Category index should be a positive integer."
                    + " Please use list-categories to get the index";
    public static final String MESSAGE_DATE_FORMAT_INVALID =
            "The date format is incorrect(YYYY-MM-DD)";
    public static final String MESSAGE_CREATED_DATE_RANGE_INVALID =
            "The created date must be between today and 2016-01-01";
    public static final String MESSAGE_INVALID_NUMBER_OF_ARGS =
            "The number of arguments for this command is incorrect";
    public static final String MESSAGE_COMMAND_UNRECOGNIZABLE =
            "This command is unrecognizable!!!\n" + "Please use the 'help' command to check";
    public static final String MESSAGE_FILE_NOT_FOUND = "OOPS!!! I cannot find your file";
    public static final String MESSAGE_STORE_INVALID =
            "Something went wrong when storing your tasks";
    public static final String MESSAGE_ARGUMENT_EMPTY = "The value cannot be empty";
    public static final String MESSAGE_CONTAIN_DATA_SEPARATOR =
            "Input cannot contain | character because it can cause errors when loading data";
    public static final String MESSAGE_USER_STORAGE_ILLEGALLY_MODIFIED = "The USER files has been corrupted at line ";
    public static final String MESSAGE_ITEM_STORAGE_ILLEGALLY_MODIFIED = "The ITEM files has been corrupted at line ";
    public static final String MESSAGE_TRANSACTION_STORAGE_ILLEGALLY_MODIFIED =
            "The TRANSACTION files has been corrupted at line ";
    public static final String MESSAGE_FILES_ILLEGALLY_DELETED = "One or more files has been deleted\n";
    public static final String MESSAGE_TO_FIX_FILES =
            "Please try to fix your data in your files before running the app again\n"
                    + "If you fix it correctly, you will see a greeting message in the next run\n"
                    + "If you cannot fix it, you will see this message again. Please delete the entire data folder\n"
                    + "to avoid errors, which also mean that all your data will be gone forever\n"
                    + "In that case, we will create three brand-new lists for your users, items, and transactions\n"
                    + "REMEMBER that all files in data folder must be edited correctly\n"
                    + "Do you want to force reset all files and restart? y or n";
    public static final String MESSAGE_EXIT_DUKE = "Exit Duke and please edit correctly...";
    public static final String MESSAGE_RESET_DUKE = "Force reset files and please restart...";
    public static final String MESSAGE_NAME_LENGTH_INVALID = "The length of name must be no more than 20 characters";
    public static final String MESSAGE_NUM_OF_ARGS_INVALID = "The number of args for line is incorrect";
    public static final String MESSAGE_VALUE_OF_ARGS_INVALID = "The value of args for line is empty";
    public static final String MESSAGE_STORAGE_REASON = "\nReason: ";
    public static final String MESSAGE_YES_OR_NO
            = "Please only input y or n. y for force resetting, n for self-editing";
}
