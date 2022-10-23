package seedu.duke.commands;

import seedu.duke.ModuleList;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

public class Delete extends Command {
    private String modCode;

    /**
     * Constructor to initialize an object of Delete class
     * @param input input entered by user. Format: String
     * @throws InvalidInputFormatException exception which is thrown if the format of the input is wrong
     * @throws InvalidInputContentException exception to be thrown if the input content is empty
     */
    public Delete(String input) throws InvalidInputFormatException, InvalidInputContentException {
        checkFormat(input);
        int[] indexes = positions(input);
        checkContent(input, indexes);
        setModCode(input, indexes);
    }

    /**
     * function to find the module code which needs to be deleted
     * @param input input entered by user. Format: String
     * @param indexes An array of indexes at which details of the module code is present in the input
     */
    private void setModCode(String input, int[] indexes) {
        if (indexes[1] == -1) {
            this.modCode = input.substring(indexes[0]).toUpperCase();
        } else {
            this.modCode = input.substring(indexes[0], indexes[1]).toUpperCase();
        }
    }

    /**
     * check of format of input for delete command is correct or not
     * @param input input entered by user. Format: String
     * @throws InvalidInputFormatException Exception thrown if format of input for delete command is incorrect
     */

    public void checkFormat(String input) throws InvalidInputFormatException {
        boolean isRight;
        isRight = InvalidInputFormatException.checkMod(input);
        checkFormatException(isRight);
    }

    public void checkFormatException(boolean isRight) throws InvalidInputFormatException {
        if (!isRight) {
            throw new InvalidInputFormatException();
        }
    }

    /**
     * function to check if input content is empty or not
     * @param input input entered by user. Format: String
     * @param idx a collection of indexes where the details should be present. If these are empty, an exception should be thrown
     * @throws InvalidInputContentException exception thrown if input content is empty
     */

    public void checkContent(String input, int[] idx) throws InvalidInputContentException {
        boolean isSame;
        isSame = InvalidInputContentException.emptyContent(idx[0], idx[1], input);
        checkContentException(isSame);
    }

    public void checkContentException(boolean isSame) throws InvalidInputContentException {
        if (isSame) {
            throw new InvalidInputContentException();
        }
    }

    /**
     * function to find the indexes of the details given in input command
     * @param input input entered by user. Format: String
     * @return an array of indexes at which the details are present in the input string
     */
    public int[] positions(String input) {
        int[] idx = new int[2];
        idx[0] = input.indexOf("m/") + 2;
        idx[1] = input.indexOf(" ", idx[0]);
        return idx;
    }

    @Override
    public void execute(ModuleList modulelist) {
        modulelist.delete(this.modCode);
    }
}
