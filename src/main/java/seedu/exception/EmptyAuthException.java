package seedu.exception;

/**
 * Exception when no API Key is entered during auth.
 */
public class EmptyAuthException extends ParkingException {
    public EmptyAuthException(String message) {
        super(message);
    }

}

