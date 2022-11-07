//@@author FeliciaBeatrice

package seedu.duke.exception.parsedeleteexception.parsedeleteclientexception;

import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;

/**
 * Represents exception when an invalid index is provided when deleting a client.
 */
public class InvalidDeleteClientIndexException extends ParseDeleteClientException {
    @Override
    public String toString() {
        return MESSAGE_INVALID_INDEX;
    }
}
