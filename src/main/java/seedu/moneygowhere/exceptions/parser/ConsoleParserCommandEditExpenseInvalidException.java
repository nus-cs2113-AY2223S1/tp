package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

//@@author xzynos

/**
 * Defines exception to be thrown when the command Edit-Expense is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandEditExpenseInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandEditExpenseInvalidException() {
    }

    public ConsoleParserCommandEditExpenseInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandEditExpenseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandEditExpenseInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandEditExpenseInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
