package seedu.moneygowhere.exceptions;

/**
 * Thrown when the command convert-currency is invalid.
 */
@SuppressWarnings("unused")
public class ConsoleParserCommandConvertCurrencyInvalidException extends MoneyGoWhereException {
    public ConsoleParserCommandConvertCurrencyInvalidException() {
    }

    public ConsoleParserCommandConvertCurrencyInvalidException(String message) {
        super(message);
    }

    public ConsoleParserCommandConvertCurrencyInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleParserCommandConvertCurrencyInvalidException(Throwable cause) {
        super(cause);
    }

    public ConsoleParserCommandConvertCurrencyInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

