//@@author FeliciaBeatrice

package seedu.duke.exception.check.checkclient;

import seedu.duke.exception.check.ParseCheckException;

import static seedu.duke.CommandStructure.CHECK_CLIENT_FLAGS;
import static seedu.duke.Messages.MESSAGE_CHECK_CLIENT_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_MISSING_FLAG;

/**
 * Representation of an exception where the user omits the flag for the check client command.
 */
public class CheckClientMissingFlagException extends ParseCheckException {
    private static final int FIRST_FLAG_INDEX = 0;
    private String missingFlag = CHECK_CLIENT_FLAGS[FIRST_FLAG_INDEX];

    public CheckClientMissingFlagException() {
    }

    @Override
    public String toString() {
        return MESSAGE_MISSING_FLAG
                + missingFlag + System.lineSeparator()
                + MESSAGE_CHECK_CLIENT_WRONG_FORMAT;
    }
}
