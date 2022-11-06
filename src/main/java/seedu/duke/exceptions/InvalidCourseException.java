package seedu.duke.exceptions;

public class InvalidCourseException extends Exception {

    public String getMessage() {
        return "* Invalid Course input." + "\n";
    }

}
