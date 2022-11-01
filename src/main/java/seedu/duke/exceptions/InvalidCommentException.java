package seedu.duke.exceptions;

public class InvalidCommentException extends Exception {
    private String message;

    public InvalidCommentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
