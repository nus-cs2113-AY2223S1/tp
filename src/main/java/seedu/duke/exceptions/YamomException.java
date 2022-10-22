package seedu.duke.exceptions;

public class YamomException extends Exception {
    private String errorMessage;

    public YamomException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return "Error! \t" + errorMessage;
    }
}
