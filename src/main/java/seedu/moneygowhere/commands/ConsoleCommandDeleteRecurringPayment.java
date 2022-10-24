package seedu.moneygowhere.commands;

/**
 * Stores the command Delete-RecurringPayment and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandDeleteRecurringPayment extends ConsoleCommand {
    private int recurringPaymentIndex;

    public ConsoleCommandDeleteRecurringPayment(int recurringPaymentIndex) {
        this.recurringPaymentIndex = recurringPaymentIndex;
    }

    public int getRecurringPaymentIndex() {
        return recurringPaymentIndex;
    }
}
