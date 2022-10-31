package seedu.exception;

import seedu.common.CommonFiles;

/**
 * Exception for when no carpark is found.
 */
public class DuplicateKeyException extends ParkingException {
    private final String filePath;

    /**
     * Constructor for the exception.
     */
    public DuplicateKeyException(String filePath) {
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
