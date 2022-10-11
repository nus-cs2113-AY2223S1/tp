package seedu.exception;

public class UnauthorisedAccessApiException extends ParkingException {
    public UnauthorisedAccessApiException() {
        super("");
    }

    public String getMessage() {
        return "Invalid API key";
    }
}
