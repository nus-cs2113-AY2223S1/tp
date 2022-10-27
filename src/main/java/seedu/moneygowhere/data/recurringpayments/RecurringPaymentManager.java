package seedu.moneygowhere.data.recurringpayments;

import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.data.recurringpayment.RecurringPaymentManagerRecurringPaymentNotFoundException;
import seedu.moneygowhere.storage.LocalStorage;

import java.util.ArrayList;

//@@author xzynos

/**
 * Stores and manages a list of recurring payments.
 */
@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class RecurringPaymentManager {
    private ArrayList<RecurringPayment> recurringPayments;

    public RecurringPaymentManager() {
        recurringPayments = new ArrayList<>();
    }

    public void addRecurringPayment(RecurringPayment recurringPayment, LocalStorage localStorage) {
        recurringPayments.add(recurringPayment);
        localStorage.setSavedRecurringPayments(recurringPayments);
    }

    public RecurringPayment getRecurringPayment(int recurringPaymentIndex) throws
            RecurringPaymentManagerRecurringPaymentNotFoundException {
        try {
            return recurringPayments.get(recurringPaymentIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new RecurringPaymentManagerRecurringPaymentNotFoundException(
                    Messages.RECURRING_PAYMENT_MANAGER_ERROR_RECURRING_PAYMENT_NOT_FOUND
            );
        }
    }

    public ArrayList<RecurringPayment> getRecurringPayments() {
        return recurringPayments;
    }

    //@@author LokQiJun
    public void setRecurringPayments(ArrayList<RecurringPayment> savedRecurringPayments) {
        this.recurringPayments = new ArrayList<RecurringPayment>(savedRecurringPayments);
    }

    public void deleteRecurringPayment(int recurringPaymentIndex,
                                       LocalStorage localStorage) throws
            RecurringPaymentManagerRecurringPaymentNotFoundException {
        try {
            recurringPayments.remove(recurringPaymentIndex);
            localStorage.setSavedRecurringPayments(recurringPayments);
        } catch (IndexOutOfBoundsException exception) {
            throw new RecurringPaymentManagerRecurringPaymentNotFoundException(
                    Messages.RECURRING_PAYMENT_MANAGER_ERROR_RECURRING_PAYMENT_NOT_FOUND
            );
        }
    }

    public void editRecurringPayment(int recurringPaymentIndex, RecurringPayment recurringPayment,
                                     LocalStorage localStorage) throws
            RecurringPaymentManagerRecurringPaymentNotFoundException {
        try {
            recurringPayments.set(recurringPaymentIndex, recurringPayment);
            localStorage.setSavedRecurringPayments(recurringPayments);
        } catch (IndexOutOfBoundsException exception) {
            throw new RecurringPaymentManagerRecurringPaymentNotFoundException(
                    Messages.RECURRING_PAYMENT_MANAGER_ERROR_RECURRING_PAYMENT_NOT_FOUND
            );
        }
    }
}
