package seedu.exception;

/**
 * Exception for response code 503 API response.
 */
public class ServerNotReadyApiException extends ParkingException {
    public ServerNotReadyApiException() {
        super();
    }

    /**
     * Message to be returned depending on the command.
     *
     * @return message string
     */
    @Override
    public String getMessage() {
        return "The server is currently unavailable: 503 Service Unavailable";
    }
}
