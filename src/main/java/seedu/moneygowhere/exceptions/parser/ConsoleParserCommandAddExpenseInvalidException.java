package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Thrown when the command add-expense is invalid.
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
