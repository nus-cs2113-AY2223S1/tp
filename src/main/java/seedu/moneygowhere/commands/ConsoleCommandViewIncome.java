package seedu.moneygowhere.commands;

//@@author penguin-s

/**
 * Stores the command View-Income and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandViewIncome extends ConsoleCommand {
    private int incomeIndex;

    public ConsoleCommandViewIncome(int incomeIndex) {
        this.incomeIndex = incomeIndex;
    }

    public int getIncomeIndex() {
        return incomeIndex;
    }
}
