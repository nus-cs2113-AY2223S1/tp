package seedu.duke.exception.storageexception;

import static seedu.duke.Messages.INVALID_CLIENT_FORMATTING;

public class InvalidClientFormatException extends StorageException {

    @Override
    public String toString() {
        return INVALID_CLIENT_FORMATTING;
    }


}
