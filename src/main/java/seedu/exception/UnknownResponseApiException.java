package seedu.exception;

/**
 * Exception for unknown response code from API response.
 */
public class UnknownResponseApiException extends ApiServerResponseErrorException {
    private final int responseCode;
    /**
     * Constructor for exception.
     *
     * @param responseCode Response code to be used in formatted output string.
     */
    public UnknownResponseApiException(int responseCode) {
        super();
        this.responseCode = responseCode;
    }

    /**
     * Returns a message depending on the command.
     *
     * @return Message.
     */
    @Override
    public String getMessage() {
        return "Response Code: " + responseCode
            + "\nIf the problem persists please contact the developer. Trying again...";
    }
}
