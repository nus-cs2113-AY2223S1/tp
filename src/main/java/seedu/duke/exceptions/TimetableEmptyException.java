package seedu.duke.exceptions;

public class TimetableEmptyException extends Exception {
    private static final String ERROR_MESSAGE = "Your timetable is empty. Please select your modules first before viewing.";

    @Override
    public String getMessage() {
        return ERROR_MESSAGE;
    }
}
