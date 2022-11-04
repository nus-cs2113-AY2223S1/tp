package seedu.duke.exceptions;

public class InvalidOverallInputException extends Exception {

    public String getMessage() {
        String message = "Please try to fix the issues and Try again!" + "\n"  ;
        return message;
    }
}
