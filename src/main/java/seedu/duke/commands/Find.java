package seedu.duke.commands;

import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.ModuleList;

public class Find extends Command {
    private String keyword;

    public Find(String keyword) throws InvalidInputContentException {
        this.keyword = keyword.toUpperCase();
        int[] idx = {0, keyword.length()};
        checkContent(keyword, idx);
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

    @Override
    public void execute(ModuleList moduleList) {
        moduleList.find(keyword);
    }

}
