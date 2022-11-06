package seedu.duke.commands;

import seedu.duke.Module;
import seedu.duke.ModuleList;
import seedu.duke.exceptions.InvalidCourseException;
import seedu.duke.exceptions.InvalidGradeException;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;
import seedu.duke.exceptions.InvalidMcException;
import seedu.duke.exceptions.InvalidOverallInputException;
import seedu.duke.exceptions.InvalidSemesterException;

import static seedu.duke.exceptions.InvalidGradeException.checkGradeFormat;
import static seedu.duke.exceptions.InvalidGradeException.checkValidGrade;
import static seedu.duke.exceptions.InvalidMcException.invalidMc;
import static seedu.duke.exceptions.InvalidSemesterException.invalidFormat;
import static seedu.duke.exceptions.InvalidSemesterException.invalidSemesterNumber;
import static seedu.duke.exceptions.InvalidSemesterException.invalidYearNumber;

public class Add extends Command {
    private Module mod;

    /**
     * Contructor of Add class to initialize an object of class Add.
     * @param input the input message to be used to initialize the variables
     * @throws InvalidInputFormatException exception which is thrown if the format of the input is wrong
     * @throws InvalidInputContentException exception to be thrown if the input content is empty
     * @throws InvalidOverallInputException exception to be thrown if any issues with the input
     */
    public Add(String input) throws InvalidInputFormatException, InvalidInputContentException, InvalidMcException, InvalidGradeException, InvalidSemesterException, InvalidOverallInputException {
        checkFormat(input);
        int[] indexes = positions(input);
        checkContent(input, indexes);
        addition(input, indexes);
    }

    /**
     * Function to extract details from input text and create a new module with the details extracted.
     * Extracted details include course, semester, mc, and grade.
     * @param input the input entered by the user
     * @param indexes an array containing the positions from which the details need to be extracted
     */
    private void addition(String input, int[] indexes) throws InvalidOverallInputException {
        input = input.toUpperCase();
        String course = extractingContent(input, indexes[0], indexes[1]);
        String semester = extractingContent(input, indexes[2], indexes[3]);
        String mcString = extractingContent(input, indexes[4], indexes[5]);
        String grade = extractingContent(input, indexes[6], indexes[7]);

        checkOverallExceptionForAdd(course, semester, mcString, grade);

        int mcInt = Integer.parseInt(mcString);
        this.mod = new Module(course, semester, grade, mcInt);

    }

    /**
     * Method to check for any exception caught due to input (Course, semester, MC, Grade) issues
     * @param course Course taken. Format: String
     * @param semester Semester taken. Format: String
     * @param mcString MC in string format. Format: String
     * @param grade Grade received for the module. Format: String
     * @throws InvalidOverallInputException exception to be thrown if any issues with any of the input
     */
    private void checkOverallExceptionForAdd(String course, String semester,
                                       String mcString, String grade) throws InvalidOverallInputException {

        String errorMessage = "";

        try {
            checkCourse(course);
        } catch (Exception e) {
            errorMessage += e.getMessage();
        }

        try {
            checkYear(semester);
        } catch (Exception e) {
            errorMessage += e.getMessage();
        }

        try {
            checkMcString(mcString);
            int mcInt = Integer.parseInt(mcString);
            checkMc(mcInt);
        } catch (Exception e) {
            errorMessage += e.getMessage();
        }

        try {
            checkGrade(grade);
        } catch (Exception e) {
            errorMessage += e.getMessage();
        }

        if (!errorMessage.equals("")) {
            System.out.println("Unable to ADD module due to these issue(s):");
            System.out.println(errorMessage);
            throw new InvalidOverallInputException();
        }
    }

    /**
     * Function to extract content from startIndex to endIndex out of input
     * @param input The input from which the content needs to be extracted. Format: String
     * @param startIndex The starting index. Format: int
     * @param endIndex The ending index. Format: int
     * @return a string which is a substring (extracted) of input.
     */
    private String extractingContent(String input, int startIndex, int endIndex) {
        if (endIndex == -1) {
            return input.substring(startIndex);
        } else {
            return input.substring(startIndex, endIndex);
        }
    }

    /**
     * Function to check if the format of input is correct or not
     * @param input input entered by user. Format: String
     * @throws InvalidInputFormatException exception thrown if format of input is incorrect
     */

