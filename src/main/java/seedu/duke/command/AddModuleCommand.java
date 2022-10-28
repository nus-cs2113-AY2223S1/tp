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
 * Add module to state by module code, updates storage and displays execution through ui.
 */

public class AddModuleCommand extends Command {
    private Module module;
    private boolean isSuccessfullyAdded;

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_USAGE = "add [ MODULE_CODE ]";
    public static final String COMMAND_DESCRIPTION = "Add a module into YAMOM timetable.";
    public static final String ERROR_WRONG_FORMAT = "Wrong format, should be: " + COMMAND_USAGE;
    public static final String MODULE_NOT_FOUND = "Module not found! Please enter a valid module code!";

    public AddModuleCommand(String[] input) throws YamomException {
        super(input);
        Parser.moduleRelatedCommandError(input, ERROR_WRONG_FORMAT);
        String moduleCode = input[1].toUpperCase();
        this.module = Module.get(moduleCode);
        this.isSuccessfullyAdded = false;
        if (this.module == null) {
            throw new YamomException(MODULE_NOT_FOUND);
        }
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        int semester = state.getSemester();
        boolean isModuleOffered = module.isOfferedInSemester(state.getSemester());
        if (!isModuleOffered) {
            ui.addMessage(module.moduleCode + " is not being offered this semester!");
            ui.displayUi();
            return;
        }

        SelectedModule selectedModule = new SelectedModule(module, semester);
        List<SelectedModule> currentSelectedModules = state.getSelectedModulesList();

        if (!currentSelectedModules.contains(selectedModule)) {
            state.addSelectedModule(selectedModule);
            isSuccessfullyAdded = true;
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

        if (isSuccessfullyAdded) {
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
