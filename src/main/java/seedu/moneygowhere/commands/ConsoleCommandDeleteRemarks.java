package seedu.moneygowhere.commands;

/**
 * Stores the delete-expense command and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandDeleteRemarks extends ConsoleCommand {
    private int expenseIndex;

    public ConsoleCommandDeleteRemarks(int expenseIndex) {
        this.expenseIndex = expenseIndex;
    }

    public int getExpenseIndex() {
        return expenseIndex;
    }
}
