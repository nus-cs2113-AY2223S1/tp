package seedu.exception;

/**
 * Exception when no response is received from the API.
 */
public class EmptyResponseException extends ParkingException {
    public EmptyResponseException() {
        super();
    }

    @Override
    public String getMessage() {
        return "No response was received. Please check your internet connection.";
    }
}
