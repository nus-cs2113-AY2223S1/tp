package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

@SuppressWarnings("unused")
public class ConsoleParserCommandViewExpenseInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandViewExpenseInvalidException() {
    }

    public ConsoleParserCommandViewExpenseInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandViewExpenseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandViewExpenseInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandViewExpenseInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
