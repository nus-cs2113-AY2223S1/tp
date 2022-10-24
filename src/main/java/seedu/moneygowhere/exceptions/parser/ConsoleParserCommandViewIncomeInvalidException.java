package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

//@@author penguin-s
@SuppressWarnings("unused")
public class ConsoleParserCommandViewIncomeInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandViewIncomeInvalidException() {
    }

    public ConsoleParserCommandViewIncomeInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandViewIncomeInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandViewIncomeInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandViewIncomeInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
