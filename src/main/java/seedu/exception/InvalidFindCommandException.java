package seedu.exception;

/**
 * Exception for when no carpark is found in the list.
 */
public class InvalidFindCommandException extends ParkingException {
    public InvalidFindCommandException(String message) {
        super(message);
    }

}

