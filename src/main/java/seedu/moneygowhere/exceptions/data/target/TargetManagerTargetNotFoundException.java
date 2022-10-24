package seedu.moneygowhere.exceptions.data.target;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Defines exception to be thrown when a target is not found in target manager.
 */
@SuppressWarnings("unused")
public class TargetManagerTargetNotFoundException extends MoneyGoWhereException {
    public TargetManagerTargetNotFoundException() {
    }

    public TargetManagerTargetNotFoundException(String message) {
        super(message);
    }

    public TargetManagerTargetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TargetManagerTargetNotFoundException(Throwable cause) {
        super(cause);
    }

    public TargetManagerTargetNotFoundException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
