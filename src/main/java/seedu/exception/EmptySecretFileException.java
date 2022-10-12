package seedu.exception;




/**
 * Exception for empty secret file.
 */
public class EmptySecretFileException extends ParkingException {
    public EmptySecretFileException() {
        super("");
    }

    /**
     * return predefined message.
     * @return predefined message.
     */
    @Override
    public String getMessage() {
        return "API key is not entered. Load it first!";
    }
}
