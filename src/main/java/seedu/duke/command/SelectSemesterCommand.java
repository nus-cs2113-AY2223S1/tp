package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectSemesterCommand extends Command {
    public static final String COMMAND_WORD = "semester";
    public static final String COMMAND_USAGE = "semester [SEMESTER_SELECTED]";
    public static final String COMMAND_DESCRIPTION = "Select another semester "
            + "to plan and organize timetable";

    private static final String ERROR_WRONG_FORMAT = "Wrong format, should be: " + COMMAND_USAGE;

    private int updatedSemester;

    private Logger logger;

    public static final String SUBSYSTEM_NAME = "SelectSemesterCommand";


    public SelectSemesterCommand(String[] input) throws YamomException {
        super(input);
        try {
            this.updatedSemester = Integer.parseInt(input[1]);
        } catch (Exception e) {
            throw new YamomException(ERROR_WRONG_FORMAT);
        }
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        assert state != null : "List of lessons should not be null";
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.FINE, "Loading select semester command");

        assert updatedSemester >= 1 && updatedSemester <= 4 : "semester selected should be in a valid range";
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.FINE, "Updating semester currently being planned");

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

    public static String getCommandDescription() {
        return COMMAND_WORD + DESCRIPTION_DELIMITER + COMMAND_DESCRIPTION;
    }

    public static String getUsage() {
        return COMMAND_USAGE;
    }
}
