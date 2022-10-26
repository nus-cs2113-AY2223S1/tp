package seedu.duke.commands;

import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.ModuleList;
import seedu.duke.exceptions.InvalidInputFormatException;

public class Find extends Command {
    private String keyword;

    /**
     * Constructor to create an object of the Find Command class
     * @param keyword the keyword inputted by the user. Format: String
     * @throws InvalidInputContentException if the user does not add a keyword
     */
    public Find(String keyword) throws InvalidInputContentException {
        this.keyword = keyword.toUpperCase();
        int[] idx = {0, keyword.length()};
        checkContent(keyword, idx);
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

    @Override
    public void execute(ModuleList moduleList) {
        moduleList.find(keyword);
    }

}
