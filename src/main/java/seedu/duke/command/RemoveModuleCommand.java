package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;
import seedu.duke.parser.Parser;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.List;

/**
 * Delete module to state by module code, updates storage and displays execution through ui.
 */
public class RemoveModuleCommand extends Command {
    private Module module;
    private boolean successful;

    public static final String COMMAND_WORD = "remove";
    public static final String COMMAND_USAGE = "remove [ MODULE_CODE ]";
    public static final String COMMAND_DESCRIPTION = "Remove a module from YAMOM timetable.";

    private static final String ERROR_WRONG_FORMAT = "Wrong format, should be: " + COMMAND_USAGE;
    public static final String MODULE_NOT_FOUND = "Module not found in database! Please enter a valid module code!";

    public RemoveModuleCommand(String[] input) throws YamomException {
        super(input);
        Parser.moduleRelatedCommandError(input, ERROR_WRONG_FORMAT);
        String moduleCode = input[1].toUpperCase();
        this.module = Module.get(moduleCode.toUpperCase());
        if (this.module == null) {
            throw new YamomException(MODULE_NOT_FOUND);
        }
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
