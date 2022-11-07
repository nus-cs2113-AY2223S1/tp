package seedu.exception;

/**
 * Exception for when a carpark ID is already in a list.
 */
public class DuplicateCarparkIdException extends ParkingException {
    private final String filePath;

    /**
     * Constructor for the exception.
     *
     * @param filePath path to file.
     */
    public DuplicateCarparkIdException(String filePath) {
        super();
        this.filePath = filePath;
    }

    /**
     * Returns a message saying no carpark was found.
     *
     * @return Formatted string.
     */
    @Override
    public String getMessage() {
        return "There was a duplicate Carpark ID found, resulting in an invalid file at " + filePath + ".\n"
            + "Regenerating file...";
    }
}
