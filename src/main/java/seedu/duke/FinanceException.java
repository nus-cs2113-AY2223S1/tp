package seedu.duke;

public class FinanceException extends Throwable {
    public enum ExceptionCollection {
        COMMAND_TYPE_EXCEPTION
        //UNKNOWN_EXCEPTION
    }

    private final ExceptionCollection exceptionType;

    public FinanceException (ExceptionCollection exception) {
        super();
        this.exceptionType = exception;
    }

    public void handleException() {
        String errorMessage;
        switch (exceptionType) {
        case COMMAND_TYPE_EXCEPTION:
            errorMessage = "Command type not correct.";
            break;
        default:
            errorMessage = "Unknown exception happens.";
            break;
        }
        Ui.showExceptionMessage(errorMessage);
    }
}
