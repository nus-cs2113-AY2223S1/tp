package seedu.moneygowhere.exceptions;

/**
 * Thrown when the command sort-expense is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandSortExpenseInvalidTypeException extends MoneyGoWhereException {
    public ConsoleParserCommandSortExpenseInvalidTypeException() {
    }

    public ConsoleParserCommandSortExpenseInvalidTypeException(String message) {
        super(message);
    }

    public ConsoleParserCommandSortExpenseInvalidTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandSortExpenseInvalidTypeException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandSortExpenseInvalidTypeException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
