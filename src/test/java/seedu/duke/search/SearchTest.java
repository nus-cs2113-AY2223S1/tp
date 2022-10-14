package seedu.duke.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.duke.command.SearchModuleCodeCommand;
import seedu.duke.command.SearchModuleNameCommand;
import seedu.duke.model.Module;

public class SearchTest {
    @Test
    void filterModuleByCode_validInput_expectCorrectNumberOfFilteredModule() {
        // String inputString = "cs1010";
        String inputString = "GEA1000";
        List<Module> searchResult = SearchModuleCodeCommand.filterModuleByCode(inputString);
        // int numberOfFilteredModulesInSearchResult = SearchModuleCodeCommand.getNumberOfModulesFound(searchResult);
        int numberOfFilteredModulesInSearchResult = searchResult.size();
        // int expectedNumberOfFilteredModules = 7;
        int expectedNumberOfFilteredModules = 2;
        assertEquals(expectedNumberOfFilteredModules, numberOfFilteredModulesInSearchResult);
    }

    @Test
    void filterModuleByName_validInput_expectCorrectNumberOfFilteredModule() {
        // String inputString = "quantitative reasoning";
        String inputString = "quantitative reasoning with data";
        List<Module> searchResult = SearchModuleNameCommand.filterModuleByName(inputString);
        int numberOfFilteredModulesInSearchResult = searchResult.size();
        // int expectedNumberOfFilteredModules = 21;
        int expectedNumberOfFilteredModules = 2;
        assertEquals(expectedNumberOfFilteredModules, numberOfFilteredModulesInSearchResult);
    }


}
