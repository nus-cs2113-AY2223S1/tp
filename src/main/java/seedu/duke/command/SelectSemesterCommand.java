package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectSemesterCommand extends Command {
    public static final String COMMAND_WORD = "semester";
    public static final String FORMAT = "semester SEMESTER_SELECTED";
    public static final String HELP_DISPLAY = COMMAND_WORD
            + ": select semester to plan for and organise!\n"
            + "Usage:\t"
            + FORMAT;
    private static int updatedSemester;

    private Logger logger;

    public static final String SUBSYSTEM_NAME = "SelectSemesterCommand";


    public SelectSemesterCommand(String[] input) {
        super(input);
        this.updatedSemester = Integer.parseInt(input[1]);

    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        assert state != null : "List of lessons should not be null";
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.INFO, "Loading select semester command");

        assert updatedSemester >= 1 && updatedSemester <= 4 : "semester selected should be in a valid range";
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.INFO, "Updating semester currently being planned");



        state.setSemester(updatedSemester);
        ui.addMessage(getExecutionMessage());
        ui.displayUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {

        String outputMessage;

        if (updatedSemester == 1) {
            outputMessage = "You are now planning for semester 1";
        } else if (updatedSemester == 2) {
            outputMessage = "You are now planning for semester 2";
        } else if (updatedSemester == 3) {
            outputMessage = "You are now planning for special term I";
        } else if (updatedSemester == 4) {
            outputMessage = "You are now planning for special term II";
        } else {
            outputMessage = "A valid semester was not selected";
        }

        return outputMessage;
    }


}
