package seedu.moneygowhere.data.recurringpayments;

import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.data.recurringpayment.RecurringPaymentManagerRecurringPaymentNotFoundException;

import java.util.ArrayList;

/**
 * Stores and manages a list of recurring payments.
 */
@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class RecurringPaymentManager {
    private ArrayList<RecurringPayment> recurringPayments;

    public RecurringPaymentManager() {
        recurringPayments = new ArrayList<>();
    }

    public void addRecurringPayment(RecurringPayment recurringPayment) {
        recurringPayments.add(recurringPayment);
    }

    public RecurringPayment getRecurringPayment(int recurringPaymentIndex) throws
            RecurringPaymentManagerRecurringPaymentNotFoundException {
        try {
            return recurringPayments.get(recurringPaymentIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new RecurringPaymentManagerRecurringPaymentNotFoundException(
                    Messages.RECURRING_PAYMENT_MANAGER_RECURRING_PAYMENT_NOT_FOUND
            );
        }
    }

    public ArrayList<RecurringPayment> getRecurringPayments() {
        return recurringPayments;
    }

    public void deleteRecurringPayment(int recurringPaymentIndex) throws
            RecurringPaymentManagerRecurringPaymentNotFoundException {
        try {
            recurringPayments.remove(recurringPaymentIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new RecurringPaymentManagerRecurringPaymentNotFoundException(
                    Messages.RECURRING_PAYMENT_MANAGER_RECURRING_PAYMENT_NOT_FOUND
            );
        }
    }

    public void editRecurringPayment(int recurringPaymentIndex, RecurringPayment recurringPayment) throws
            RecurringPaymentManagerRecurringPaymentNotFoundException {
        try {
            recurringPayments.set(recurringPaymentIndex, recurringPayment);
        } catch (IndexOutOfBoundsException exception) {
            throw new RecurringPaymentManagerRecurringPaymentNotFoundException(
                    Messages.RECURRING_PAYMENT_MANAGER_RECURRING_PAYMENT_NOT_FOUND
            );
        }
    }
}
