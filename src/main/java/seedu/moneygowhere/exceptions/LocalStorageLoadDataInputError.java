package seedu.moneygowhere.exceptions;

/**
 * Thrown when there is an error in load data.
 */
@SuppressWarnings("unused")
public class LocalStorageLoadDataInputError extends MoneyGoWhereException {
    public LocalStorageLoadDataInputError() {
    }

    public LocalStorageLoadDataInputError(String message) {
        super(message);
    }

    public LocalStorageLoadDataInputError(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalStorageLoadDataInputError(Throwable cause) {
        super(cause);
    }

    public LocalStorageLoadDataInputError(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
