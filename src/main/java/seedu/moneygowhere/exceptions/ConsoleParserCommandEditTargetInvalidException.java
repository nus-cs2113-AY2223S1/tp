package seedu.moneygowhere.exceptions;

@SuppressWarnings("unused")
public class ConsoleParserCommandEditTargetInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandEditTargetInvalidException() {
    }

    public ConsoleParserCommandEditTargetInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandEditTargetInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandEditTargetInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandEditTargetInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
