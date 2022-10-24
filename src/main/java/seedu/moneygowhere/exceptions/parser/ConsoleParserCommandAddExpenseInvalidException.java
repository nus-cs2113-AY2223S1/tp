package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Defines exception to be thrown when the command Add-Expense is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandAddExpenseInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandAddExpenseInvalidException() {
    }

    public ConsoleParserCommandAddExpenseInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandAddExpenseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandAddExpenseInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandAddExpenseInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
