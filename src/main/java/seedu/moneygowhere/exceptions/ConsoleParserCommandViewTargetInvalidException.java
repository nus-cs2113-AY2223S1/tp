package seedu.moneygowhere.exceptions;

@SuppressWarnings("unused")
public class ConsoleParserCommandViewTargetInvalidException extends MoneyGoWhereException{
    public ConsoleParserCommandViewTargetInvalidException(){
    }

    public ConsoleParserCommandViewTargetInvalidException(String message){ super(message); }

    public ConsoleParserCommandViewTargetInvalidException(String message, Throwable cause){ super(message, cause); }

    public ConsoleParserCommandViewTargetInvalidException(Throwable cause){ super(cause); }

    public ConsoleParserCommandViewTargetInvalidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
