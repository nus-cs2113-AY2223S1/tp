package seedu.duke.exception;

//@@author wcwy

/**
 * Represents the base class of the exceptions defined for Moolah Manager.
 */
public abstract class MoolahException extends Exception {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    public abstract String getMessage();
}
