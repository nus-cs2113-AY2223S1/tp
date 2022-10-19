package seedu.duke.command;

import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String HELP_MODULE_COMMAND_USAGE =  HelpCommand.COMMAND_WORD
            + ": lists all functions!\n";
    public static final String ADD_MODULE_COMMAND_USAGE = AddModuleCommand.COMMAND_WORD
            + ": add a module into your planner!\n";
    public static final String DELETE_MODULE_COMMAND_USAGE = DeleteModuleCommand.COMMAND_WORD
            + ": delete MODULE - remove a module from your planner!\n";
    public static final String EXIT_MODULE_COMMAND_USAGE = ExitCommand.COMMAND_WORD
            + ": exit Yet Another Module Organiser / Manager (YAMOM)!\n";
    public static final String SEARCH_MODULE_COMMAND_USAGE = SearchModuleCommand.COMMAND_WORD
            + ": search /code [MODULE_CODE] /title [MODULE_TITLE] /level [MODULE_LEVEL] "
            + "/sem [SEMESTER_OFFERED] - returns modules that match the similar search terms!\n";
    public static final String VIEW_TIMETABLE_COMMAND_USAGE = ViewTimetableCommand.COMMAND_WORD
            + ": displays current timetable!\n";
    public static final String SELECT_SLOT_COMMAND_USAGE = SelectSlotCommand.COMMAND_WORD
            + ": select /module [MODULE_CODE] /type [LESSON_TYPE] /code [CLASS_NO] - select slot for modules!\n";
    public static final String SELECT_SEMESTER_COMMAND_USAGE = SelectSemesterCommand.COMMAND_WORD
            + ": semester [SEMESTER SELECTED] = select semester to plan for and organise!\n";


    public static final String HELP_DISPLAY_MESSAGE = HELP_MODULE_COMMAND_USAGE
            + ADD_MODULE_COMMAND_USAGE
            + DELETE_MODULE_COMMAND_USAGE
            + EXIT_MODULE_COMMAND_USAGE
            + SEARCH_MODULE_COMMAND_USAGE
            + VIEW_TIMETABLE_COMMAND_USAGE
            + SELECT_SLOT_COMMAND_USAGE
            + SELECT_SEMESTER_COMMAND_USAGE;

    public HelpCommand(String[] input) {
        super(input);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        ui.addMessage(HELP_DISPLAY_MESSAGE);
        ui.displayUi();
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
