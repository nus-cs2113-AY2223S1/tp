package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.model.Module;

public class SearchModuleCommandTest {
    @Test
    void searchModuleCommand_noFieldsEntered_exceptionThrown() {
        String input = "search";

        String expected = "Error! \tWrong format given, should be \n"
                + "\tsearch (/code [MODULE_CODE] | /title [KEYWORD]) </level [MODULE_LEVEL]> </sem [MODULE_SEMESTER]>";

        try {
            SearchModuleCommand searchModuleCommand = new SearchModuleCommand(input);
            fail();
        } catch (YamomException e) {
            assertEquals(expected.replaceAll("\\s+", ""), e.getMessage().replaceAll("\\s+", ""));
        }
    }

    @Test
    void searchModuleCommand_missingTitleAndCodeField_exceptionThrown() {
        String input = "search /level 2 /sem 1";

        String expected = "Error! \tSearch require at least a code field or a title field, in the format of: \n"
                + "\tsearch (/code [MODULE_CODE] | /title [KEYWORD]) </level [MODULE_LEVEL]> </sem [MODULE_SEMESTER]>";

        try {
            SearchModuleCommand searchModuleCommand = new SearchModuleCommand(input);
            fail();
        } catch (YamomException e) {
            assertEquals(expected.replaceAll("\\s+", ""), e.getMessage().replaceAll("\\s+", ""));
        }
    }

    @Test
    void filterModuleSearch_fullValidInputFields_expectCorrectNumberOfFilteredModule() {
        String toSearchModuleCode = "dtk1234";
        String toSearchModuleTitle = "Design Thinking";
        String toSearchLevel = "1";
        String toSearchSemester = "1";

        List<Module> searchResult = SearchModuleCommand.filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);
        int numberOfFilteredModulesInSearchResult = searchResult.size();
        int expectedNumberOfFilteredModules = 2;
        assertEquals(expectedNumberOfFilteredModules, numberOfFilteredModulesInSearchResult);
    }

    @Test
    void filterModuleSearch_onlyModuleCodeWithoutNumbers_expectCorrectNumberOfFilteredModule() {
        String toSearchModuleCode = "gea";
        String toSearchModuleTitle = null;
        String toSearchLevel = null;
        String toSearchSemester = null;

        List<Module> searchResult = SearchModuleCommand.filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);
        int numberOfFilteredModulesInSearchResult = searchResult.size();
        int expectedNumberOfFilteredModules = 2;
        assertEquals(expectedNumberOfFilteredModules, numberOfFilteredModulesInSearchResult);
    }

    @Test
    void filterModuleSearch_onlyModuleCodeWithoutLetters_expectCorrectNumberOfFilteredModule() {
        String toSearchModuleCode = "2113";
        String toSearchModuleTitle = null;
        String toSearchLevel = null;
        String toSearchSemester = null;

        List<Module> searchResult = SearchModuleCommand.filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);
        int numberOfFilteredModulesInSearchResult = searchResult.size();
        int expectedNumberOfFilteredModules = 7;
        assertEquals(expectedNumberOfFilteredModules, numberOfFilteredModulesInSearchResult);
    }

    @Test
    void filterModuleSearch_onlyModuleTitle_expectCorrectNumberOfFilteredModule() {
        String toSearchModuleCode = null;
        String toSearchModuleTitle = "samurai";
        String toSearchLevel = null;
        String toSearchSemester = null;

        List<Module> searchResult = SearchModuleCommand.filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);
        int numberOfFilteredModulesInSearchResult = searchResult.size();
        int expectedNumberOfFilteredModules = 3;
        assertEquals(expectedNumberOfFilteredModules, numberOfFilteredModulesInSearchResult);
    }

    @Test
    void filterModuleSearch_onlyModuleCodeAndTitle_expectCorrectNumberOfFilteredModule() {
        String toSearchModuleCode = "cs1010";
        String toSearchModuleTitle = "methodology";
        String toSearchLevel = null;
        String toSearchSemester = null;

        List<Module> searchResult = SearchModuleCommand.filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);
        int numberOfFilteredModulesInSearchResult = searchResult.size();
        int expectedNumberOfFilteredModules = 7;
        assertEquals(expectedNumberOfFilteredModules, numberOfFilteredModulesInSearchResult);
    }

    @Test
    void filterModuleSearch_missingSemesterInput_expectCorrectNumberOfFilteredModule() {
        String toSearchModuleCode = "cs1010";
        String toSearchModuleTitle = "methodology";
        String toSearchLevel = "1";
        String toSearchSemester = null;

        List<Module> searchResult = SearchModuleCommand.filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);
        int numberOfFilteredModulesInSearchResult = searchResult.size();
        int expectedNumberOfFilteredModules = 7;
        assertEquals(expectedNumberOfFilteredModules, numberOfFilteredModulesInSearchResult);
    }

    @Test
    void filterModuleSearch_allValidInputs_expectCorrectNumberOfFilteredModule() {
        String toSearchModuleCode = "cs1010";
        String toSearchModuleTitle = "methodology";
        String toSearchLevel = "1";
        String toSearchSemester = "2";

        List<Module> searchResult = SearchModuleCommand.filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);
        int numberOfFilteredModulesInSearchResult = searchResult.size();
        int expectedNumberOfFilteredModules = 4;
        assertEquals(expectedNumberOfFilteredModules, numberOfFilteredModulesInSearchResult);
    }
}