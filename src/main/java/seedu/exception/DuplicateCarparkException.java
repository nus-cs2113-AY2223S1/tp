package seedu.exception;

/**
 * Exception for when carpark is already in a list.
 */
public class DuplicateCarparkException extends ParkingException {
    /**
     * Constructor for the exception.
     */
    public DuplicateCarparkException() {
        super();
    }

    /**
     * Returns a message saying carpark was already in list.
     *
     * @return Message.
     */
    @Override
    public String getMessage() {
        return "Carpark already in list.";
    }
}
