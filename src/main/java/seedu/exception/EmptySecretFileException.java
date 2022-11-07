package seedu.exception;

/**
 * Exception for empty secret file.
 */
public class EmptySecretFileException extends ParkingException {
    private final String directory;

    /**
     * Constructor for the exception.
     *
     * @param directory path to directory of file.
     */
    public EmptySecretFileException(String directory) {
        super();
        this.directory = directory;
    }

    /**
     * Returns a message saying API token file is empty.
     *
     * @return Message.
     */
    @Override
    public String getMessage() {
        return String.format("API key in secret.txt is empty. Please check your directory at %s. "
                + "Using default API key for now.", directory);
    }
}
