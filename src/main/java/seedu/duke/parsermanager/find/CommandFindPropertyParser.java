package seedu.duke.parsermanager.find;

//@@author wilsonngja
import seedu.duke.command.Command;
import seedu.duke.command.CommandFindProperty;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.parsefindexception.FindEmptyDescriptionException;
import seedu.duke.exception.parsefindexception.FindIncorrectNumOfTagException;
import seedu.duke.exception.parsefindexception.NoFindPropertyTagException;
import seedu.duke.parsermanager.Parser;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.FIND_FLAGS;

public class CommandFindPropertyParser extends Parser {

    private String commandDescription;
    private static final String EMPTY_TEXT = "";
    private static final String REGEX_CHECKER = "f/";
    private static final int SINGLE_TAG = 1;
    private static final int MAXIMUM_SPLIT = 2;
    private static final int INDEX_BEFORE_FIND_FLAG = 0;
    private static final int INDEX_AFTER_FIND_FLAG = 1;

    public CommandFindPropertyParser(String commandDescription) {
        this.commandDescription = commandDescription;
    }

    @Override
    public Command parseCommand() throws DukeException {


        checkCommandValidity(commandDescription);


        String queryText = commandDescription.replace(FIND_FLAGS, EMPTY_TEXT);
        return new CommandFindProperty(queryText.trim());

    }

    public void checkCommandValidity(String commandDescription) throws FindIncorrectNumOfTagException,
            FindEmptyDescriptionException, NoFindPropertyTagException {

        String[] commandSplitByTag = commandDescription.trim().split(REGEX_CHECKER,MAXIMUM_SPLIT);
        boolean hasNoDescription = commandDescription.isEmpty();

        // IF the input is empty, throw empty exception
        if (hasNoDescription) {
            throw new NoFindPropertyTagException();
        }

        boolean hasOtherTag = !commandSplitByTag[INDEX_BEFORE_FIND_FLAG].equals(EMPTY_TEXT);
        boolean hasFindTag = commandSplitByTag.length > SINGLE_TAG;
        boolean hasCorrectTag = hasFindTag && !hasOtherTag;
        if (!hasCorrectTag) {
            throw new FindIncorrectNumOfTagException();
        } else {
            boolean hasNoQuery = commandSplitByTag[INDEX_AFTER_FIND_FLAG].trim().isEmpty();
            if (hasNoQuery) {
                throw new FindEmptyDescriptionException();
            }
        }


        // Create an array list to store all non-empty string.
        ArrayList<String> queryLists = new ArrayList<>();

        boolean isEmptyString;
        for (int i = 0; i < commandSplitByTag.length; i += 1) {
            String currentString = commandSplitByTag[i].trim();
            isEmptyString = currentString.equals(EMPTY_TEXT);
            if (!isEmptyString) {
                queryLists.add(currentString);
            }
        }
    }
}
//@@author