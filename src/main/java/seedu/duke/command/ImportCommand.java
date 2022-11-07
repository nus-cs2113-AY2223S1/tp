package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.parser.Parser;
import seedu.duke.utils.Link;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Import a sharable NUSMOD link into the YAMOM timetable.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    public static final String COMMAND_USAGE = "import [ NUSMODS_LINK ]";
    public static final String COMMAND_DESCRIPTION = 
            "Imports a timetable from an NUSMod timetable sharing link.";

    private String nusModLink;

    public ImportCommand(String[] input) throws YamomException {
        super(input);

        if (Parser.isOneWordCommand(input)) {
            throw new YamomException("No NUSMod link given.");
        } else if (Parser.isMultiWordsCommand(input)) {
            throw new YamomException("Too many parameters supplied.");
        } else if (!Link.isValidLink(input[1])) {
            throw new YamomException("The link you supplied is not valid.");
        } else if (Link.isEmptyLink(input[1])) {
            throw new YamomException("You are trying to import an empty timetable.");
        }
        nusModLink = input[1];
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {

        Logger logger = Logger.getLogger("Importing from NUSMod");
        try {
            Link.parseLink(nusModLink, state, ui);
            ui.addMessage(getExecutionMessage());
        } catch (YamomException e) {
            ui.addMessage(e.getMessage());
        }
        ui.displayUi();
        logger.log(Level.FINE, "Timetable imported from YAMOM");
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return "Timetable imported to YAMOM!";
    }
}
