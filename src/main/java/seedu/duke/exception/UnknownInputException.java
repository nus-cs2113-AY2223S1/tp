package seedu.duke.exception;

import seedu.duke.Ui;

public class UnknownInputException extends MoolahException {

    // HELP ME CHANGE PLS

    @Override
    public String getMessage() {
        Ui.showInvalidCommand();
        return "OOPS!!! I'm sorry, but I don't know what that means :-( ";
    }

}
