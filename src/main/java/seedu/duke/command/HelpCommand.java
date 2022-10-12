package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String HELP_MODULE_COMMAND_INSTRUCTION = "help = lists all functions";
    public static final String ADD_MODULE_COMMAND_INSTRUCTION = "add MODULE_CODE - add a module into your planner!";
    public static final String DELETE_MODULE_COMMAND_INSTRUCTION = "delete MODULE - remove a module from your planner!";
    public static final String EXIT_MODULE_COMMAND_INSTRUCTION = "bye - exit YAMOM!";
    public static final String SEARCH_MODULE_COMMAND_INSTRUCTION = "search [MODULE_CODE][MODULE_NAME] - returns modules that match the search terms!";
    public static final String VIEW_TIMETABLE_COMMAND_INSTRUCTION = "view - displays current timetable!";
    public static final String SELECT_SLOT_COMMAND_INSTRUCTION = "select /module [MODULE_CODE] /type [LESSON_TYPE] /code [CLASS_NO] - select slot for modules!";


    public static final String HELP_DISPLAY_MESSAGE = "";
    public HelpCommand(String[] input) {
        super(input);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return null;
    }
}
