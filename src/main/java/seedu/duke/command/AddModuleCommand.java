package seedu.duke.command;

import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.Map;

/**
 * Add module to state by module code, updates storage and displays execution through ui.
 */

public class AddModuleCommand extends Command {
    Module module;

    public static final String COMMAND_WORD = "add";

    public AddModuleCommand(String[] input) { //
        super(input);
        String moduleCode = input[1].toUpperCase();
        this.module = Module.get(moduleCode);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        int semester = state.getSemester();
        SelectedModule selectedModule = new SelectedModule(module, semester);

        state.addSelectedModule(selectedModule);
        ui.addMessage(getExecutionMessage());
        ui.displayUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return module.moduleCode + " has been added";
    }
}
