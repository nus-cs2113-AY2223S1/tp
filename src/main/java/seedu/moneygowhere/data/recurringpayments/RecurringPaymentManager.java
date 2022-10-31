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

    //@@author xzynos
    public RecurringPaymentManager() {
        recurringPayments = new ArrayList<>();
    }

    //@@author xzynos
    public void addRecurringPayment(RecurringPayment recurringPayment, LocalStorage localStorage) {
        recurringPayments.add(recurringPayment);
        localStorage.setSavedRecurringPayments(recurringPayments);
    }

    public boolean hasRecurringPayment(RecurringPayment recurringPayment) {
        return recurringPayments.contains(recurringPayment);
    }

    //@@author xzynos
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

    //@@author xzynos
    public ArrayList<RecurringPayment> getRecurringPayments() {
        return recurringPayments;
    }

    //@@author LokQiJun
    public void setRecurringPayments(ArrayList<RecurringPayment> savedRecurringPayments) {
        this.recurringPayments = new ArrayList<>(savedRecurringPayments);
    }

    //@@author xzynos
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

    //@@author xzynos
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
