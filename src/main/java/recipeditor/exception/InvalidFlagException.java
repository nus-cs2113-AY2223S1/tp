package recipeditor.exception;

public class InvalidFlagException extends Exception {

    private static final String errorMessage = "Invalid flag in input. Type '/help edit' to see the syntax.";

    public InvalidFlagException() {
        super(errorMessage);
    }
}
