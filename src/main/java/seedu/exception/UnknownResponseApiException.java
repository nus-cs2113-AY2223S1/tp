package seedu.exception;

/**
 * Exception for unknown response code from API response.
 */
public class UnknownResponseApiException extends ParkingException {

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
     * Message to be returned depending on the command.
     *
     * @return message string
     */
    @Override
    public String getMessage() {
        return "Response Code: " + responseCode
            + "\nIf the problem persists please contact the developer. Trying again...";
    }

}
