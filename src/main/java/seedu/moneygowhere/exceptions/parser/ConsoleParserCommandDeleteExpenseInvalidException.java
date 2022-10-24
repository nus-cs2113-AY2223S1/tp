package seedu.moneygowhere.exceptions.parser;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

@SuppressWarnings("unused")
public class ConsoleParserCommandDeleteExpenseInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandDeleteExpenseInvalidException() {
    }

    public ConsoleParserCommandDeleteExpenseInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandDeleteExpenseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandDeleteExpenseInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandDeleteExpenseInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
