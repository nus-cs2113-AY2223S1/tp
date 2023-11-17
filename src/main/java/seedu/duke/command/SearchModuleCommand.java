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
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * Filter and list out modules that match user's search.
 * User can search by module code, module title, module level and the semester where the module is offered.
 */
public class SearchModuleCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String COMMAND_USAGE = "search [ /code PARTIAL_MODULE_CODE | /title KEYWORD ] "
            + "< /level MODULE_LEVEL > < /sem SEMESTER >"
            + System.lineSeparator() + Ui.INDENT + Ui.INDENT
            + " * the search term can either be module code or a keyword in module title, or both."
            + System.lineSeparator() + Ui.INDENT + Ui.INDENT
            + " * MODULE_LEVEL and SEMESTER should be a single digit number specified in the User Guide.";
    public static final String COMMAND_DESCRIPTION = "List out all modules that contains a search term.";

    public static final String ERROR_WRONG_FORMAT = "Wrong format given, should be "
            + System.lineSeparator() + "\t" + COMMAND_USAGE;
    public static final String ERROR_INVALID_SEMESTER = " is not a valid semester!";
    public static final String ERROR_INVALID_LEVEL = " is not a valid module level!";

    public static final String ERROR_MISSING_CODE_AND_TITLE = "Search require at least a code field "
            + "or a title field, in the format of: " + System.lineSeparator() + "\t" + COMMAND_USAGE;

    public static final String ERROR_MISSING_CODE = "Search require at least a code field, in the format of: "
            + System.lineSeparator() + "\t" + COMMAND_USAGE;

    public static final String ERROR_MISSING_TITLE = "Search require at least a title field, in the format of: "
            + System.lineSeparator() + "\t" + COMMAND_USAGE;

    public static final String ERROR_UNEXPECTED_PARAMETER = "Unexpected parameter: ";

    public static final String FOOTER = "\nTo get full details of the module, enter 'info [ MODULE_CODE ]'";

    public static final String SUBSYSTEM_NAME = "SearchModuleCommand";
    public static final int HEADING_INDENT = 10;

    String toSearchModuleCode;
    String toSearchModuleTitle;
    Integer toSearchLevel;
    Integer toSearchSemester;
    Map<String, String> params;

    public SearchModuleCommand(String input) throws YamomException {
        super(input.split("\\s+"));
        params = Parser.parseParams(input);
        processParams(); // validate params
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        Logger logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.FINE, "Loading search module command, starting to search for modules");

        List<Module> searchResult = filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);

        logger.log(Level.FINE, "Search module command loaded, printing search result");
        ui.addMessage("Search Result:");

        if (searchResult.size() == 0) {
            ui.addMessage("No module found");
        } else {
            ui.addMessage("Total " + searchResult.size() + " module(s) found\n");
            for (Module module : searchResult) {
                ui.addMessage(StringUtils.rightPad(module.moduleCode, HEADING_INDENT) + module.title);
            }
            ui.addMessage(FOOTER);
        }

        ui.displayUi();
    }

    /**
     * Process the parameters given by the user.
     *
     * @throws YamomException if the parameters given by the user is invalid or missing.
     */
    private void processParams() throws YamomException {
        if (params.isEmpty()) {
            throw new YamomException(ERROR_WRONG_FORMAT);
        }
        List<String> extraParams = params.keySet()
                .stream()
                .filter(k -> !List.of("code", "title", "level", "sem").contains(k))
                .collect(Collectors.toList());
        for (String p : extraParams) {
            throw new YamomException(ERROR_UNEXPECTED_PARAMETER + p);
        }
        toSearchModuleCode = params.getOrDefault("code", null);
        toSearchModuleTitle = params.getOrDefault("title", null);
        String level = params.getOrDefault("level", null);
        String semester = params.getOrDefault("sem", null);
        // validate level
        toSearchLevel = processSearchLevel(level);
        // validate semester
        toSearchSemester = processSemester(semester);

        if ("".equals(toSearchModuleCode)) {
            throw new YamomException(ERROR_MISSING_CODE);
        }

        if ("".equals(toSearchModuleTitle)) {
            throw new YamomException(ERROR_MISSING_TITLE);
        }

        if (toSearchModuleCode == null && toSearchModuleTitle == null) {
            throw new YamomException(ERROR_MISSING_CODE_AND_TITLE);
        }
    }

    private Integer processSearchLevel(String level) throws YamomException {
        if (level == null) {
            return null;
        }
        Integer searchLevel;
        try {
            searchLevel = Integer.parseInt(level);
        } catch (NumberFormatException e) {
            throw new YamomException(level + ERROR_INVALID_LEVEL);
        }
        if (searchLevel < 1 || searchLevel > 8) {
            throw new YamomException(level + ERROR_INVALID_LEVEL);
        }
        return searchLevel;
    }

    private Integer processSemester(String s) throws YamomException {
        Integer semester;
        if (s == null) {
            return null;
        }
        try {
            semester = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new YamomException(s + ERROR_INVALID_SEMESTER);
        }
        if (semester < 1 || semester > 4) {
            throw new YamomException(s + ERROR_INVALID_SEMESTER);
        }
        return semester;
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
     * @return list of modules that match the search query
     */
    public static List<Module> filterModuleSearch(String toSearchModuleCode, Integer toSearchLevel,
                                                  Integer toSearchSemester, String toSearchModuleTitle) {
        assert toSearchModuleCode != null || toSearchModuleTitle != null : "At least either the module code or title "
                + "must be provided to search for!";

        List<Module> searchResult = initialiseSearchResult(toSearchModuleCode, toSearchModuleTitle);

        // if search field is both module code and module title, then searchResult will be updated to only
        // showing modules that contains both user's input module code and title
        if (toSearchModuleCode != null && toSearchModuleTitle != null) {
            searchResult = filterByCodeAndTitle(searchResult, toSearchModuleCode, toSearchModuleTitle);

        }

        // filter the searchResult if toSearchLevel is not empty and level does not match
        if (toSearchLevel != null) {
            searchResult = filterByLevel(searchResult, toSearchLevel);
        }

        // filter the searchResult if toSearchSemester is not empty and semester does not match
        if (toSearchSemester != null) {
            searchResult = filterBySemester(searchResult, toSearchSemester);
        }

        return searchResult;
    }

    /**
     * Initialise the search result by adding modules that contains the module code or module title.
     *
     * @param toSearchModuleCode  module code to search for
     * @param toSearchModuleTitle module title to search for
     * @return list of modules that match the search query
     */
    public static List<Module> initialiseSearchResult(String toSearchModuleCode, String toSearchModuleTitle) {
        List<Module> moduleList = Module.getAll();
        List<Module> searchResult = new ArrayList<>();

        for (Module m : moduleList) {
            if (toSearchModuleCode != null && m.moduleCode.contains(toSearchModuleCode.toUpperCase())) {
                searchResult.add(m);
            }

            // add only if it is not already in the list
            if (toSearchModuleTitle != null && m.title.toLowerCase().contains(toSearchModuleTitle.toLowerCase())) {
                if (!searchResult.contains(m)) {
                    searchResult.add(m);
                }
            }
        }
        return searchResult;
    }

    /**
     * Filter the search result by module code and module title. This will remove duplicates in the initial search
     * result as well as return a list of modules that contains both the module code and module title.
     *
     * @param searchResult        list of modules to filter
     * @param toSearchModuleCode  module code to search for
     * @param toSearchModuleTitle module title to search for
     * @return list of modules that match the search query
     */
    public static List<Module> filterByCodeAndTitle(List<Module> searchResult, String toSearchModuleCode,
                                                    String toSearchModuleTitle) {
        List<Module> updatedSearchResult = new ArrayList<>();
        for (Module m : searchResult) {
            if (m.moduleCode.contains(toSearchModuleCode.toUpperCase()) && m.title.toLowerCase()
                    .contains(toSearchModuleTitle.toLowerCase())) {
                updatedSearchResult.add(m);
            }
        }
        return updatedSearchResult;
    }

    /**
     * Filter the list of search result by module level.
     *
     * @param searchResult A list of prior search result
     * @param toSearchLevel The module level to search for
     * @return an updated list of search result with the desired module level
     */
    public static List<Module> filterByLevel(List<Module> searchResult, Integer toSearchLevel) {
        List<Module> updatedSearchResult = new ArrayList<>();
        for (Module module : searchResult) {
            if (module.getLevel() == toSearchLevel) {
                updatedSearchResult.add(module);
            }
        }
        return updatedSearchResult;
    }

    /**
     * Filter the list of search result by the semester offered.
     *
     * @param searchResult A list of prior search result
     * @param toSearchSemester The semester offered to check for
     * @return an updated list of search result where the module is offered in the desired semester
     */
    public static List<Module> filterBySemester(List<Module> searchResult, Integer toSearchSemester) {
        List<Module> updatedSearchResult = new ArrayList<>();
        for (Module module : searchResult) {
            if (module.isOfferedInSemester(toSearchSemester)) {
                updatedSearchResult.add(module);
            }
        }
        return updatedSearchResult;
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