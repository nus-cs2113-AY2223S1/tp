package seedu.moneygowhere.exceptions;

@SuppressWarnings("unused")
public class ConsoleParserCommandEditIncomeInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandEditIncomeInvalidException() {
    }

    public ConsoleParserCommandEditIncomeInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandEditIncomeInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandEditIncomeInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandEditIncomeInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

