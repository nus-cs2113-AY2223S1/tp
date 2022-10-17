package seedu.moneygowhere.exceptions;

/**
 * Thrown when the currency entered is invalid.
 */
@SuppressWarnings("unused")
public class CurrencyInvalidException extends MoneyGoWhereException {
    public CurrencyInvalidException() {
    }

    public CurrencyInvalidException(String message) {
        super(message);
    }

    public CurrencyInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyInvalidException(Throwable cause) {
        super(cause);
    }

    public CurrencyInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
