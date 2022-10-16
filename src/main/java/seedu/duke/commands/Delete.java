package seedu.duke.commands;

import seedu.duke.ModuleList;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

public class Delete extends Command {
    private String modCode;

    public Delete(String input) throws InvalidInputFormatException, InvalidInputContentException {
        checkFormat(input);
        int[] indexes = positions(input);
        checkContent(input, indexes);
        setModCode(input, indexes);
    }

    private void setModCode(String input, int[] indexes) {
        if (indexes[1] == -1) {
            this.modCode = input.substring(indexes[0]).toUpperCase();
        } else {
            this.modCode = input.substring(indexes[0], indexes[1]).toUpperCase();
        }
    }

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
