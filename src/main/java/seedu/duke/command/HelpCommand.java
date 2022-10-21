package seedu.duke.command;

import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String FORMAT = "help";
    public static final String HELP_DISPLAY = HelpCommand.COMMAND_WORD
            + ": lists all functions!\n"
            + "Usage\t"
            + FORMAT;

    public static final String HELP_DISPLAY_MESSAGE = HelpCommand.HELP_DISPLAY
            + AddModuleCommand.HELP_DISPLAY
            + DeleteModuleCommand.HELP_DISPLAY
            + DisplaySelectedModuleListCommand.HELP_DISPLAY
            + ExitCommand.HELP_DISPLAY
            + GetModuleCommand.HELP_DISPLAY
            + SearchModuleCommand.HELP_DISPLAY
            + SelectSlotCommand.HELP_DISPLAY
            + SelectSemesterCommand.HELP_DISPLAY
            + ViewTimetableCommand.HELP_DISPLAY;

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
