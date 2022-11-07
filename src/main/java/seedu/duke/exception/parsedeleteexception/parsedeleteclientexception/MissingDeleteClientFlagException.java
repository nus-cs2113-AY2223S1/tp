//@@author FeliciaBeatrice

package seedu.duke.exception.parsedeleteexception.parsedeleteclientexception;

import static seedu.duke.Messages.MESSAGE_DELETE_CLIENT_WRONG_FORMAT;

/**
 * Represents exception when delete client flag is missing.
 */
public class MissingDeleteClientFlagException extends ParseDeleteClientException {
    @Override
    public String toString() {
        return MESSAGE_DELETE_CLIENT_WRONG_FORMAT;
    }
}
