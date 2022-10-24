package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Thrown when the command view-recurringpayment is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandViewRecurringPaymentInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandViewRecurringPaymentInvalidException() {
    }

    public ConsoleParserCommandViewRecurringPaymentInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandViewRecurringPaymentInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandViewRecurringPaymentInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandViewRecurringPaymentInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
