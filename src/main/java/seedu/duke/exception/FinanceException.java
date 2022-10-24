package seedu.duke.exception;

import seedu.duke.BasicUi;

public class FinanceException extends Throwable {
    public enum ExceptionCollection {
        COMMAND_TYPE_EXCEPTION,
        USERNAME_FILE_NOT_FOUND_EXCEPTION,
        USERFILE_CREATE_EXCEPTION,
        USERFILE_WRITE_EXCEPTION,
        CURRENCY_NAME_NOT_FOUND_EXCEPTION,
        CURRENCY_FILE_NOT_FOUND_EXCEPTION,
        WALLET_FILE_NOT_FOUND_EXCEPTION,
        WALLET_FILE_OCCUPIED_EXCEPTION,
        WALLET_FILE_DELETION_EXCEPTION,
        SET_DEFAULT_CURRENCY_EXCEPTION,
        SAVE_MONEY_EXCEPTION,
        WITHDRAW_MONEY_EXCEPTION,
        AMOUNT_PARSE_EXCEPTION,
        AMOUNT_NEGATIVE_EXCEPTION,
        //CURRENCY_INVALID_EXCEPTION
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
        case CURRENCY_NAME_NOT_FOUND_EXCEPTION:
            errorMessage = "Currency name is invalid. Please use the three-letter abbreviation name. Example: sgd";
            break;
        case CURRENCY_FILE_NOT_FOUND_EXCEPTION:
            errorMessage = "Currency info file not found. Program exited.";
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
        case SET_DEFAULT_CURRENCY_EXCEPTION:
            errorMessage = "Setting default currency failed. Example: setDefault sgd";
            break;
        case SAVE_MONEY_EXCEPTION:
            errorMessage = "Saving money failed. Example: save sgd 100";
            break;
        case WITHDRAW_MONEY_EXCEPTION:
            errorMessage = "Saving money failed. Example: withdraw sgd 100";
            break;
        case AMOUNT_PARSE_EXCEPTION:
            errorMessage = "Money amount parse failure. Please enter a valid number.";
            break;
        case AMOUNT_NEGATIVE_EXCEPTION:
            errorMessage = "Money amount cannot be negative. Please enter a positive number.";
            break;
        default:
            errorMessage = "Unknown exception happens.";
            break;
        }
        BasicUi.showExceptionMessage(errorMessage);
    }
}
