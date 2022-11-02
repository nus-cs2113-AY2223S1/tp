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
        SAVE_MONEY_EXCEPTION,
        WITHDRAW_MONEY_EXCEPTION,
        AMOUNT_PARSE_EXCEPTION,
        AMOUNT_NEGATIVE_EXCEPTION,
        CURRENCY_NOT_FOUND,
        NOT_PERSONAL_CURRENCY,
        CURRENCY_ARRAY_FULL,
        SET_DEFAULT_CURRENCY_EXCEPTION,
        INVALID_USERNAME, EXIT_LOGIN_TERMINAL, ACCOUNT_OVERDRAW
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
        case SAVE_MONEY_EXCEPTION:
            errorMessage = "Saving money failed. Example: save sgd 100";
            break;
        case WITHDRAW_MONEY_EXCEPTION:
            errorMessage = "Withdrawing money failed. Example: withdraw sgd 100";
            break;
        case AMOUNT_PARSE_EXCEPTION:
            errorMessage = "Money amount parse failure. Please enter a valid number.";
            break;
        case AMOUNT_NEGATIVE_EXCEPTION:
            errorMessage = "Money amount cannot be negative. Please enter a positive number.";
            break;
        case CURRENCY_NOT_FOUND:
            errorMessage = "Currency type is not valid";
            break;
        case NOT_PERSONAL_CURRENCY:
            errorMessage = "This is not your personal currency and cannot be removed";
            break;
        case CURRENCY_ARRAY_FULL:
            errorMessage = "You have no more space to add new currencies";
            break;
        case ACCOUNT_OVERDRAW:
            errorMessage = "The amount you have entered will lead the account to overdraw\nCancelling withdrawal";
            break;
        case INVALID_USERNAME:
            errorMessage = "The username you have entered is invalid, please enter another username";
            break;
        case EXIT_LOGIN_TERMINAL:
            errorMessage = "You have exited the login sequence";
            break;
        default:
            errorMessage = "Unknown exception happens.";
            break;
        }
        BasicUi.showExceptionMessage(errorMessage);
    }
}
