package seedu.moneygowhere.commands;

/**
 * Stores the delete-expense command and its arguments.
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
