package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.parser.Parser;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Select semester currently being modelled in state.
 */
public class SelectSemesterCommand extends Command {
    public static final String COMMAND_WORD = "semester";
    public static final String COMMAND_USAGE = "semester [ SEMESTER ]";
    public static final String COMMAND_DESCRIPTION =
            "Select another semester to plan and organize timetable.";

    private static final String ERROR_WRONG_FORMAT = "Wrong format, should be: " + COMMAND_USAGE;
    private static final String PLANNING_FOR = "You are now planning for ";
    private static final String INVALID_SEMESTER_SELECTED = "A valid semester was not selected";

    private static final String ERROR_MISSING_SEMESTER = "Please enter a semester number from 1 to 4 inclusive!";

    private int updatedSemester;

    private Logger logger;

    public static final String SUBSYSTEM_NAME = "SelectSemesterCommand";

    public SelectSemesterCommand(String[] input) throws YamomException {
        super(input);
        if (Parser.isOneWordCommand(input)) {
            throw new YamomException(ERROR_MISSING_SEMESTER);
        }
        if (!Parser.isValidSpecialTerm(input) && !Parser.isValidSemester(input)) {
            throw new YamomException(ERROR_WRONG_FORMAT + System.lineSeparator() + "Not a valid semester.");
        }
        this.updatedSemester = Integer.parseInt(input[1]);
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
            outputMessage = PLANNING_FOR + "semester 1";
        } else if (updatedSemester == 2) {
            outputMessage = PLANNING_FOR + "semester 2";
        } else if (updatedSemester == 3) {
            outputMessage = PLANNING_FOR + "special term I";
        } else if (updatedSemester == 4) {
            outputMessage = PLANNING_FOR + "special term II";
        } else {
            outputMessage = INVALID_SEMESTER_SELECTED;
        }

        return outputMessage;
    }
}
