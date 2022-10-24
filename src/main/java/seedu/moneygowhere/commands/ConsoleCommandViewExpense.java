package seedu.moneygowhere.commands;

//@@author xzynos

/**
 * Stores the command View-Expense and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandViewExpense extends ConsoleCommand {
    private int expenseIndex;
    private String expenseCategory;
    private String expenseName;

    public ConsoleCommandViewExpense(int expenseIndex, String expenseCategory, String expenseName) {
        this.expenseIndex = expenseIndex;
        this.expenseCategory = expenseCategory;
        this.expenseName = expenseName;
    }

    public int getExpenseIndex() {
        return expenseIndex;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public String getExpenseName() {
        return expenseName;
    }
}
