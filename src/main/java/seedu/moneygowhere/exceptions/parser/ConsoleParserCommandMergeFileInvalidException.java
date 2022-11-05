package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

//@@author LokQiJun

/**
 * Defines exception to be thrown when the command View-RecurringPayment is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandMergeFileInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandMergeFileInvalidException() {
    }

    public ConsoleParserCommandMergeFileInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandMergeFileInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandMergeFileInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandMergeFileInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
