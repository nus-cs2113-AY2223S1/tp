package seedu.exception;

/**
 * Exception subclass of {@link ParkingException} for if a given string has arguments after the command
 * that should not be present.
 */
public class UnneededArgumentsException extends ParkingException {
    private final String command;

    /**
     * Constructor for exception
     *
     * @param command command string
     */
    public UnneededArgumentsException(String command) {
        super();
        this.command = command;
    }

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getMessage() {
        return String.format("There were unrecognized arguments after "
                + "the <%1$s> command. Please try the <%1$s> command"
                + " again by itself.", command);
    }
}
