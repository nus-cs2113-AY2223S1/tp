package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

//@@author LokQiJun

/**
 * Defines exception to be thrown when the command View-RecurringPayment is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandMergeExternalFileInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandMergeExternalFileInvalidException() {
    }

    public ConsoleParserCommandMergeExternalFileInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandMergeExternalFileInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandMergeExternalFileInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandMergeExternalFileInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
