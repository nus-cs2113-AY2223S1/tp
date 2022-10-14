package seedu.moneygowhere.commands;

/**
 * Stores the view-recurringpayment command and its arguments.
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
