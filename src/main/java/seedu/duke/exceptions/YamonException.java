package seedu.duke.exceptions;

public class YamonException extends Exception {
    private String errorMessage;

    public YamonException (String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage () {
        return "Error! \t" + errorMessage;
    }
}
