package seedu.duke.exceptions;

public class InvalidUserCommandException extends Exception {
    private String message;

    public InvalidUserCommandException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
