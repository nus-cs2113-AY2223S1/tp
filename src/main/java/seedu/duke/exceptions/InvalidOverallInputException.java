package seedu.duke.exceptions;

public class InvalidOverallInputException extends Exception {

    public String getMessage() {
        String message = "Please try to fix the issue(s) and Try again!";
        return message;
    }
}
