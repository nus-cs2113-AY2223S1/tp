package seedu.duke.exception.check;


import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER_INSTANCES;

/**
 * Representation of an exception where a non-positive integer is provided in a check command.
 */
public class CheckNotIntegerException extends ParseCheckException {


    public CheckNotIntegerException() {
    }

    @Override
    public String toString() {
        return MESSAGE_NOT_INTEGER;
    }

}
