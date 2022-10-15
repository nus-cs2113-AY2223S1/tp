package seedu.exception;

/**
 * Exception for response code 402 (unauthorized) API response.
 */
public class UnauthorisedAccessApiException extends ParkingException {
    public UnauthorisedAccessApiException() {
        super();
    }

    public String getMessage() {
        return "Invalid API key. Use command `auth <api_key>` to re-authenticate.\n";
    }
}
