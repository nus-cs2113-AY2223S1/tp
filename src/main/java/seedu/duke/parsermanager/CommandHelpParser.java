package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandHelp;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.helpexception.UnwantedTextException;


public class CommandHelpParser extends Parser {
    private String command;
    private static String SPACE = " ";


    public CommandHelpParser(String input) {
        this.command = input;
    }

    @Override
    public Command parseCommand() throws DukeException {
        if (checkForAdditionalText()) {
            throw new UnwantedTextException();
        } else {
            return new CommandHelp();
        }
    }


    private boolean checkForAdditionalText() {
        String[] commandTexts = command.split(SPACE);
        boolean hasMoreThanOneText = (commandTexts.length > 1);

        return hasMoreThanOneText;
    }
}
