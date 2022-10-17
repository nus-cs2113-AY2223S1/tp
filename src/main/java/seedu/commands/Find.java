package seedu.commands;

import seedu.exception.NoCommandArgumentException;
import seedu.parser.Parser;

/**
 * Represents the 'find' command to search for available lots in carparks.
 */
public class Find {

    /**
     * Checks the 'find' command for errors, returns the carpark ID to search for if no errors.
     *
     * @param input User command.
     * @return Carpark ID.
     */
    public String getCarparkID(String input) throws NoCommandArgumentException {
        String[] words = Parser.splitCommandArgument(input);
        if (words.length == 2 && words[1].trim().length() > 0) {
            return words[1];
        } else {
            throw new NoCommandArgumentException("find");
        }
    }
}
