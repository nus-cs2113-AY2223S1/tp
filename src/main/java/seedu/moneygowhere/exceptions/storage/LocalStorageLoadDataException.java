package seedu.moneygowhere.exceptions.storage;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Defines exception to be thrown when there is an error in load data.
 */
@SuppressWarnings("unused")
public class LocalStorageLoadDataException extends MoneyGoWhereException {
    public LocalStorageLoadDataException() {
    }

    public LocalStorageLoadDataException(String message) {
        super(message);
    }

    public LocalStorageLoadDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalStorageLoadDataException(Throwable cause) {
        super(cause);
    }

    public LocalStorageLoadDataException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
