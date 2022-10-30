package seedu.duke.exception.storagexception;

import static seedu.duke.Messages.INVALID_CLIENT_ENTRIES;

public class InvalidClientTextException extends StorageException {

    @Override
    public String toString() {
        return INVALID_CLIENT_ENTRIES;
    }
}
