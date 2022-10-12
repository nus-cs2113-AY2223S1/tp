package seedu.duke.exceptions;

public class TimetableEmptyException extends Exception {
    @Override
    public String getMessage() {
        return "Your timetable is empty. Please select lessons first before viewing.";
    }
}
