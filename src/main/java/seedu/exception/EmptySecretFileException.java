package seedu.exception;

/**
 * Exception for empty secret file.
 */
public class EmptySecretFileException extends ParkingException {
    public EmptySecretFileException() {
        super("");
    }

    /**
     * return Predefined message.
     * @return Predefined message.
     */
    @Override
    public String getMessage() {
        return "API key in secretfile.txt is empty.";
    }
}
