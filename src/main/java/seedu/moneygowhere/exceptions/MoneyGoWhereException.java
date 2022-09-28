package seedu.moneygowhere.exceptions;

/**
 * Define exceptions thrown the program.
 */
public class MoneyGoWhereException extends Exception {
    /**
     * Initializes a new exception.
     */
    public MoneyGoWhereException() {
    }

    /**
     * Initializes a new exception with a detail message.
     *
     * @param message Detail message.
     */
    public MoneyGoWhereException(String message) {
        super(message);
    }

    /**
     * Initializes a new exception with a detail message and cause.
     *
     * @param message Detail message.
     * @param cause Throwable cause.
     */
    public MoneyGoWhereException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Initializes a new exception with a cause.
     *
     * @param cause Throwable cause.
     */
    public MoneyGoWhereException(Throwable cause) {
        super(cause);
    }

    /**
     * Initializes a new exception with a detail message and cause.
     * Suppression and writable stack trace can be enabled or disabled.
     *
     * @param message Detail message.
     * @param cause Throwable cause.
     * @param enableSuppression Enables or disables suppression.
     * @param writableStackTrace Enables or disables writable stack trace.
     */
    public MoneyGoWhereException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
