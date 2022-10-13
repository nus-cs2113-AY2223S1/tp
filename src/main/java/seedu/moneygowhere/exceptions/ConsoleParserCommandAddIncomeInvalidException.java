package seedu.moneygowhere.exceptions;

/**
 * Thrown when the command add-expense is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandAddIncomeInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandAddIncomeInvalidException() {
    }

    public ConsoleParserCommandAddIncomeInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandAddIncomeInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandAddIncomeInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandAddIncomeInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
