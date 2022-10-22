package seedu.moneygowhere.exceptions;

@SuppressWarnings("unused")
public class ConsoleParserCommandDeleteIncomeInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandDeleteIncomeInvalidException() {
    }

    public ConsoleParserCommandDeleteIncomeInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandDeleteIncomeInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandDeleteIncomeInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandDeleteIncomeInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
