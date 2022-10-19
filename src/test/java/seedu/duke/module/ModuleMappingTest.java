package seedu.duke.module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidModuleMappingException;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.university.University;

public class ModuleMappingTest {
    @Test
    void toString_validModuleMapping_correctString()
            throws InvalidUniversityException, InvalidModuleException, InvalidModuleMappingException {
        University partneUniversity = new University("Zhejiang University", "China");
        Module partnerUniversityModule = new Module("21121340", "Computer Networks", "2.5", partneUniversity);
        University nus = new University("NUS", "Singapore");
        Module nusModule = new Module("CS2105", "Intro to Computer Networks", "4", nus);
        ModuleMapping moduleMapping = new ModuleMapping(partnerUniversityModule, nusModule);

        assertEquals(
                "21121340 Computer Networks 2.5MCs in Zhejiang University | CS2105 Intro to Computer"
                        + " Networks 4MCs in NUS",
                moduleMapping.toString());
    }
}
