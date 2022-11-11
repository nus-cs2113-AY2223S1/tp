package seedu.duke.commands;

import seedu.duke.ModuleList;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;
import seedu.duke.exceptions.InvalidOverallInputException;
import seedu.duke.exceptions.InvalidSemesterException;

import static seedu.duke.exceptions.InvalidSemesterException.invalidFormat;
import static seedu.duke.exceptions.InvalidSemesterException.invalidSemesterNumber;
import static seedu.duke.exceptions.InvalidSemesterException.invalidYearNumber;

public class Mcs extends Command {
    private String semester;

    /**
     * Constructor to initialize an object of Mcs class.
     * @param input the input message to be used to initialize the variables
     * @throws InvalidInputFormatException exception which is thrown if the format of the input is wrong
     * @throws InvalidInputContentException exception to be thrown if the input content is empty
     */
    public Mcs(String input) throws InvalidInputFormatException, InvalidInputContentException,
            InvalidOverallInputException {
        checkFormat(input);
        int[] indexes = positions(input);
        checkContent(input, indexes);
        setSem(input, indexes);
        checkOverallExceptionForMcs(this.semester);
    }

    /**
     * Function to find the semester from input for which the mcs need to be calculated.
     * @param input input entered by user.
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
     * Function to check if the format of input is correct or not.
     * @param input input entered by user.
     * @throws InvalidInputFormatException exception thrown if format of input is incorrect
     */
    public void checkFormat(String input) throws InvalidInputFormatException {
        boolean isRight;
        isRight = InvalidInputFormatException.checkSem(input);
        checkFormatException(isRight);
    }

    /**
     * Function to check format of input.
     * @param isRight whether it is in correct format.
     * @throws InvalidInputFormatException exception thrown if content of input has issues
     */
    public void checkFormatException(boolean isRight) throws InvalidInputFormatException {
        if (!isRight) {
            throw new InvalidInputFormatException();
        }
    }

    /**
     * Function to check if content entered by user is empty or not.
     * @param input input entered by user.
     * @param idx a collection of indexes where the details should be present.
     *            If these are empty, an exception should be thrown
     * @throws InvalidInputContentException exception thrown if content of input is empty
     */
    public void checkContent(String input, int[] idx) throws InvalidInputContentException {
        boolean isSame;
        isSame = InvalidInputContentException.emptyContent(idx[0], idx[1], input);
        checkContentException(isSame);
    }

    /**
     * Function to check content of input.
     * @param isSame whether it is same or not.
     * @throws InvalidInputContentException exception thrown if content has issues
     */
    public void checkContentException(boolean isSame) throws InvalidInputContentException {
        if (isSame) {
            throw new InvalidInputContentException();
        }
    }

    /**
     * Function to return the positions of the details in input.
     * @param input the input given by user.
     * @return an integer array containing the positions of the details given by user
     */
    public int[] positions(String input) {
        int[] idx = new int[2];
        idx[0] = input.indexOf("s/") + 2;
        idx[1] = input.indexOf(" ", idx[0]);
        return idx;
    }

    /**
     * Method to check for any exception caught due to input (semester) format issues.
     * @param semester Semester taken.
     * @throws InvalidOverallInputException exception to be thrown if any issues with any of the input
     */
    private void checkOverallExceptionForMcs(String semester) throws InvalidOverallInputException {
        String errorMessage = "";

        try {
            checkYear(semester);
        } catch (Exception e) {
            errorMessage += e.getMessage();
        }

        if (!errorMessage.equals("")) {
            System.out.println("Unable to view MCS due to these issue(s):");
            System.out.println(errorMessage);
            throw new InvalidOverallInputException();
        }
    }

    /**
     * Function to check if semester input is in correct format.
     * @param semester semester in string format.
     * @throws InvalidSemesterException exception thrown when semester input is in incorrect format
     */
    public void checkYear(String semester) throws InvalidSemesterException {
        if (invalidFormat(semester)) {
            throw new InvalidSemesterException();
        }
        if (invalidYearNumber(semester) || invalidSemesterNumber(semester)) {
            throw new InvalidSemesterException();
        }
    }

    @Override
    public void execute(ModuleList modulelist) {
        modulelist.mc(this.semester);
    }
}
