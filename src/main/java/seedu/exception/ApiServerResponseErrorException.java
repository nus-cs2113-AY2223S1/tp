package seedu.exception;

/**
 * Exception for 503 and other unhandled response code.
 */
public class ApiServerResponseErrorException extends ParkingException {
    /**
     * Constructor for the exception.
     */
    public ApiServerResponseErrorException() {
        super();
    }

    /**
     * Returns a message.
     *
     * @return Message.
     */
    @Override
    public String getMessage() {
        return "";
    }

}
