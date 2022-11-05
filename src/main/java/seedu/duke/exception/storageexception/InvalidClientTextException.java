package seedu.duke.exception.storageexception;

import static seedu.duke.Messages.INVALID_CLIENT_ENTRIES;

public class InvalidClientTextException extends StorageException {

    @Override
    public String toString() {
        return INVALID_CLIENT_ENTRIES;
    }
}