    public void checkFormat(String input) throws InvalidInputFormatException {
        boolean isRight;
        isRight = InvalidInputFormatException.checkMod(input);
        checkFormatException(isRight);
        isRight = InvalidInputFormatException.checkSem(input);
        checkFormatException(isRight);
        isRight = InvalidInputFormatException.checkMC(input);
        checkFormatException(isRight);
        isRight = InvalidInputFormatException.checkGrade(input);
        checkFormatException(isRight);
    }

    /**
     * Function to check format of input
     * @param isRight whether it is in correct format. Format: boolean
     * @throws InvalidInputFormatException exception thrown if content of input has issues
     */
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
        isSame = InvalidInputContentException.emptyContent(idx[2], idx[3], input);
        checkContentException(isSame);
        isSame = InvalidInputContentException.emptyContent(idx[4], idx[5], input);
        checkContentException(isSame);
        isSame = InvalidInputContentException.emptyContent(idx[6], idx[7], input);
        checkContentException(isSame);
    }

    /**
     * Function to check content of input
     * @param isSame whether it is same or not. Format: boolean
     * @throws InvalidInputContentException exception thrown if content has issues
     */
    public void checkContentException(boolean isSame) throws InvalidInputContentException {
        if (isSame) {
            throw new InvalidInputContentException();
        }
    }

    /**
     * Function to check if course input entered by user is correct format
     * Course input must be below 10 characters and have both letters and numbers
     * @param course input entered by user. Format: String
     * @throws InvalidCourseException exception thrown when course input is invalid
     */
    public static void checkCourse(String course) throws InvalidCourseException {
        if (course.length() > 10) {
            throw new InvalidCourseException();
        } else if (course.matches("[a-zA-Z]+")) {
            throw new InvalidCourseException();
        } else if (course.matches("[0-9]+")) {
            throw new InvalidCourseException();
        }
    }

    /**
     * Function to check if mc String input is in correct format
     * @param mcString MC in string format. Format: String
     * @throws InvalidMcException exception thrown when mc String input is in incorrect format
     */
    public void checkMcString(String mcString) throws InvalidMcException {
        if (mcString.length() > 2) {
            throw new InvalidMcException();
        }
        if (!mcString.matches("[0-9]+")) {
            throw new InvalidMcException();
        }
    }


    /**
     * Function to check if semester input is in correct format
     * @param semester semester in string format. Format: String
     * @throws InvalidSemesterException exception thrown when semester input is in incorrect format
     */
    public void checkYear(String semester) throws InvalidSemesterException {
        if(invalidFormat(semester)) {
            throw new InvalidSemesterException();
        }
        if (invalidYearNumber(semester) || invalidSemesterNumber(semester)) {
            throw new InvalidSemesterException();
        }
    }

    /**
     * Function to check if mc input is in correct format
     * @param mcs mcs in Integer format. Format: Integer
     * @throws InvalidMcException exception thrown when mc input is in incorrect format
     */
    public void checkMc(int mcs) throws InvalidMcException {
        if (invalidMc(mcs)) {
            throw new InvalidMcException();
        }
    }

    /**
     * Function to check if grade input is in correct format
     * @param grade grade in string format. Format: String
     * @throws InvalidGradeException exception thrown when grade input is in incorrect format
     */
    public void checkGrade(String grade) throws InvalidGradeException {
        if (!checkGradeFormat(grade)) {
            throw new InvalidGradeException();
        }
        if (!checkValidGrade(grade)) {
            throw new InvalidGradeException();
        }
    }

    /**
     * function to return the positions of the details in input
     * @param input the input given by user. Format: String
     * @return an integer array containing the positions of the details given by user
     */
    public int[] positions(String input) {
        int[] idx = new int[8];
        idx[0] = input.indexOf("m/") + 2;
        idx[1] = input.indexOf(" ", idx[0]);
        idx[2] = input.indexOf("s/") + 2;
        idx[3] = input.indexOf(" ", idx[2]);
        idx[4] = input.indexOf("mc/") + 3;
        idx[5] = input.indexOf(" ", idx[4]);
        idx[6] = input.indexOf("g/") + 2;
        idx[7] = input.indexOf(" ", idx[6]);
        return idx;
    }

    @Override
    public void execute(ModuleList modulelist) {
        modulelist.add(this.mod, false);
    }
}
