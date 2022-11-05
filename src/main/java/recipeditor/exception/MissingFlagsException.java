package recipeditor.exception;

public class MissingFlagsException extends Exception {

    private static final String message = "Miss flags from arguments.";

    public MissingFlagsException() {
        super(message);
    }

    public MissingFlagsException(String flagType) {
        super("Missing " + flagType + " flag from arguments.");
    }
}
