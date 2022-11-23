package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

//@@author penguin-s
@SuppressWarnings("unused")
public class ConsoleParserCommandDeleteTargetInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandDeleteTargetInvalidException() {
    }

    public ConsoleParserCommandDeleteTargetInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandDeleteTargetInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandDeleteTargetInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandDeleteTargetInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
