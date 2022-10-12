package seedu.duke.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.model.Module;

public class SearchModuleNameCommand extends Command {
    public static final String COMMAND_WORD = "search";
    private String searchModuleName;

    public SearchModuleNameCommand(String[] input) {
        super(input);
        searchModuleName = Arrays.copyOfRange(input, 1, input.length)
                .toString();
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        List<Module> searchResult = filterModuleByName(searchModuleName);

        ui.addMessage("Module search list");
        for (var m : searchResult) {
            ui.addMessage(m.title);
        }

        ui.displayUi();
    }

    /**
     * Filter module by module name and return a list of modules that match the search query.
     *
     * @return searchResult
     */
    public static List<Module> filterModuleByName(String searchModuleName) {
        List<Module> moduleList = Module.getAll();
        List<Module> searchResult = new ArrayList<>();
        for (Module m : moduleList) {
            if (m.title.toLowerCase().contains(searchModuleName.toLowerCase())) {
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
