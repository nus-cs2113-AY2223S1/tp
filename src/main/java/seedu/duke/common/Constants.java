package seedu.duke.common;

/**
 * Represents all the constant settings of the application.
 * Developers should update the constant in this file to allow for different limits for the application.
 *
 * <p>Note that altering the values in this folder may result in integer overflow in the program in extreme cases.
 */
public class Constants {
    //@@author wcwy
    /*
        WARNING: Editing the values below may result in integer overflow in
        1. TransactionList.calculateMonthlyTotalExpense()
        2. Budget.getBudgetLeft()
     */

    // One million transactions is the capacity allowed
    public static int MAX_TRANSACTIONS_COUNT = 1000000;

    // The amount of one transaction is allowed to be in the range or 0 <= x <= 10000000
    public static int MIN_AMOUNT_VALUE = 0;
    public static int MAX_AMOUNT_VALUE = 10000000;

    // The amount of transaction is allowed to be in the range or 0 <= x <= 10000000
    public static int MIN_BUDGET_VALUE = 1;
    public static long MAX_BUDGET_VALUE = Long.valueOf(MAX_TRANSACTIONS_COUNT) * Long.valueOf(MAX_AMOUNT_VALUE);
}
