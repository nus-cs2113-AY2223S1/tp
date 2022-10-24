package seedu.exception;

/**
 * Exception for 503 and other unhandled response code.
 */
public class ApiServerResponseErrorException extends ParkingException {
    private int tryNumber;

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

    public void setTryNumber(int number) {
        tryNumber = number;
    }

    public int getTryNumber() {
        return tryNumber;
    }
}
