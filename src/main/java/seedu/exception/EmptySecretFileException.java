package seedu.exception;

public class EmptySecretFileException extends ParkingException {
    public EmptySecretFileException() {
        super("");
    }

    public String getMessage() {
        return "API key is not entered. Load it first!";
    }
}
