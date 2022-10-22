package seedu.duke.command;

import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.List;

/**
 * Delete module to state by module code, updates storage and displays execution through ui.
 */

public class DeleteModuleCommand extends Command {
    Module module;
    private boolean successful;

    public static final String COMMAND_WORD = "delete";
    public static final String FORMAT = "delete MODULE_CODE";
    public static final String HELP_DISPLAY = COMMAND_WORD
            + ": remove a module from your planner!\n"
            + "\tUsage:\t"
            + FORMAT
            + System.lineSeparator();

    public DeleteModuleCommand(String[] input) {
        super(input);
        String moduleCode = input[1];
        this.module = Module.get(moduleCode.toUpperCase());
        this.successful = false;
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        int semester = state.getSemester();

        SelectedModule selectedModule = new SelectedModule(module, semester);

        List<SelectedModule> currentSelectedModules = state.getSelectedModulesList();

        if (currentSelectedModules.contains(selectedModule)) {
            state.removeSelectedModule(selectedModule);
            successful = true;
        }

        ui.addMessage(getExecutionMessage());
        ui.displayUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public String getExecutionMessage() {
        String outputMessage;
        if (successful) {
            outputMessage = module.moduleCode + " has been deleted!";
        } else {
            outputMessage = module.moduleCode + " does not exist in current list of selected list modules!";
        }
        return outputMessage;
    }

}
