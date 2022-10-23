package seedu.duke.commands;

import seedu.duke.ModuleList;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

public class Mcs extends Command {
    private String semester;

    /**
     * Constructor to initialize an object of Mcs class
     * @param input the input message to be used to initialize the variables
     * @throws InvalidInputFormatException exception which is thrown if the format of the input is wrong
     * @throws InvalidInputContentException exception to be thrown if the input content is empty
     */
    public Mcs(String input) throws InvalidInputFormatException, InvalidInputContentException {
        checkFormat(input);
        int[] indexes = positions(input);
        checkContent(input, indexes);
        setSem(input, indexes);
    }

    /**
     * function to find the semester from input for which the mcs need to be calculated
     * @param input input entered by user. Format: String
     * @param indexes An array of indexes which specify the positions at which details are present in the input
     */
    private void setSem(String input, int[] indexes) {
        if (indexes[1] == -1) {
            this.semester = input.substring(indexes[0]).toUpperCase();
        } else {
            this.semester = input.substring(indexes[0], indexes[1]).toUpperCase();
        }
    }
    /**
     * Function to check if the format of input is correct or not
     * @param input input entered by user. Format: String
     * @throws InvalidInputFormatException exception thrown if format of input is incorrect
     */
    public void checkFormat(String input) throws InvalidInputFormatException {
        boolean isRight;
        isRight = InvalidInputFormatException.checkSem(input);
        checkFormatException(isRight);
    }

    public void checkFormatException(boolean isRight) throws InvalidInputFormatException {
        if (!isRight) {
            throw new InvalidInputFormatException();
        }
    }
    /**
     * Function to check if content entered by user is empty or not
     * @param input input entered by user. Format: String
     * @param idx a collection of indexes where the details should be present. If these are empty, an exception should be thrown
     * @throws InvalidInputContentException exception thrown if content of input is empty
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
     * function to return the positions of the details in input
     * @param input the input given by user. Format: String
     * @return an integer array containing the positions of the details given by user
     */
    public int[] positions(String input) {
        int[] idx = new int[2];
        idx[0] = input.indexOf("s/") + 2;
        idx[1] = input.indexOf(" ", idx[0]);
        return idx;
    }

    @Override
    public void execute(ModuleList modulelist) {
        modulelist.mc(this.semester);
    }
}
