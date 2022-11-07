package seedu.exception;

/**
 * Exception for response code 402 (unauthorized) API response.
 */
public class UnauthorisedAccessApiException extends ParkingException {
    /**
     * Constructor for the exception.
     */
    public UnauthorisedAccessApiException() {
        super();
    }

    /**
     * Returns a message saying API key is invalid.
     *
     * @return Message.
     */
    public String getMessage() {
        return "Invalid API key. Use command `auth <api_key>` to re-authenticate.";
    }
}
