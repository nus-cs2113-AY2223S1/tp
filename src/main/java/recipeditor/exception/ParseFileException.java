package recipeditor.exception;

public class ParseFileException extends Exception {
    public static final String DUPLICATE_IN_MODEL = "This Recipe Title already existed!";
    public ParseFileException(String message) {
        super(message);
    }
}
