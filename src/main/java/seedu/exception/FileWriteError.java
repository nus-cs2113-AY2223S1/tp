package seedu.exception;
/**
 * Exception for when a file cannot be written to.
 */
public class FileWriteError extends ParkingException {
    String filePath;
    public FileWriteError(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    /** Returns a message prompting the user to check a filepath.
     * @return Formatted string.
     */
    @Override
    public String getMessage(){
        return String.format("The file was not successfully written to. Please check your directory at %s.", filePath);
    }
}