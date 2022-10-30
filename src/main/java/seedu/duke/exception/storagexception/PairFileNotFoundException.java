package seedu.duke.exception.storagexception;

import static seedu.duke.Messages.MESSAGE_NO_PAIRING_FILE;

public class PairFileNotFoundException extends StorageException {
    @Override
    public String toString() {
        return MESSAGE_NO_PAIRING_FILE;
    }
}
