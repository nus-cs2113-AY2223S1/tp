//@@author OVReader

package seedu.duke.parsermanager.add;

import seedu.duke.parsermanager.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for add-type command.
 */
public abstract class CommandAddParser extends Parser {
    protected static final int MISSING_FLAG_VALUE = -1;

    protected boolean checkForFlagPresence(int flagIndexPosition) {
        return (flagIndexPosition != MISSING_FLAG_VALUE);
    }

    protected boolean checkForDetailFormat(String regex, String detail) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(detail);
        return matcher.matches();
    }
}
