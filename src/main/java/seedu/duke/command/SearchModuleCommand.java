package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.exceptions.YamomException;
import seedu.duke.model.Module;
import seedu.duke.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchModuleCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String COMMAND_USAGE = "search /code [SIMILAR_MODULE_CODE] /title [SIMILAR_MODULE_TITLE] "
            + "/level <level> /sem <semester>";
    public static final String COMMAND_DESCRIPTION = "List out all modules that contains matching input search fields"
            + System.lineSeparator() + "\t * Either the module code or title has to be present.";

    private Map<String, String> params;
    private String toSearchModuleCode;
    private String toSearchModuleTitle;
    private String toSearchLevel;
    private String toSearchSemester;

    private Logger logger;
    public static final String SUBSYSTEM_NAME = "SearchModuleCommand";

    public SearchModuleCommand(String input) throws YamomException {
        super(input.split("\\s"));
        params = Parser.parseParams(input);
        toSearchModuleCode = params.getOrDefault("code", null);
        toSearchModuleTitle = params.getOrDefault("title", null);
        toSearchLevel = params.getOrDefault("level", null);
        toSearchSemester = params.getOrDefault("sem", null);

        // if size of params is 0, means no params are given, throw exception
        if (params.size() == 0) {
            throw new YamomException("Please input valid search fields to search for! You can search by module code, "
                    + "module title, level and semester.\n\nType [help] for assistance!");
        }
        if (toSearchModuleCode == null && toSearchModuleTitle == null) {
            throw new YamomException("Please input at least either the module code or title to search for!"
                    + "\n\nType [help] for assistance!");
        }
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.FINE, "Loading search module command, starting to search for modules");

        List<Module> searchResult = filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);

        logger.log(Level.FINE, "Search module command loaded, printing search result");
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
     * At least either the module code or module title must be provided.
     * If both module code and module title are provided, results displayed will contain modules that contains both
     * the module code and module title.
     * The level and semester arguments are optional. If provided, the results will be filtered and further
     * refined based on the input level and semester.
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
        assert toSearchModuleCode != null || toSearchModuleTitle != null : "At least either the module code or title "
                + "must be provided to search for!";
        List<Module> moduleList = Module.getAll();
        List<Module> searchResult = new ArrayList<>();

        // add all the mods with similar toSearchModuleCode and toSearchModuleTitle to searchResult
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

        // if search field is both module code and module title, then searchResult will be updated to only
        // showing modules that contains both user's input module code and title
        if (toSearchModuleCode != null && toSearchModuleTitle != null) {
            List<Module> updatedSearchResult = new ArrayList<>();
            for (Module m : searchResult) {
                if (m.moduleCode.contains(toSearchModuleCode.toUpperCase()) && m.title.toLowerCase()
                        .contains(toSearchModuleTitle.toLowerCase())) {
                    updatedSearchResult.add(m);
                }
            }
            searchResult = updatedSearchResult;
        }

        // filter the searchResult if toSearchLevel is not empty and level does not match
        if (toSearchLevel != null) {
            List<Module> updatedSearchResult = new ArrayList<>();
            ;
            for (int i = 0; i < searchResult.size(); i++) {
                if (isSameModuleLevel(searchResult.get(i), toSearchLevel)) {
                    updatedSearchResult.add(searchResult.get(i));
                }
            }
            searchResult = updatedSearchResult;
        }

        // filter the searchResult if toSearchSemester is not empty and semester does not match
        if (toSearchSemester != null) {
            List<Module> updatedSearchResult = new ArrayList<>();
            ;
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

    public static String getCommandDescription() {
        return COMMAND_WORD + DESCRIPTION_DELIMITER + COMMAND_DESCRIPTION;
    }

    public static String getUsage() {
        return COMMAND_USAGE;
    }
}
