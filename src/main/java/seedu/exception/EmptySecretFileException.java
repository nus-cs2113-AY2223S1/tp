package seedu.exception;

/**
 * Exception for empty secret file.
 */
public class EmptySecretFileException extends ParkingException {
    private String directory;

    /**
     * Constructor for the exception.
     * @param directory String to be used for formatting purposes.
     */
    public EmptySecretFileException(String directory) {
        super();
        this.directory = directory;
    }

    /**
     * Create formatted message when API token file is empty.
     * @return Predefined message with filepath.
     */
    @Override
    public String getMessage() {
        return String.format("API key in secretfile.txt is empty. Please check your directory at %s.", directory);
    }
}
