package seedu.duke.exceptions;

public class InvalidSemesterException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid semester selected!";
    }
}
