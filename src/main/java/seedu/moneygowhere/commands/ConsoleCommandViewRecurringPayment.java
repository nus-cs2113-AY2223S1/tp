package seedu.moneygowhere.commands;

//@@author xzynos

/**
 * Stores the command View-RecurringPayment and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandViewRecurringPayment extends ConsoleCommand {
    private int recurringPaymentIndex;

    public ConsoleCommandViewRecurringPayment(int recurringPaymentIndex) {
        this.recurringPaymentIndex = recurringPaymentIndex;
    }

    public int getRecurringPaymentIndex() {
        return recurringPaymentIndex;
    }
}
