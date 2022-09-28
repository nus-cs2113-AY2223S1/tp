package seedu.moneygowhere.exceptions;

@SuppressWarnings("unused")
public class ConsoleParserCommandNotFoundException extends MoneyGoWhereException {
    public ConsoleParserCommandNotFoundException() {
    }

    public ConsoleParserCommandNotFoundException(String message) {
        super(message);
    }

    public ConsoleParserCommandNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandNotFoundException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandNotFoundException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
