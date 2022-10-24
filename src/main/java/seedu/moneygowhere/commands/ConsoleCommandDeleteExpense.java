package seedu.moneygowhere.commands;

/**
 * Stores the command Delete-Expense and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandDeleteExpense extends ConsoleCommand {
    private int expenseIndex;

    public ConsoleCommandDeleteExpense(int expenseIndex) {
        this.expenseIndex = expenseIndex;
    }

    public int getExpenseIndex() {
        return expenseIndex;
    }
}
