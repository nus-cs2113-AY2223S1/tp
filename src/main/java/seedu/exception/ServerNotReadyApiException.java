package seedu.exception;

/**
 * Exception for response code 503 API response.
 */
public class ServerNotReadyApiException extends ApiServerResponseErrorException {
    /**
     * Constructor for the exception.
     */
    public ServerNotReadyApiException() {
        super();
    }

    /**
     * Returns a message saying the server is unavailable.
     *
     * @return Message.
     */
    @Override
    public String getMessage() {
        return "The server is currently unavailable: 503 Service Unavailable"
                + "\nTrying again...";
    }
}
