package seedu.exception;

abstract public class ParkingException extends Exception {
    public ParkingException(String message) {
        super(message);
    }
}
