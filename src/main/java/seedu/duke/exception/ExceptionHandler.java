package seedu.duke.exception;

import seedu.duke.Ui;

public class ExceptionHandler {
    static String errorMessage;

    public static void handleError(Exception e) {
        if (e instanceof NullPointerException) {
            errorMessage = "ID of User/Transaction/Item not found. Null pointer returned";
        }
        Ui.printErrorMessage(errorMessage);
    }
}
