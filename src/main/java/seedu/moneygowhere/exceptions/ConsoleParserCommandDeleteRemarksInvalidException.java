package seedu.moneygowhere.exceptions;

@SuppressWarnings("unused")
public class ConsoleParserCommandDeleteRemarksInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandDeleteRemarksInvalidException() {
    }

    public ConsoleParserCommandDeleteRemarksInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandDeleteRemarksInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandDeleteRemarksInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandDeleteRemarksInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
