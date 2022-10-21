package seedu.duke.common;

/**
 * Represents all the constant settings of the application.
 * Developers should update the constant in this file to allow for different limits for the application.
 */
public class Constants {
    // The amount of transaction is allowed to be in the range or 0 <= x <= 10000000
    public static int MIN_AMOUNT_VALUE = 0;
    public static int MAX_AMOUNT_VALUE = 10000000;
    // One million transactions is the capacity allowed
    public static int MAX_TRANSACTIONS_COUNT = 1000000;
}
