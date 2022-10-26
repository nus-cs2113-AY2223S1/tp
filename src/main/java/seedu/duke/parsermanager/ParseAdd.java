package seedu.duke.parsermanager;

import seedu.duke.exception.EmptyDetailException;
import seedu.duke.exception.IncorrectFlagOrderException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.Messages.EXCEPTION;

//@@author OVReader
/**
 * Parses input for add-type command.
 */
public abstract class ParseAdd extends Parser {
    protected static final int MISSING_FLAG_VALUE = -1;

    protected void checkForEmptyDetails(String commandDetail) throws EmptyDetailException {
        boolean isEmptyDetail = isEmptyString(commandDetail);
        if (isEmptyDetail) {
            throw new EmptyDetailException(EXCEPTION);
        }
    }

    private boolean isEmptyString(String commandDetail) {
        return commandDetail.trim().isEmpty();
    }

    protected boolean checkForFlagPresence(int flagIndexPosition) {
        return (flagIndexPosition != MISSING_FLAG_VALUE);
    }

    protected void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition) throws IncorrectFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new IncorrectFlagOrderException(EXCEPTION);
        }
    }

    protected boolean checkForDetailFormat(String regex, String detail) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(detail);
        return matcher.matches();
    }
}
