package seedu.duke.exception.parsefindexception;

import static seedu.duke.Messages.INCORRECT_NUMBER_OF_FLAG;

public class FindIncorrectNumOfTagException extends ParseFindException {
    @Override
    public String toString() {
        return INCORRECT_NUMBER_OF_FLAG;
    }
}
