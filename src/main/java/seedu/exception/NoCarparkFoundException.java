package seedu.exception;

/**
 * Exception for when no carpark is found in the list.
 */
public class NoCarparkFoundException extends ParkingException {
    public NoCarparkFoundException(String message) {
        super(message);
    }
}
