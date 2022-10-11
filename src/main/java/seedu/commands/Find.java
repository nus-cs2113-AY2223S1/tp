package seedu.commands;

import seedu.exception.InvalidFindCommandException;

/**
 * Represents the 'find' command to search for available lots in carparks.
 */
public class Find {

    /**
     * Checks the 'find' command for errors, returns the carpark ID to search for if no errors.
     *
     * @param input User command.
     * @return Carpark ID.
     * @throws InvalidFindCommandException If invalid command syntax used.
     */
    public String getCarparkID(String input) throws InvalidFindCommandException {
        String[] words = input.split("\\s+", 2);
        if (words.length == 2 && words[1].trim().length() > 0) {
            return words[1];
        } else {
            throw new InvalidFindCommandException("Usage: find <carpark ID>");
        }
    }
}