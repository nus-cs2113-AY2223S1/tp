package seedu.exception;

/**
 * Exception for when no file was found.
 */
public class NoFileFoundException extends ParkingException {
    public NoFileFoundException(String message) {
        super(message);
    }
}
