package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

//@@author xzynos

/**
 * Defines exception to be thrown when the command Pay-RecurringPayment is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandPayRecurringPaymentInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandPayRecurringPaymentInvalidException() {
    }

    public ConsoleParserCommandPayRecurringPaymentInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandPayRecurringPaymentInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandPayRecurringPaymentInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandPayRecurringPaymentInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
