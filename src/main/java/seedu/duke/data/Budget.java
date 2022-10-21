package seedu.duke.data;

/**
 * Represents the user's budget for the current month.
 *
 * <p>The default budget value is $1000, or the value read from the file storage.
 * Operations related to the budgets are also defined under this class.
 */
public class Budget {
    // Default value of the monthly budget is $1000
    private static int budget = 1000;

    /**
     * Retrieves the budget value set for the current month
     *
     * @return The budget value set by the user
     */
    public static int getBudget() {
        return budget;
    }

    /**
     * Updates the budget value set for the current month
     *
     * @param budget The new value for budget
     */
    public static void setBudget(int budget) {
        Budget.budget = budget;
    }

    /**
     * Return the amount of budget left in
     *
     * @param transactionList
     * @return
     */
    public static String getBudgetLeft(TransactionList transactionList) {

        return "";
    }

}
