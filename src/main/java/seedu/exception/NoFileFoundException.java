package seedu.exception;

/**
 * Exception for when no file was found.
 */
public class NoFileFoundException extends ParkingException {
    private final String message;

    /**
     * Constructor for the exception.
     *
     * @param message Message to be printed.
     */
    public NoFileFoundException(String message) {
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
