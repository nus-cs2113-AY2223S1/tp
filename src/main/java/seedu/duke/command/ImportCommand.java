package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.Link;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    public static final String FORMAT = "import NUSMods_LINK";
    public static final String HELP_DISPLAY = COMMAND_WORD
            + ": Imports timetable information from NUSMods generated link!\n"
            + "\tUsage:\t"
            + FORMAT
            + System.lineSeparator();
    private static String nusModLink;


    public ImportCommand(String[] input) throws YamomException {
        super(input);

        if (input.length != 2) {
            throw new YamomException("No nusmod link given");
        }
        nusModLink = input[1];
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {

        Logger logger = Logger.getLogger("Importing from NUSMod");
        try {
            Link.parseLink(nusModLink, state);
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
