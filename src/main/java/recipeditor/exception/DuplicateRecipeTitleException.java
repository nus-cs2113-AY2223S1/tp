package recipeditor.exception;

public class DuplicateRecipeTitleException extends Exception {
    public static final String DUPLICATE_IN_MODEL = "This Recipe Title already existed!";

    public DuplicateRecipeTitleException(String message) {
        super(message);
    }
}
