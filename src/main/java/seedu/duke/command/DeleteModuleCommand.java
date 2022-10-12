package seedu.duke.command;

import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class DeleteModuleCommand extends Command {
    Module module;

    public DeleteModuleCommand(String[] input) {
        super(input);
        String moduleCode = input[1];
        this.module = Module.get(moduleCode);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        int semester = state.getSemester();

        SelectedModule selectedModule = new SelectedModule(module, semester);

        state.removeSelectedModule(selectedModule);
        ui.addMessage(getExecutionMessage());
        ui.displayUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public String getExecutionMessage() {
        return module.moduleCode + " has been deleted";
    }

}
