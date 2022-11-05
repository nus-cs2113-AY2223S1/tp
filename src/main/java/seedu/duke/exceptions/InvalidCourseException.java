package seedu.duke.exceptions;

public class InvalidCourseException extends Exception {

    public String getMessage() {
        String message = "* Invalid Course input." + "\n";
        return message;
    }

}
