package seedu.duke.exception.check;


import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;

public class CheckNotIntegerException extends ParseCheckException {

    public CheckNotIntegerException() {
    }

    @Override
    public String toString() {
        return MESSAGE_NOT_INTEGER;
    }

}
