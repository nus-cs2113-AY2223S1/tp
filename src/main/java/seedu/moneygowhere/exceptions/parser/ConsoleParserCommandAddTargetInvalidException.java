package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Defines exception to be thrown when the command Add-Target is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandAddTargetInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandAddTargetInvalidException() {
    }

    public ConsoleParserCommandAddTargetInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandAddTargetInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandAddTargetInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandAddTargetInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

