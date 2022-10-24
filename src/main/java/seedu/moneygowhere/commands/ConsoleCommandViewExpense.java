package seedu.moneygowhere.commands;

//@@author xzynos

/**
 * Stores the command View-Expense and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandViewExpense extends ConsoleCommand {
    private int expenseIndex;
    private String expenseCategory;

    public ConsoleCommandViewExpense(int expenseIndex, String expenseCategory) {
        this.expenseIndex = expenseIndex;
        this.expenseCategory = expenseCategory;
    }

    public int getExpenseIndex() {
        return expenseIndex;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }
}
