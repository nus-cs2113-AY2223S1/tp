package seedu.moneygowhere.exceptions.data.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Thrown when the command add-recurringpayment is invalid.
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
