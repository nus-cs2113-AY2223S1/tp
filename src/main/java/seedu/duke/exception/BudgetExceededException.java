package seedu.duke.exception;

public class BudgetExceededException extends DukeCommandException {

    private final String message;

    public BudgetExceededException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
