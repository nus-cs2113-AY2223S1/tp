package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Defines exception to be thrown when the command Add-Expense is invalid.
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
