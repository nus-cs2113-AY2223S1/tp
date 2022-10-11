package seedu.exception;

public class UnauthorisedAccessAPIException extends ParkingException {
    public UnauthorisedAccessAPIException() {
        super("");
    }

    public String getMessage() {
        return "Invalid API key";
    }
}
