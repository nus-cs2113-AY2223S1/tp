package seedu.exception;

/**
 * Exception when no response is received from the API.
 */
public class EmptyResponseException extends ParkingException {
    /**
     * Constructor for the exception.
     */
    public EmptyResponseException() {
        super();
    }

    /**
     * Return message string.
     */
    @Override
    public String getMessage() {
        return "No response was received. Please check your internet connection.";
    }
}
