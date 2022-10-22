package seedu.duke.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidModuleMappingException;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.university.University;

public class ModuleMappingTest {
    @Test
    void toString_validModuleMapping_correctString()
            throws InvalidUniversityException, InvalidModuleException, InvalidModuleMappingException {
        University partnerUniversity = new University("Zhejiang University", "China");
        Module partnerUniversityModule = new Module("21121340", "Computer Networks", "2.5", partnerUniversity);
        University nus = new University("NUS", "Singapore");
        Module nusModule = new Module("CS2105", "Intro to Computer Networks", "4", nus);
        ModuleMapping moduleMapping = new ModuleMapping(partnerUniversityModule, nusModule);

        assertEquals(
                "21121340 Computer Networks 2.5MCs in Zhejiang University | CS2105 Intro to Computer"
                        + " Networks 4MCs in NUS",
                moduleMapping.toString());
    }

    @Test
    void toString_invalidPartnerUniversityName_throwsInvalidUniversityException() {
        assertThrows(InvalidUniversityException.class, () -> new University("", "China"));
    }

    @Test
    void toString_invalidPartnerUniversityCountry_throwsInvalidUniversityException() {
        assertThrows(InvalidUniversityException.class, () -> new University("Zhejiang University", ""));
    }

    @Test
    void toString_invalidNusUniversityName_throwsInvalidUniversityException() {
        assertThrows(InvalidUniversityException.class, () -> new University("", "Singapore"));
    }

    @Test
    void toString_invalidNusUniversityCountry_throwsInvalidUniversityException() {
        assertThrows(InvalidUniversityException.class, () -> new University("NUS", ""));
    }

    @Test
    void toString_invalidPartnerUniversityModuleCode_throwsInvalidModuleException() throws InvalidUniversityException {
        University partnerUniversity = new University("Zhejiang University", "China");

        assertThrows(InvalidModuleException.class,
                () -> new Module("", "Computer Networks", "2.5", partnerUniversity));
    }

    @Test
    void toString_invalidPartnerUniversityModuleTitle_throwsInvalidModuleException() throws InvalidUniversityException {
        University partnerUniversity = new University("Zhejiang University", "China");

        assertThrows(InvalidModuleException.class,
                () -> new Module("21121340", "", "2.5", partnerUniversity));
    }

    @Test
    void toString_invalidPartnerUniversityModuleCredit_throwsInvalidModuleException()
            throws InvalidUniversityException {
        University partnerUniversity = new University("Zhejiang University", "China");

        assertThrows(InvalidModuleException.class,
                () -> new Module("21121340", "Computer Networks", "", partnerUniversity));
    }

    @Test
    void toString_invalidNusModuleCode_throwsInvalidModuleException() throws InvalidUniversityException {
        University nus = new University("NUS", "Singapore");

        assertThrows(InvalidModuleException.class,
                () -> new Module("", "Intro to Computer Networks", "4", nus));
    }

    @Test
    void toString_invalidNusModuleTitle_throwsInvalidModuleException() throws InvalidUniversityException {
        University nus = new University("NUS", "Singapore");

        assertThrows(InvalidModuleException.class,
                () -> new Module("CS2105", "", "4", nus));
    }

    @Test
    void toString_invalidNusModuleCredit_throwsInvalidModuleException() throws InvalidUniversityException {
        University nus = new University("NUS", "Singapore");

        assertThrows(InvalidModuleException.class,
                () -> new Module("CS2105", "Intro to Computer Networks", "", nus));
    }
}
