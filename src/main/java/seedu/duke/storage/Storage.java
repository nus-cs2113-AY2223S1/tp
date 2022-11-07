package seedu.duke.storage;

//@@author bdthanh

import seedu.duke.exception.InvalidUserException;

/**
 * An abstract class for Storage.
 */
public abstract class Storage {
    /**
     * Checks if args from files are empty or not.
     *
     * @param splitLine      The array of args splitting from the line of entry
     * @param numOfValidArgs The correct number of args
     * @param errorNumber    The message when lack of args
     * @param errorValue     The message when arg is empty
     * @throws InvalidUserException When args are empty
     */
    public void checkIfArgsEmpty(String[] splitLine, int numOfValidArgs, String errorNumber, String errorValue)
            throws InvalidUserException {
        if (splitLine.length != numOfValidArgs) {
            throw new InvalidUserException(errorNumber);
        }
        for (String arg : splitLine) {
            if (arg.trim().equals("")) {
                throw new InvalidUserException(errorValue);
            }
        }
    }

    /**
     * Trims the string after splitting.
     *
     * @param arrayToTrim The array of args splitting from the line of entry
     */
    public void trimArrayValues(String[] arrayToTrim) {
        for (int i = 0; i < arrayToTrim.length; i++) {
            arrayToTrim[i] = arrayToTrim[i].trim();
        }
    }
}
