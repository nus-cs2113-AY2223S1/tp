package seedu.exception;

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
     * @return Formatted string.
     */
    @Override
    public String getMessage() {
        return "Invalid Command.";
    }
}
