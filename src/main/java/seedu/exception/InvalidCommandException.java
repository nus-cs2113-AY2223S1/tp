package seedu.exception;

/**
 * Exception for when the command input is invalid.
 */
public class InvalidCommandException extends ParkingException {
    /**
     * Constructor for the exception.
     */
    public InvalidCommandException() {
        super();
    }

    /**
     * Returns a message saying there is no such command.
     *
     * @return Message.
     */
    @Override
    public String getMessage() {
        return "Invalid Command.";
    }
}
