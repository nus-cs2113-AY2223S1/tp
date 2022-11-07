package recipeditor.exception;

public class ParseException extends Exception {

    private static final String errorMessage = "Error parsing input.";

    public ParseException(String correctFormat) {
        super(errorMessage + '\n' + correctFormat);
    }

    public  ParseException() {
        super(errorMessage);
    }
}
