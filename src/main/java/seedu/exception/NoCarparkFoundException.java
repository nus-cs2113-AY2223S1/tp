package seedu.exception;

/**
 * Exception for when no carpark is found.
 */
public class NoCarparkFoundException extends ParkingException {
    /**
     * Constructor for the exception.
     */
    public NoCarparkFoundException() {
        super();
    }

    /**
     * Returns a message saying no carpark was found.
     *
     * @return Formatted string.
     */
    @Override
    public String getMessage() {
        return "No carpark was found.";
    }
}
