package seedu.exception;

/**
 * Exception for 503 and other unhandled response code.
 */
public class ApiServerResponseErrorException extends ParkingException {
    public ApiServerResponseErrorException() {
        super();
    }

    /**
     * Message to be returned depending on the command.
     *
     * @return message string
     */
    @Override
    public String getMessage() {
        return "";
    }

}
