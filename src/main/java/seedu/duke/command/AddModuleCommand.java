package seedu.duke.command;

import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.List;
import java.util.Map;

/**
 * Add module to state by module code, updates storage and displays execution through ui.
 */

public class AddModuleCommand extends Command {
    private Module module;
    private boolean successful;

    public static final String COMMAND_WORD = "add";
    private static final String COMMAND_USAGE = "add [MODULE_CODE]";
    private static final String COMMAND_DESCRIPTION = "add a module into YAMOM timetable.";

    public AddModuleCommand(String[] input) {
        super(input);
        String moduleCode = input[1].toUpperCase();
        this.module = Module.get(moduleCode);
        successful = false;
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        int semester = state.getSemester();
        SelectedModule selectedModule = new SelectedModule(module, semester);

        List<SelectedModule> currentSelectedModules = state.getSelectedModulesList();

        if (!currentSelectedModules.contains(selectedModule)) {
            state.addSelectedModule(selectedModule);
            successful = true;
        }

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
        if (successful) {
            outputMessage = module.moduleCode + " has been added";
        } else {
            outputMessage = module.moduleCode + " has already been added!";
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
