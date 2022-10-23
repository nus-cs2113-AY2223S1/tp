package seedu.duke.data;

import static seedu.duke.common.Constants.MAX_TRANSACTIONS_COUNT;
import static seedu.duke.common.Constants.MAX_AMOUNT_VALUE;
import static seedu.duke.common.Constants.MIN_BUDGET_VALUE;

/**
 * Represents the user's budget for the current month.
 *
 * <p>The default budget value is $1000, or the value read from the file storage.
 * Operations related to the budgets are also defined under this class.
 */
public class Budget {
    //@@author wcwy
    // Default value of the monthly budget is $1000
    private static long budget = 1000;

    /**
     * Retrieves the budget value set for the current month.
     *
     * @return The budget value set by the user.
     */
    public static long getBudget() {
        return budget;
    }

    /**
     * Updates the budget value set for the current month.
     *
     * @param budget The new value for budget.
     */
    public static void setBudget(long budget) {
        Budget.budget = budget;
    }

    /**
     * Returns the amount of budget left in the month, as a string.
     *
     * <p>If the total amount of expenses is higher than the budget, a negative value in string will be returned.
     *
     * @param totalMonthlyExpense The long value representing the total sum of a monthly expense.
     * @return A string value representing the amount of budget left.
     */
    public static String getBudgetLeft(long totalMonthlyExpense) {
        /*
            Since the maximum number of transaction is 1000000, maximum amount of expense is 10000000,
            and minimum is 1, the lowest possible budget left value is
            1 - (10^6 * 10^7) = -10^15 + 1 > Long.MIN_VALUE (approx -9.22 * 10^18)
            Thus, this function is safe from integer overflow UNLESS the values in common.Constants.java is altered.
         */

        assert (Long.valueOf(MAX_AMOUNT_VALUE) * Long.valueOf(MAX_TRANSACTIONS_COUNT) > 0);
        assert (Long.valueOf(MAX_AMOUNT_VALUE) * Long.valueOf(MAX_TRANSACTIONS_COUNT) > Long.valueOf(MAX_AMOUNT_VALUE));
        assert (MIN_BUDGET_VALUE > 0);

        return Long.toString(budget - totalMonthlyExpense);
    }
}
