package seedu.duke.commands;

import seedu.duke.Module;
import seedu.duke.ModuleList;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

public class Add extends Command {
    private Module mod;

    public Add(String input) throws InvalidInputFormatException, InvalidInputContentException {
        checkFormat(input);
        int[] indexes = positions(input);
        checkContent(input, indexes);
        addition(input, indexes);
    }

    private void addition(String input, int[] indexes) {
        String course = extractingContent(input, indexes[0], indexes[1]);
        String semester = extractingContent(input, indexes[2], indexes[3]);
        String mcString = extractingContent(input, indexes[4], indexes[5]);
        int mc = Integer.parseInt(mcString);
        String grade = extractingContent(input, indexes[6], indexes[7]);

        this.mod = new Module(course.toUpperCase(), semester.toUpperCase(), grade.toUpperCase(), mc);
    }

    private String extractingContent(String input, int startIndex, int endIndex) {
        if (endIndex == -1) {
            return input.substring(startIndex);
        } else {
            return input.substring(startIndex, endIndex);
        }
    }

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

    public void checkFormatException(boolean isRight) throws InvalidInputFormatException {
        if (!isRight) {
            throw new InvalidInputFormatException();
        }
    }

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

    public void checkContentException(boolean isSame) throws InvalidInputContentException {
        if (isSame) {
            throw new InvalidInputContentException();
        }
    }

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
        modulelist.add(this.mod);
    }
}
