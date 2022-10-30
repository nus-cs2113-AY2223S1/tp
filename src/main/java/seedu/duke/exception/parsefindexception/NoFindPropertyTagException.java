package seedu.duke.exception.parsefindexception;

import static seedu.duke.Messages.MESSAGE_NO_FIND_PROPERTY_TAG;

public class NoFindPropertyTagException extends ParseFindException {
    @Override
    public String toString() {
        return MESSAGE_NO_FIND_PROPERTY_TAG;
    }
}
