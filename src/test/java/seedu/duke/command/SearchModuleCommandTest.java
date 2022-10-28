package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.model.Module;

public class SearchModuleCommandTest {
    @Test
    void searchModuleCommand_noFieldsEntered_exceptionThrown() {
        String input = "search";

        String expected = "Error! " + SearchModuleCommand.ERROR_WRONG_FORMAT;

        try {
            SearchModuleCommand searchModuleCommand = new SearchModuleCommand(input);
            searchModuleCommand.execute(null, null, null);
            fail();
        } catch (YamomException e) {
            assertEquals(expected.replaceAll("\\s+", ""), e.getMessage().replaceAll("\\s+", ""));
        }
    }

    @Test
    void searchModuleCommand_missingTitleAndCodeField_exceptionThrown() {
        String input = "search /level 2 /sem 1";

        String expected = "Error! " + SearchModuleCommand.ERROR_MISSING_CODE_AND_TITLE;

        try {
            SearchModuleCommand searchModuleCommand = new SearchModuleCommand(input);
            searchModuleCommand.execute(null, null, null);
            fail();
        } catch (YamomException e) {
            assertEquals(expected.replaceAll("\\s+", ""), e.getMessage().replaceAll("\\s+", ""));
        }
    }

    @Test
    void filterModuleSearch_fullValidInputFields_expectCorrectNumberOfFilteredModule() {
        String toSearchModuleCode = "dtk1234";
        String toSearchModuleTitle = "Design Thinking";
        Integer toSearchLevel = 1;
        Integer toSearchSemester = 1;

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
        Integer toSearchLevel = null;
        Integer toSearchSemester = null;

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
        Integer toSearchLevel = null;
        Integer toSearchSemester = null;

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
        Integer toSearchLevel = null;
        Integer toSearchSemester = null;

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
        Integer toSearchLevel = null;
        Integer toSearchSemester = null;

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
        Integer toSearchLevel = 1;
        Integer toSearchSemester = null;

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
        Integer toSearchLevel = 1;
        Integer toSearchSemester = 2;

        List<Module> searchResult = SearchModuleCommand.filterModuleSearch(toSearchModuleCode, toSearchLevel,
                toSearchSemester, toSearchModuleTitle);
        int numberOfFilteredModulesInSearchResult = searchResult.size();
        int expectedNumberOfFilteredModules = 4;
        assertEquals(expectedNumberOfFilteredModules, numberOfFilteredModulesInSearchResult);
    }

    @Test
    public void searchCommand_parsing_invalidSemester() {
        assertThrows(YamomException.class, () -> new SearchModuleCommand("search /title programming /sem 5"));
    }

    @Test
    public void searchCommand_parsing_validSemester() throws YamomException {
        SearchModuleCommand smc1 = new SearchModuleCommand("search /title programming");
        assertEquals(smc1.toSearchLevel, null);
        assertEquals(smc1.toSearchModuleTitle, "programming");
        assertEquals(smc1.toSearchModuleCode, null);
        assertEquals(smc1.toSearchSemester, null);
        SearchModuleCommand smc2 = new SearchModuleCommand("search /title programming methodology /sem 1");
        assertEquals(smc2.toSearchLevel, null);
        assertEquals(smc2.toSearchModuleTitle, "programming methodology");
        assertEquals(smc2.toSearchModuleCode, null);
        assertEquals(smc2.toSearchSemester, 1);
        SearchModuleCommand smc3 = new SearchModuleCommand("search /code CS1 /sem 3 /level 1");
        assertEquals(smc3.toSearchLevel, 1);
        assertEquals(smc3.toSearchModuleTitle, null);
        assertEquals(smc3.toSearchModuleCode, "CS1");
        assertEquals(smc3.toSearchSemester, 3);
    }
}