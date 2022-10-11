package seedu.exception;

/**
 * Exception when no response is received from the API.
 */
public class EmptyResponseException extends ParkingException {
    public EmptyResponseException(String message) {
        super(message);
    }
}
