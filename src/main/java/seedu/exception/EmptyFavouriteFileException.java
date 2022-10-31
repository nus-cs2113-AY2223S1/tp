package seedu.exception;

/**
 * Exception for empty secret file.
 */
public class EmptyFavouriteFileException extends ParkingException {

    /**
     * Constructor for the exception.
     */
    public EmptyFavouriteFileException() {
        super();
    }

    /**
     * Create formatted message when API token file is empty.
     *
     * @return Predefined message with filepath.
     */
    @Override
    public String getMessage() {
        return "No favourite carparks yet! Try adding some with the 'favourite' command.";
    }
}
