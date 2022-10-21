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
    public static final String FORMAT = "add MODULE_CODE";
    public static final String HELP_DISPLAY = COMMAND_WORD
            + ": add a module into your planner!\n"
            + "Usage:\t"
            + FORMAT;

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
}
