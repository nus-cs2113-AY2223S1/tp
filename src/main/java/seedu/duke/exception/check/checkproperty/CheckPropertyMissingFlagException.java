package seedu.duke.exception.check.checkproperty;

//@@author ngdeqi
import seedu.duke.exception.check.ParseCheckException;

import static seedu.duke.CommandStructure.INDEX_FLAGS;
import static seedu.duke.Messages.MESSAGE_CHECK_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_MISSING_FLAG;

/**
 * Representation of an exception where the user omits the property flag for the check property command.
 */
public class CheckPropertyMissingFlagException extends ParseCheckException {

    private static int FIRST_FLAG_INDEX = 0;
    private String missingFlag;


    public CheckPropertyMissingFlagException() {
        this.missingFlag = INDEX_FLAGS[FIRST_FLAG_INDEX];
    }

    @Override
    public String toString() {
        return MESSAGE_MISSING_FLAG
                + missingFlag + System.lineSeparator()
                + MESSAGE_CHECK_PROPERTY_WRONG_FORMAT;
    }


}
