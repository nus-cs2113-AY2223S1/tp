package seedu.moneygowhere.commands;

/**
 * Stores the view-expense command and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandViewExpense extends ConsoleCommand {
    private int expenseIndex;

    public ConsoleCommandViewExpense(int expenseIndex) {
        this.expenseIndex = expenseIndex;
    }

    public int getExpenseIndex() {
        return expenseIndex;
    }
}
