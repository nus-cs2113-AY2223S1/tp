package seedu.moneygowhere.exceptions;

/**
 * Thrown when the arguments for command sort-expense is invalid.
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
