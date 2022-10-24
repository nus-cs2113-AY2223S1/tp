package seedu.moneygowhere.exceptions.data.income;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Thrown when an income is not found in the income manager.
 */
@SuppressWarnings("unused")
public class IncomeManagerIncomeNotFoundException extends MoneyGoWhereException {
    public IncomeManagerIncomeNotFoundException() {
    }

    public IncomeManagerIncomeNotFoundException(String message) {
        super(message);
    }

    public IncomeManagerIncomeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncomeManagerIncomeNotFoundException(Throwable cause) {
        super(cause);
    }

    public IncomeManagerIncomeNotFoundException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
