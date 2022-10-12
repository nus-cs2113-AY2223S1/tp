package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.model.Module;

public class SearchModuleFacultyCommand extends Command {
    public static final String COMMAND_WORD = "search";
    private String toSearchModuleFaculty;

    public SearchModuleFacultyCommand(String[] input) {
        super(input);
        toSearchModuleFaculty = input[1];
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        List<Module> searchResult = filterModuleByCode(toSearchModuleFaculty);

        ui.addMessage("Module search list");
        for (var m : searchResult) {
            ui.addMessage(m.moduleCode);
        }

        ui.displayUi();
    }

    /**
     * Filter module by module code and return a list of modules that match the search query.
     *
     * @return searchResult
     */
    public static List<Module> filterModuleByCode(String toSearchModuleCode) {
        List<Module> moduleList = Module.getAll();
        List<Module> searchResult = new ArrayList<>();
        for (Module m : moduleList) {
            if (m.moduleCode.contains(toSearchModuleCode.toUpperCase())) {
                searchResult.add(m);
            }
        }
        return searchResult;
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
