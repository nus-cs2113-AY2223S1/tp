package computercomponentchooser.exceptions;

/**
 * An exception that occurs when the parse method in Parser class takes in a String as command that is
 * not part of any acceptable command.
 */
public class UnknownCommandException extends Exception {
    protected final String errorMessage = "Please input a valid command";

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return errorMessage;
    }
}
