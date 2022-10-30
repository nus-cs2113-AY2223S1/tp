package seedu.duke.exception.parsefindexception;

import static seedu.duke.Messages.EMPTY_FIND_DESCRIPTION;

public class FindEmptyDescriptionException extends ParseFindException {
    @Override
    public String toString() {
        return EMPTY_FIND_DESCRIPTION;
    }
}
