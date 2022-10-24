package seedu.duke.data;

import java.time.LocalDate;

import static java.lang.Math.abs;
import static seedu.duke.common.Constants.MAX_TRANSACTIONS_COUNT;
import static seedu.duke.common.Constants.MAX_AMOUNT_VALUE;
import static seedu.duke.common.Constants.MIN_BUDGET_VALUE;
import static seedu.duke.common.InfoMessages.INFO_COLON_SPACE;
import static seedu.duke.common.InfoMessages.INFO_EXCEEDING_BUDGET;
import static seedu.duke.common.InfoMessages.INFO_REMAINING_BUDGET;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_EXCEEDED_TIPS;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_NOT_EXCEEDED_TIPS;
import static seedu.duke.common.InfoMessages.INFO_FULL_STOP_SPACE;
import static seedu.duke.common.InfoMessages.INFO_DOLLAR_SIGN;

//@@author wcwy

/**
 * Represents the user's budget for the current month.
 *
 * <p>The default budget value is $1000, or the value read from the file storage.
 * Operations related to the budgets are also defined under this class.
 */
public class Budget {
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
     * Generates a budget remaining message based on the total monthly expense given.
     *
     * <p>Caller method can choose to either to generate it with a tips behind or without it
     * by setting the boolean withTips correspondingly.
     *
     * @param totalMonthlyExpense The long value representing the total sum of a monthly expense.
     * @param withTips            The boolean value to indicate whether tips need to be appended behind the message.
     * @param monthYear           The date of transaction.
     * @return The message generated based on the budget remained for the given date.
     */
    public static String generateBudgetRemainingMessage(long totalMonthlyExpense, boolean withTips, String monthYear) {
        long budgetLeft = calculateBudgetLeft(totalMonthlyExpense);
        boolean hasExceededBudget = hasExceededBudget(budgetLeft);

        String message = getBudgetLeftMessage(budgetLeft, hasExceededBudget, monthYear);

        if (withTips) {
            message += INFO_FULL_STOP_SPACE + getMoneyManagingTips(hasExceededBudget);
        }

        return message;
    }

    public static String generateCurrentMonthBudgetRemainingMessage() {
        LocalDate todayDate = LocalDate.now();
        String monthYear = "current month";
        long currentMonthTotalExpense = TransactionList.calculateMonthlyTotalExpense(todayDate);
        String message = generateBudgetRemainingMessage(currentMonthTotalExpense, false, monthYear);

        return message;
    }

    /**
     * Calculates and returns the amount of budget left in the month, as a long value.
     *
     * <p>If the total amount of expenses is higher than the budget, a negative value will be returned.
     *
     * @param totalMonthlyExpense The long value representing the total sum of a monthly expense.
     * @return A long value representing the amount of budget left.
     */
    private static long calculateBudgetLeft(long totalMonthlyExpense) {
        /*
            Since the maximum number of transaction is 1000000, maximum amount of expense is 10000000,
            and minimum is 1, the lowest possible budget left value is
            1 - (10^6 * 10^7) = -10^15 + 1 > Long.MIN_VALUE (approx -9.22 * 10^18)
            Thus, this function is safe from integer overflow UNLESS the values in common.Constants.java is altered.
         */

        assert (Long.valueOf(MAX_AMOUNT_VALUE) * Long.valueOf(MAX_TRANSACTIONS_COUNT) > 0);
        assert (Long.valueOf(MAX_AMOUNT_VALUE) * Long.valueOf(MAX_TRANSACTIONS_COUNT) > Long.valueOf(MAX_AMOUNT_VALUE));
        assert (MIN_BUDGET_VALUE > 0);

        return budget - totalMonthlyExpense;
    }

    /**
     * Checks if the budget has been exceeded.
     *
     * <p>If the budget is negative, it means that the total monthly expense is higher than the budget left.
     * Thus, the budget is exceeded and returns true.
     *
     * <p>If the budget is not positive, it means that the total monthly expense is lower than or equals to the
     * budget left. Thus budget is not yet exceeded and returns false.
     *
     * @param budgetLeft A long value indicating the difference of total monthly expense and monthly budget.
     * @return A boolean value indicating whether the budget has been exceeded for the month.
     */
    private static boolean hasExceededBudget(long budgetLeft) {
        return budgetLeft < 0;
    }

    /**
     * Retrieves a message to inform user if the budget has been exceeded.
     *
     * @param budgetLeft        A long value indicating the difference of total monthly expense and monthly budget.
     * @param hasExceededBudget A boolean value indicating whether the budget has been exceeded for the month.
     * @return A budget remaining or exceeding message.
     */
    private static String getBudgetLeftMessage(long budgetLeft, boolean hasExceededBudget, String monthAndYear) {
        if (hasExceededBudget) {
            assert budgetLeft < 0;
            // The absolute value of budget left will be the amount of budget exceeded
            return INFO_EXCEEDING_BUDGET + monthAndYear + INFO_COLON_SPACE + INFO_DOLLAR_SIGN
                    + abs(budgetLeft);
        } else {
            assert budgetLeft >= 0;
            return INFO_REMAINING_BUDGET + monthAndYear + INFO_COLON_SPACE + INFO_DOLLAR_SIGN
                    + budgetLeft;
        }
    }

    /**
     * Retrieves a money managing tips based on whether budget has been exceeded.
     *
     * @param hasExceededBudget A boolean value indicating whether the budget has been exceeded for the month.
     * @return A string containing a money managing tips to the user.
     */
    private static String getMoneyManagingTips(boolean hasExceededBudget) {
        if (hasExceededBudget) {
            return INFO_BUDGET_EXCEEDED_TIPS.toString();
        } else {
            return INFO_BUDGET_NOT_EXCEEDED_TIPS.toString();
        }
    }


}
