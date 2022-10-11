package seedu.duke.exceptions;

public class InvalidUserStorageFileException extends Exception {
    private String message;

    public InvalidUserStorageFileException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
