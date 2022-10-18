package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.model.Module;
import seedu.duke.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchModuleCommand extends Command {
    public static final String COMMAND_WORD = "search";

    // private String toSearchModuleCode;
    private Map<String, String> params;
    private String toSearchModuleCode;
    private String toSearchModuleTitle;
    private String toSearchLevel;
    private String toSearchSemester;

    private Logger logger;

    public static final String SUBSYSTEM_NAME = "SearchModuleCommand";

    public SearchModuleCommand(String input) {
        super(input.split("\\s"));
        params = Parser.parseParams(input);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        assert state != null : "List of lessons should not be null";
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.INFO, "Loading search module command");

        toSearchModuleCode = params.getOrDefault("code", null);
        toSearchModuleTitle = params.getOrDefault("title", null);
        toSearchLevel = params.getOrDefault("level", null);
        toSearchSemester = params.getOrDefault("sem", null);

        List<Module> searchResult = filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);

        ui.addMessage("Search Result:");

        if (searchResult.size() == 0) {
            ui.addMessage("No module found");
        } else {
            ui.addMessage("Total " + searchResult.size() + " module(s) found\n");
            for (int i = 0; i < searchResult.size(); i++) {
                ui.addMessage(searchResult.get(i).moduleCode + " " + searchResult.get(i).title);
            }
            ui.addMessage("\nTo get full details of the module, type 'get <module code>'");
        }

        ui.displayUi();
    }

    /**
     * Filter the module list based on input module level.
     *
     * @param module the module object
     * @param level  the level that user input
     * @return true if module level matches input level
     */
    static boolean isSameModuleLevel(Module module, String level) {
        // get the first integer embedded in the module code
        int moduleLevel = module.getModuleLevel(module);
        return level.equals(Integer.toString(moduleLevel));
    }

    /**
     * Filters the module list based on the input semester.
     *
     * @param module           the module object
     * @param toSearchSemester the semester that user input
     * @return true if module is offered in the semester
     */
    static boolean isOfferedInSemester(Module module, String toSearchSemester) {
        // convert toSearchSemester to int
        int toSearchSemesterInt = Integer.parseInt(toSearchSemester);

        // check module is offered in which semester
        if (module.getSemesterData(toSearchSemesterInt) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Filter module by user input arguments and return a list of modules that match the search query.
     * If no arguments are provided, no module will be returned.
     * At least the module code or module title must be provided.
     * If both module code and module title are provided, results will be display based on similar module code
     * and module title but will not be repeated.
     * Arguments can be in any order.
     *
     * @param toSearchModuleCode  the module code that user input
     * @param toSearchLevel       the level that user input
     * @param toSearchSemester    the semester that user input
     * @param toSearchModuleTitle the module title that user input
     * @return list of modules that match the search query
     */
    public static List<Module> filterModuleSearch(String toSearchModuleCode, String toSearchLevel,
                                                  String toSearchSemester, String toSearchModuleTitle) {
        List<Module> moduleList = Module.getAll();
        List<Module> searchResult = new ArrayList<>();

        // add all the mods with similar toSearchModuleCode to searchResult
        for (Module m : moduleList) {
            if (toSearchModuleCode != null && m.moduleCode.contains(toSearchModuleCode.toUpperCase())) {
                searchResult.add(m);
            }

            if (toSearchModuleTitle != null && m.title.toLowerCase().contains(toSearchModuleTitle.toLowerCase())) {
                // add only if it is not already in the list
                if (!searchResult.contains(m)) {
                    searchResult.add(m);
                }
            }
        }

        // filter the searchResult if toSearchLevel is not empty and level does not match
        if (toSearchLevel != null) {
            List<Module> updatedSearchResult = new ArrayList<>();;
            for (int i = 0; i < searchResult.size(); i++) {
                if (isSameModuleLevel(searchResult.get(i), toSearchLevel)) {
                    updatedSearchResult.add(searchResult.get(i));
                }
            }
            searchResult = updatedSearchResult;
        }

        // filter the searchResult if toSearchSemester is not empty and semester does not match
        if (toSearchSemester != null) {
            List<Module> updatedSearchResult = new ArrayList<>();;
            for (int i = 0; i < searchResult.size(); i++) {
                if (isOfferedInSemester(searchResult.get(i), toSearchSemester)) {
                    updatedSearchResult.add(searchResult.get(i));
                }
            }
            searchResult = updatedSearchResult;
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
