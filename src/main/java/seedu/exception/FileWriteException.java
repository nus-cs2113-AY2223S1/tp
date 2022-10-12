package seedu.exception;

/**
 * Exception for when a file cannot be written to.
 */
public class FileWriteException extends ParkingException {
    private String filePath;

    /**
     * Constructor for the exception.
     */
    public FileWriteException(String filePath) {
        super();
        this.filePath = filePath;
    }

    /**
     * Returns a message prompting the user to check a filepath.
     * @return Formatted string.
     */
    @Override
    public String getMessage() {
        return String.format("The file was not successfully written to. Please check your directory at %s.", filePath);
    }
}
