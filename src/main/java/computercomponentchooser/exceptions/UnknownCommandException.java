package computercomponentchooser.exceptions;

/**
 * An exception that occurs when the parse method in Parser class takes in a String as command that is
 * not part of any acceptable command.
 */

public class UnknownCommandException extends Exception{
    protected final String error_message = "Please input a valid command";
    public String getMessage() {
        return error_message;
    }
}
