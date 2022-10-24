package seedu.moneygowhere.exceptions.data.currency;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Thrown when there is an error in currency load data.
 */
@SuppressWarnings("unused")
public class CurrencyLoadDataInputException extends MoneyGoWhereException {
    public CurrencyLoadDataInputException() {
    }

    public CurrencyLoadDataInputException(String message) {
        super(message);
    }

    public CurrencyLoadDataInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyLoadDataInputException(Throwable cause) {
        super(cause);
    }

    public CurrencyLoadDataInputException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
