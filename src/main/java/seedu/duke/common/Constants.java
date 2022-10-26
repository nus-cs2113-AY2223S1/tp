package seedu.duke.common;

//@@author wcwy

/**
 * Represents all the constant settings of the application.
 * Developers should update the constant in this file to allow for different limits for the application.
 *
 * <p>Note that altering the values in this folder may result in integer overflow in the program in extreme cases.
 */
public class Constants {
    //@@author paullowse

    public static final int MINIMUM_YEAR = 1000;
    public static final int MAXIMUM_YEAR = 9999;
    public static final int MAXIMUM_STATS_NUMBER = 100;
    public static final int MINIMUM_STATS_NUMBER = 1;


    //@@author wcwy

    /*
        WARNING: Editing the values below may result in integer overflow in
        1. TransactionList.calculateMonthlyTotalExpense()
        2. Budget.getBudgetLeft()
     */

    // One million transactions is the capacity allowed
    public static int MAX_TRANSACTIONS_COUNT = 1000000;

    //@@author chinhan99

    // The amount of one transaction is allowed to be in the range of 1 <= x <= 10000000
    public static int MIN_AMOUNT_VALUE = 1;
    public static int MAX_AMOUNT_VALUE = 10000000;

    //@@author wcwy

    // The amount of transaction is allowed to be in the range or 1 <= x <= MAX_AMOUNT * MAX_TRANSACTION (10^13)
    public static int MIN_BUDGET_VALUE = 1;

    public static long MAX_BUDGET_VALUE = Long.valueOf(MAX_TRANSACTIONS_COUNT) * Long.valueOf(MAX_AMOUNT_VALUE);
}
