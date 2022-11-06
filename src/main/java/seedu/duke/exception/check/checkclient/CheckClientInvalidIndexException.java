//@@author FeliciaBeatrice

package seedu.duke.exception.check.checkclient;

import seedu.duke.exception.check.ParseCheckException;

import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;

/**
 * Representation of an exception where the user provides an index that is not within the client list in a check
 * client command.
 */
public class CheckClientInvalidIndexException extends ParseCheckException {
    @Override
    public String toString() {
        return MESSAGE_INVALID_INDEX;
    }
}
