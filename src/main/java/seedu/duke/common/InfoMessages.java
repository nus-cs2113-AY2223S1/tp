package seedu.duke.common;

/**
 * Provides enum variables for storing custom program information messages.
 */
public enum InfoMessages {
    LINE_SEPARATOR(System.lineSeparator()),
    SPACE(" "),
    FULL_STOP("."),
    FULL_STOP_SPACE(". "),
    COLON_SPACE(": "),
    NEGATIVE_DOLLAR_SIGN("-$"),
    DOLLAR_SIGN("$"),
    INFO_INCOME("Income"),
    INFO_EXPENSE("Expense"),
    INFO_SAVINGS("Savings"),
    INFO_BUDGET("Budget"),
    INFO_SPENDING_HABIT("Spending Habit"),
    INFO_DIVIDER("____________________________________________________________"),
    INFO_ADD_EXPENSE("I have added the following Expense transaction:"),
    INFO_ADD_INCOME("I have added the following Income transaction:"),
    INFO_EDIT_EXPENSE("I have edited the following Expense transaction:"),
    INFO_EDIT_INCOME("I have edited the following Income transaction:"),
    INFO_DELETE("I have deleted the following transaction:"),
    INFO_EXIT("Goodbye and see you soon."),
    INFO_GREET("Hello! I'm Moo and I will help you to manage your finances."),
    INFO_HELP_GREET("Gotcha! Here are the commands that you may use:"),
    INFO_HELP_PROMPT("Enter <help> if you need the list of commands."),
    INFO_LIST("Here are your transaction records:"),
    INFO_LIST_EMPTY("There are no transaction records found."),
    INFO_LIST_FILTERED("Here are the transaction records that match your search expression:"),
    INFO_LIST_FILTERED_EMPTY("There are no transaction records that match your search expression."),
    INFO_LIST_WITHOUT_INDEXES("Indexes are not shown as your transactions have been consolidated for specific time"
            + " periods." + LINE_SEPARATOR + "If you need to know an entry's index, use the Find command to "
            + "search by description."),
    INFO_STATS_EMPTY("There are no statistics available yet for the given statistics type."),
    INFO_STATS_CATEGORY("Here are your net categorical savings:"),
    INFO_STATS_MONTHLY("Here is a summary of your monthly expenditure:"),
    INFO_STATS_HABIT_VERY_HIGH_SAVINGS("Excellent! You saved quite a lot this month."),
    INFO_STATS_HABIT_HIGH_SAVINGS("Wow, keep up the good work. You saved at least two-third of your income."),
    INFO_STATS_HABIT_MEDIUM_SAVINGS("Good effort, you saved at least half of your income."),
    INFO_STATS_HABIT_LOW_SAVINGS("Hmm, you are spending more than 50% of what you earned. "
            + "Do strike a balance between what you earn and what you spend for better savings!"),
    INFO_STATS_HABIT_VERY_LOW_SAVINGS("You spent way more than what you have earned for the current month. "
            + "Please spend wisely based on your income."),
    INFO_STATS_TIME_INSIGHTS("Here are the categorical savings and expenditure summary for"),
    INFO_STATS_CATEGORIES_HEADER("-----Categorical Savings-----"),
    INFO_STATS_EXPENDITURE_HEADER("-----Expenditure Summary-----"),
    INFO_PURGE("All your transactions have been purged."),
    INFO_PURGE_ABORT("Purging has been aborted. All transactions records are retained."),
    INFO_PURGE_EMPTY("The command is aborted as the transactions list is empty."),
    INFO_PURGE_WARNING("Are you sure you want to proceed with this command? Please enter 'Y' to confirm."),
    INFO_BUDGET_SET_SUCCESSFUL("You have successfully updated the budget."),
    INFO_CURRENT_BUDGET("Monthly budget set as: $"),
    INFO_REMAINING_BUDGET("Remaining budget for "),
    INFO_EXCEEDING_BUDGET("Budget exceeded for "),
    INFO_BUDGET_EXCEEDED_TIPS("Consider spending less!"),
    INFO_BUDGET_NOT_EXCEEDED_TIPS("Keep it up!"),
    INFO_BUDGET_EXCEEDED_REMINDER("REMINDER: You have already exceeded the budget set for current month!"),
    INFO_BUDGET_NOT_EXCEEDED_REMINDER("REMINDER: Continue to stay within your budget for this month! Good fortune!"),
    INFO_SAVING_TIPS_AND_BUDGET_ADVICE_SEPARATOR("In terms of monthly budget, "),
    INFO_BUDGET_EXCEEDED_ADVICE_SPENDING_HIGH("you have exceeded more than twice of your budget!"),
    INFO_BUDGET_EXCEEDED_ADVICE_SPENDING_LOW("you have spent more than your budget planned!"),
    INFO_BUDGET_NOT_EXCEEDED_ADVICE_SPENDING_HIGH("you are left with less than half of your budget for the month!"),
    INFO_BUDGET_NOT_EXCEEDED_ADVICE_SPENDING_LOW("you have kept yourself well within the budget!"),
    INFO_BUDGET_NOT_SPENT_ADVICE("you still have the full budget for the month!");

    //@@author chydarren
    public final String message;

    /**
     * Instantiates a new information message when user initialises a new instance of this enum.
     *
     * @param message A string containing the message.
     */
    InfoMessages(String message) {
        this.message = message;
    }

    /**
     * Gets the information message as a string.
     *
     * @return A string containing the message.
     */
    public String toString() {
        return message;
    }
}