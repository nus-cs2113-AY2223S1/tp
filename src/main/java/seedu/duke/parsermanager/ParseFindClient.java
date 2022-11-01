package seedu.duke.parsermanager;

//@@author wilsonngja
import seedu.duke.command.Command;
import seedu.duke.command.CommandFindClient;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.parsefindexception.FindEmptyDescriptionException;
import seedu.duke.exception.parsefindexception.FindIncorrectNumOfTagException;
import seedu.duke.exception.parsefindexception.NoFindClientTagException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.FIND_FLAGS;


public class ParseFindClient extends Parser {

    private String commandDescription;
    private static final String EMPTY_TEXT = "";
    private static final String REGEX_CHECKER = "f/";
    private static final int SINGLE_TAG = 1;

    public ParseFindClient(String commandDescription) {
        this.commandDescription = commandDescription;
    }

    @Override
    public Command parseCommand() throws DukeException {


        checkCommandValidity(commandDescription);

        String queryText = commandDescription.replace(FIND_FLAGS, EMPTY_TEXT);

        return new CommandFindClient(queryText.trim());
    }

    public void checkCommandValidity(String commandDescription) throws FindIncorrectNumOfTagException,
            FindEmptyDescriptionException, NoFindClientTagException {
        String[] commandSplitByTag = commandDescription.trim().split(REGEX_CHECKER, 2);
        boolean hasNoDescription = commandDescription.isEmpty();


        if (hasNoDescription) {
            throw new NoFindClientTagException();
        }

        boolean hasOtherTag = !commandSplitByTag[0].equals(EMPTY_TEXT);
        boolean hasFindTag = commandSplitByTag.length > SINGLE_TAG;
        boolean hasCorrectTag = hasFindTag && !hasOtherTag;

        if (!hasCorrectTag) {
            throw new FindIncorrectNumOfTagException();
        } else {
            boolean hasNoQueryMessage = commandSplitByTag[1].trim().isEmpty();
            if (hasNoQueryMessage) {
                throw new FindEmptyDescriptionException();
            }
        }

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
    }
}
//@@author