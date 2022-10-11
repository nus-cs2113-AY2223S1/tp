package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.model.Module;

public class SearchModuleCodeCommand extends Command {
    public static final String COMMAND_WORD = "search";
    private String toSearchModuleCode;

    public SearchModuleCodeCommand(String[] input) {
        super(input);
        toSearchModuleCode = input[1];
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        List<Module> searchResult = filterModuleByCode(toSearchModuleCode);

        ui.addMessage("Module search list");

        // if searchResult only contain one module, it means the module code is unique, display the module details
        if (searchResult.size() == 1) {
            ui.addMessage(searchResult.get(0).moduleCode + " " + searchResult.get(0).title + "\n"
                    + searchResult.get(0).description);
        } else {
            for (Module module : searchResult) {
                ui.addMessage(module.moduleCode + " " + module.title);
            }
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
