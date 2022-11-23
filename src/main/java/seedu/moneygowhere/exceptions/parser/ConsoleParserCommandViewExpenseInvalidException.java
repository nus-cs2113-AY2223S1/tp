package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

//@@author xzynos

/**
 * Defines exception to be thrown when the command View-Expense is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandViewExpenseInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandViewExpenseInvalidException() {
    }

    public ConsoleParserCommandViewExpenseInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandViewExpenseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandViewExpenseInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandViewExpenseInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
