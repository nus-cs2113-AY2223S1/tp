package seedu.duke.exception.check;


import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;

/**
 * Representation of an exception where a non-positive integer is provided in a check command.
 */
public class CommandCheckNotIntegerException extends ParseCheckException {


    public CommandCheckNotIntegerException() {
    }

    @Override
    public String toString() {
        return MESSAGE_NOT_INTEGER;
    }

}
