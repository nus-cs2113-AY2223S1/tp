package seedu.moneygowhere.exceptions.data.recurringpayment;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Defines exception to be thrown when a recurring payment is not found in recurring payment manager.
 */
@SuppressWarnings("unused")
public class RecurringPaymentManagerRecurringPaymentNotFoundException extends MoneyGoWhereException {
    public RecurringPaymentManagerRecurringPaymentNotFoundException() {
    }

    public RecurringPaymentManagerRecurringPaymentNotFoundException(String message) {
        super(message);
    }

    public RecurringPaymentManagerRecurringPaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecurringPaymentManagerRecurringPaymentNotFoundException(Throwable cause) {
        super(cause);
    }

    public RecurringPaymentManagerRecurringPaymentNotFoundException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
