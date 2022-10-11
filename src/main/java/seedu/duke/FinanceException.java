package seedu.duke;

public class FinanceException extends Throwable {
    public enum exceptionCollection {
        COMMAND_TYPE_EXCEPTION,
        FILE_NOT_FOUND,
        UNKNOWN_EXCEPTION
    }
    private exceptionCollection exceptionType;
    public FinanceException(exceptionCollection exception) {
        super();
        this.exceptionType = exception;
    }

    public void handleException() {
        String errorMessage;
        switch (exceptionType) {
        case COMMAND_TYPE_EXCEPTION:
            errorMessage = "Command type not correct.";
            break;
        case FILE_NOT_FOUND:
            errorMessage = "File not found.";
            break;
        default:
            errorMessage = "Unknown exception happens.";
            break;
        }
        Ui.showExceptionMessage(errorMessage);
    }
}
