package seedu.duke.exception.check.checkproperty;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_CHECK_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;

public class CheckPropertyEmptyDescriptionException {
    @Override
    public String toString() {
        return MESSAGE_EMPTY_DESCRIPTION + MESSAGE_CHECK_PROPERTY_WRONG_FORMAT;
    }

}
