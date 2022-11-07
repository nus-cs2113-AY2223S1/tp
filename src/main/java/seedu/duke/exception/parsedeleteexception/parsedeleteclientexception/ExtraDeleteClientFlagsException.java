//@@author FeliciaBeatrice

package seedu.duke.exception.parsedeleteexception.parsedeleteclientexception;

import static seedu.duke.Messages.MESSAGE_DELETE_CLIENT_WRONG_FORMAT;

/**
 * Represents exception when extra flags are provided for delete client.
 */
public class ExtraDeleteClientFlagsException extends ParseDeleteClientException {
    @Override
    public String toString() {
        return MESSAGE_DELETE_CLIENT_WRONG_FORMAT;
    }
}
