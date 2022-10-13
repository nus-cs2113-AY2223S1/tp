package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import seedu.duke.model.Module;
import seedu.duke.parser.Parser;

public class SearchModuleCodeCommand extends Command {
    public static final String FORMAT = "search /module [MODULE_CODE] /module level [LEVEL] /semester [SEMESTER]";
    public static final String COMMAND_WORD = "search";

    private String toSearchModuleCode;
    private Map<String, String> params;

    public SearchModuleCodeCommand(String input) {
        super(input.split("\\s"));
        params = Parser.parseParams(input);
        toSearchModuleCode = params.get("module").toUpperCase();
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        List<Module> searchResult = filterModuleByCode(toSearchModuleCode);

        ui.addMessage("Search Result:");

        // if searchResult only contain one module, it means the module code is unique, display the module details
        if (searchResult.size() == 1) {
            ui.addMessage(searchResult.get(0).moduleCode + " " + searchResult.get(0).title + "\n"
                    + searchResult.get(0).description);
        } else {
            for (Module module : searchResult) {
                ui.addMessage(module.moduleCode + " " + module.title);
            }
        }

        ui.addMessage("To get full details of the module, type 'search full <module code>'");
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
