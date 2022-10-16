package seedu.exception;

/**
 * Exception for when an argument is needed after
 * a command but none are given.
 */
public class NoCommandArgumentException extends ParkingException {
    private final String command;

    /**
     * Constructor for the exception.
     *
     * @param command command to display in message.
     */
    public NoCommandArgumentException(String command) {
        super();
        this.command = command;
    }

    /**
     * Message to be returned depending on the command.
     *
     * @return message string
     */
    @Override
    public String getMessage() {
        return String.format("The command `%s` requires an argument after. "
            + "Please try again with an argument after the command.", command);
    }
}
