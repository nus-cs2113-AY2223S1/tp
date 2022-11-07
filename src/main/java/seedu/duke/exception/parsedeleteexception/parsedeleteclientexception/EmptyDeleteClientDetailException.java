//@@author FeliciaBeatrice

package seedu.duke.exception.parsedeleteexception.parsedeleteclientexception;

import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;

/**
 * Represents exception when an empty description is provided for delete client.
 */
public class EmptyDeleteClientDetailException extends ParseDeleteClientException {
    @Override
    public String toString() {
        return MESSAGE_EMPTY_DESCRIPTION;
    }
}
