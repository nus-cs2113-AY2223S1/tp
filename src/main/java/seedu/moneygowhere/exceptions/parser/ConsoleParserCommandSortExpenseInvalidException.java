package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Defines exception to be thrown when the arguments for command Sort-Expense is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandSortExpenseInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandSortExpenseInvalidException() {
    }

    public ConsoleParserCommandSortExpenseInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandSortExpenseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandSortExpenseInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandSortExpenseInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
