package seedu.moneygowhere.exceptions;

public class MoneyGoWhereException extends Exception {
    public MoneyGoWhereException() {
    }

    public MoneyGoWhereException(String message) {
        super(message);
    }

    public MoneyGoWhereException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoneyGoWhereException(Throwable cause) {
        super(cause);
    }

    public MoneyGoWhereException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
