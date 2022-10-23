package seedu.moneygowhere.commands;

/**
 * Stores the delete-income command and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandDeleteIncome extends ConsoleCommand {
    private int incomeIndex;

    public ConsoleCommandDeleteIncome(int incomeIndex) {
        this.incomeIndex = incomeIndex;
    }

    public int getIncomeIndex() {
        return incomeIndex;
    }
}
