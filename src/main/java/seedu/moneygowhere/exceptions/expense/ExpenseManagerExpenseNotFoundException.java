package seedu.moneygowhere.exceptions.expense;

import seedu.moneygowhere.exceptions.MoneyGoWhereException;

/**
 * Thrown when an expense is not found in expense manager.
 */
@SuppressWarnings("unused")
public class ExpenseManagerExpenseNotFoundException extends MoneyGoWhereException {
    public ExpenseManagerExpenseNotFoundException() {
    }

    public ExpenseManagerExpenseNotFoundException(String message) {
        super(message);
    }

    public ExpenseManagerExpenseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpenseManagerExpenseNotFoundException(Throwable cause) {
        super(cause);
    }

    public ExpenseManagerExpenseNotFoundException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
