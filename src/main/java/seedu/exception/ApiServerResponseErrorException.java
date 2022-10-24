package seedu.exception;

public class ApiServerResponseErrorException extends ParkingException {
    private int tryNumber;

    public ApiServerResponseErrorException() {
        super();
    }

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
