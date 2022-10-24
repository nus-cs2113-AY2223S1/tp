package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

//@@author xzynos

/**
 * Defines exception to be thrown when the command Add-Target is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandDeleteRecurringPaymentInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandDeleteRecurringPaymentInvalidException() {
    }

    public ConsoleParserCommandDeleteRecurringPaymentInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandDeleteRecurringPaymentInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandDeleteRecurringPaymentInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandDeleteRecurringPaymentInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
