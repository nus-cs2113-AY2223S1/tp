package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

//@@author xzynos

/**
 * Defines exception to be thrown when the command Add-RecurringPayment is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandAddRecurringPaymentInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandAddRecurringPaymentInvalidException() {
    }

    public ConsoleParserCommandAddRecurringPaymentInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandAddRecurringPaymentInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandAddRecurringPaymentInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandAddRecurringPaymentInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
