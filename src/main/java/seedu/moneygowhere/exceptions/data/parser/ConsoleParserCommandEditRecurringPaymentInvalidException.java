package seedu.moneygowhere.exceptions.data.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Thrown when the command Edit-RecurringPayment is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandEditRecurringPaymentInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandEditRecurringPaymentInvalidException() {
    }

    public ConsoleParserCommandEditRecurringPaymentInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandEditRecurringPaymentInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandEditRecurringPaymentInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandEditRecurringPaymentInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
