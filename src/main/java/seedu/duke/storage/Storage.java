package seedu.duke.storage;

//@@author bdthanh

import seedu.duke.exception.InvalidUserException;

/**
 * An abstract class for Storage.
 */
public abstract class Storage {
    public void checkIfArgsEmpty(String[] splitLine, int numOfValidArgs, String errorNumber, String errorValue)
            throws InvalidUserException {
        if (splitLine.length != numOfValidArgs) {
            throw new InvalidUserException(errorNumber);
        }
        for (String arg: splitLine) {
            if (arg.trim().equals("")) {
                throw new InvalidUserException(errorValue);
            }
        }
    }

    public void trimArrayValues(String[] arrayToTrim) {
        for (int i = 0; i < arrayToTrim.length; i++) {
            arrayToTrim[i] = arrayToTrim[i].trim();
        }
    }
}
