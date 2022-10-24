package seedu.moneygowhere.commands;

//@@author penguin-s

/**
 * Stores the command Delete-Income and its arguments.
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
