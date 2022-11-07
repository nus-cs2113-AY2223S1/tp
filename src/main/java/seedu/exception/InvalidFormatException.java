package seedu.exception;

/**
 * Exception for when no file was found.
 */
public class InvalidFormatException extends ParkingException {
    private final String message;

    /**
     * Constructor for the exception.
     *
     * @param message message to be printed.
     */
    public InvalidFormatException(String message) {
        super();
        this.message = message;
    }

    /**
     * Returns a message.
     *
     * @return Message.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
