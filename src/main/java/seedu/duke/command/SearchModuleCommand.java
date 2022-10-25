package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.model.Module;
import seedu.duke.parser.Parser;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchModuleCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String COMMAND_USAGE = "search (/code [MODULE_CODE] | /title [KEYWORD]) "
            + "</level [MODULE_LEVEL]> </sem [MODULE_SEMESTER]>";
    public static final String COMMAND_DESCRIPTION = "List out all modules that contains a search term"
            + System.lineSeparator() + "\t * the search term can either be module code or a keyword in module title."
            + System.lineSeparator() + "\t * MODULE_LEVEL and MODULE_SEMESTER should be a single digit number";

    private static final String ERROR_WRONG_FORMAT = "Wrong format given, should be "
            + System.lineSeparator() + "\t" + COMMAND_USAGE;

    private static final String ERROR_MISSING_CODE_AND_TITLE = "Search require at least a code field "
            + "or a title field, in the format of: " + System.lineSeparator() + "\t" + COMMAND_USAGE;

    private Map<String, String> params;
    private static String toSearchModuleCode;
    private static String toSearchModuleTitle;
    private static String toSearchLevel;
    private static String toSearchSemester;

    private Logger logger;

    public static final String SUBSYSTEM_NAME = "SearchModuleCommand";

    public SearchModuleCommand(String input) throws YamomException {
        super(input.split("\\s"));
        params = Parser.parseParams(input);
        processParams();
    }

    public void execute(State state, Ui ui, Storage storage) {
        assert state != null : "List of lessons should not be null";
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.FINE, "Loading search module command");

        List<Module> searchResult = filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);

        ui.addMessage("Search Result:");

        if (searchResult.size() == 0) {
            ui.addMessage("No module found");
        } else {
            ui.addMessage("Total " + searchResult.size() + " module(s) found\n");
            for (Module module : searchResult) {
                ui.addMessage(module.moduleCode + " " + module.title);
            }
            ui.addMessage("\nTo get full details of the module, type 'get <module code>'");
        }

        ui.displayUi();
    }

    private void processParams() throws YamomException {

        if (params.isEmpty()) {
            throw new YamomException(ERROR_WRONG_FORMAT);
        }

        toSearchModuleCode = params.getOrDefault("code", null);
        toSearchModuleTitle = params.getOrDefault("title", null);
        toSearchLevel = params.getOrDefault("level", null);
        toSearchSemester = params.getOrDefault("sem", null);

        if (toSearchModuleCode == null && toSearchModuleTitle == null) {
            throw new YamomException(ERROR_MISSING_CODE_AND_TITLE);
        }
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
        return module.getSemesterData(toSearchSemesterInt) != null;
    }

    /**
     * Filter module by user input arguments and return a list of modules that match the search query.
     * If no arguments are provided, no module will be returned.
     * At least the module code or module title must be provided.
     * If both module code and module title are provided, results will be display based on similar module code
     * and module title but will not be repeated.
     * Arguments can be in any order.
     *
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
            List<Module> updatedSearchResult = new ArrayList<>();
            for (Module module : searchResult) {
                if (isSameModuleLevel(module, toSearchLevel)) {
                    updatedSearchResult.add(module);
                }
            }
            searchResult = updatedSearchResult;
        }

        // filter the searchResult if toSearchSemester is not empty and semester does not match
        if (toSearchSemester != null) {
            List<Module> updatedSearchResult = new ArrayList<>();
            for (Module module : searchResult) {
                if (isOfferedInSemester(module, toSearchSemester)) {
                    updatedSearchResult.add(module);
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

    public static String getCommandDescription() {
        return COMMAND_WORD + DESCRIPTION_DELIMITER + COMMAND_DESCRIPTION;
    }

    public static String getUsage() {
        return COMMAND_USAGE;
    }

}
