package seedu.duke.exception.check;


import static seedu.duke.Messages.MESSAGE_CHECK_CLIENT_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_CHECK_EXTRA_ARGUMENTS;
import static seedu.duke.Messages.MESSAGE_CHECK_PROPERTY_WRONG_FORMAT;

/**
 * Representation of an exception where the user keys in extra arguments for a check command.
 */
public class CommandCheckExtraParametersException extends ParseCheckException {

    private String extraArgument;

    public CommandCheckExtraParametersException(String extraArgument) {
        this.extraArgument = extraArgument;
    }

    @Override
    public String toString() {
        return MESSAGE_CHECK_EXTRA_ARGUMENTS + extraArgument + System.lineSeparator()
                + MESSAGE_CHECK_PROPERTY_WRONG_FORMAT
                + MESSAGE_CHECK_CLIENT_WRONG_FORMAT;
    }
}
