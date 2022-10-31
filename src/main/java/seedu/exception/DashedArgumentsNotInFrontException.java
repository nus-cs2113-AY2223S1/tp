package seedu.exception;

/**
 * For if the command format is invalid.
 */
public class DashedArgumentsNotInFrontException extends ParkingException {
    /**
     * Constructor for the exception.
     */
    public DashedArgumentsNotInFrontException() {
        super();
    }

    /**
     * Returns a message saying no carpark was found.
     *
     * @return Formatted string.
     */
    @Override
    public String getMessage() {
        return "Any dashed argument must be to the left of any other arguments. Please try again.";
    }
}
