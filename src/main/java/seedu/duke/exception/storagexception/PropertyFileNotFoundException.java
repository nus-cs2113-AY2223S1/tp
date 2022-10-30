package seedu.duke.exception.storagexception;

import static seedu.duke.Messages.MESSAGE_NO_PROPERTY_FILE;

public class PropertyFileNotFoundException extends StorageException {
    @Override
    public String toString() {
        return MESSAGE_NO_PROPERTY_FILE;
    }
}
