package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandFindClient;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.MissingFlagException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.FIND_FLAGS;
import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_FIND_INVALID_FLAG;


public class ParseFindClient extends Parser {

    private String commandDescription;
    private static final String EMPTY_TEXT = "";
    private static final String REGEX_CHECKER = "\\w+/";
    private static final int SINGLE_TAG = 1;

    public ParseFindClient(String commandDescription) {
        this.commandDescription = commandDescription;
    }

    @Override
    public Command parseCommand() throws DukeException {
        boolean hasNoDescription = commandDescription.trim().isEmpty();
        boolean isValidDescription = checkCommandValidity(commandDescription);

        if (hasNoDescription) {
            throw new EmptyDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        } else if (!isValidDescription) {
            throw new MissingFlagException(MESSAGE_FIND_INVALID_FLAG);
        } else {
            String queryText = commandDescription.replace(FIND_FLAGS, EMPTY_TEXT);
            return new CommandFindClient(queryText);
        }
    }

    private boolean checkCommandValidity(String commandDescription) {
        String[] commandSplitByTag = commandDescription.trim().split(REGEX_CHECKER);

        // Create an array list to store all non-empty string.
        ArrayList<String> queryLists = new ArrayList<>();

        boolean isEmptyString;
        for (int i = 0; i < commandSplitByTag.length; i += 1) {
            String currentString = commandSplitByTag[i];
            isEmptyString = currentString.equals(EMPTY_TEXT);
            if (!isEmptyString) {
                queryLists.add(currentString);
            }
        }

        boolean hasCorrectNumOfTag = queryLists.size() == SINGLE_TAG;
        boolean hasCorrectTag = commandDescription.contains(FIND_FLAGS);
        boolean isValidTag = hasCorrectTag & hasCorrectNumOfTag;
        if (isValidTag) {
            return true;
        }

        return false;
    }
}
