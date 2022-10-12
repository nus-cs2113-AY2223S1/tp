package seedu.exception;


/**
 * Exception for when API key is not entered.
 */
public class EmptySecretFileException extends ParkingException {
    public EmptySecretFileException() {
        super("");
    }

    public String getMessage() {
        return "API key is not entered. Load it first!";
    }
}
