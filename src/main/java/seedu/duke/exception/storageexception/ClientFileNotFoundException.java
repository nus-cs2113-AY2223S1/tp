package seedu.duke.exception.storageexception;

import static seedu.duke.Messages.MESSAGE_NO_CLIENT_FILE;

public class ClientFileNotFoundException extends StorageException {
    @Override
    public String toString() {
        return MESSAGE_NO_CLIENT_FILE;
    }
}
