package seedu.duke;

public class FinanceException extends Throwable {
    public enum ExceptionCollection {
        COMMAND_TYPE_EXCEPTION,
        USERNAME_FILE_NOT_FOUND_EXCEPTION,
        USERFILE_CREATE_EXCEPTION,
        USERFILE_WRITE_EXCEPTION,
        CURRENCY_NOT_FOUND_EXCEPTION,
        CURRENCY_FILE_NOT_FOUND_EXCEPTION,
        WALLET_FILE_NOT_FOUND_EXCEPTION,
        WALLET_FILE_OCCUPIED_EXCEPTION,
        WALLET_FILE_DELETION_EXCEPTION,
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
        case USERNAME_FILE_NOT_FOUND_EXCEPTION:
            errorMessage = "username files not found.";
            break;
        case USERFILE_CREATE_EXCEPTION:
            errorMessage = "Userfile created failed.";
            break;
        case USERFILE_WRITE_EXCEPTION:
            errorMessage = "Userfile writing failed.";
            break;
        case CURRENCY_NOT_FOUND_EXCEPTION:
            errorMessage = "Currency type is not valid.";
            break;
        case CURRENCY_FILE_NOT_FOUND_EXCEPTION:
            errorMessage = "Currency file not found.";
            break;
        case WALLET_FILE_NOT_FOUND_EXCEPTION:
            errorMessage = "Wallet file not found.";
            break;
        case WALLET_FILE_OCCUPIED_EXCEPTION:
            errorMessage = "Wallet file occupied by file system so cannot be operated.";
            break;
        case WALLET_FILE_DELETION_EXCEPTION:
            errorMessage = "Deletion operation failed.";
            break;
        default:
            errorMessage = "Unknown exception happens.";
            break;
        }
        Ui.showExceptionMessage(errorMessage);
    }
}
