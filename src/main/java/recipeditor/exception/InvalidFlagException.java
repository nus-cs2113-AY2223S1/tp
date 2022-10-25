package recipeditor.exception;

public class InvalidFlagException extends Exception {

    private static final String errorMessage = "Invalid flag in input";

    public InvalidFlagException() {
        super(errorMessage);
    }
}
