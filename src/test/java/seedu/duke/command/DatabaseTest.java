package seedu.duke.command;

//@@author joshuan98

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidModuleMappingException;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.exceptions.UniversityNotFoundException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleMapping;
import seedu.duke.university.University;

public class DatabaseTest {
    @Test
    void addModuleMapping_validModuleMapping_correctString()
            throws InvalidUniversityException, InvalidModuleException, InvalidModuleMappingException {
        University partnerUniversity = new University("Zhejiang University", "China");
        Module partnerUniversityModule = new Module("21121340", "Computer Networks", "2.5", partnerUniversity);
        University nus = new University("NUS", "Singapore");
        Module nusModule = new Module("CS2105", "Intro to Computer Networks", "4", nus);
        ModuleMapping moduleMapping = new ModuleMapping(partnerUniversityModule, nusModule);

        Database.addModuleMapping(moduleMapping);

        assertEquals(
                "21121340 Computer Networks 2.5MCs in Zhejiang University | CS2105 Intro to Computer "
                        + "Networks 4MCs in NUS",
                Database.getModuleMappings().get(Database.getModuleMappings().size() - 1).toString());
    }

    @Test
    void addUniversity_validUniversity_correctString() throws InvalidUniversityException {
        University nus = new University("NUS", "Singapore");

        Database.addUniversity(nus);

        assertEquals("NUS", Database.getUniversities().get(0).toString());
    }

    @Test
    void findPuMapping_validModule_correctList() throws ModuleNotFoundException, UniversityNotFoundException {
        DatabaseStorage.loadDatabase();

        assertEquals(
                "C3130 Information Security 5MCs in Aalto University | CS2107 Introduction to Information "
                        + "Se 4MCs in NUS",
                Database.findPuMapping("C3130", "Aalto University").toString());
    }

    @Test
    void findPuMapping_invalidModule_throwsException() throws ModuleNotFoundException {
        DatabaseStorage.loadDatabase();

        assertThrows(UniversityNotFoundException.class, () -> Database.findPuMapping("CS2113", "ABCDEFG").toString());
    }

    @Test
    void findNusMapping_validModule_correctList() throws ModuleNotFoundException {
        DatabaseStorage.loadDatabase();

        assertEquals(
                "[COM162 Object Oriented Design and Programming with Java 15MCs in The University of "
                        + "Sheffield | CS2113 Software Engineering & Object- 4MCs in NUS, CS329E Elements of Software "
                        + "Engineering 3MCs in The University of Texas at Austin | CS2113 Software Engineering & "
                        + "Object- 4MCs in NUS, COM162 Object Oriented Design and Programming with Java 15MCs in The "
                        + "University of Sheffield | CS2113 Software Engineering & Object- 4MCs in NUS, CS329E "
                        + "Elements of Software Engineering 3MCs in The University of Texas at Austin | CS2113 "
                        + "Software Engineering & Object- 4MCs in NUS, COM162 Object Oriented Design and Programming "
                        + "with Java 15MCs in The University of Sheffield | CS2113 Software Engineering & Object- 4MCs "
                        + "in NUS, CS329E Elements of Software Engineering 3MCs in The University of Texas at "
                        + "Austin | CS2113 Software Engineering & Object- 4MCs in NUS, COM162 Object Oriented Design "
                        + "and Programming with Java 15MCs in The University of Sheffield | CS2113 Software "
                        + "Engineering & Object- 4MCs in NUS, CS329E Elements of Software Engineering 3MCs in The "
                        + "University of Texas at Austin | CS2113 Software Engineering & Object- 4MCs in NUS]",
                Database.findNusMapping("CS2113").toString());
    }

    @Test
    void findNusMapping_invalidModule_throwsException() throws ModuleNotFoundException {
        DatabaseStorage.loadDatabase();

        assertThrows(ModuleNotFoundException.class, () -> Database.findNusMapping("ABCD").toString());
    }

    @Test
    void findUniversityMapping_validUniversity_correctList() throws UniversityNotFoundException {
        DatabaseStorage.loadDatabase();

        assertEquals(
                "[C3130 Information Security 5MCs in Aalto University | CS2107 Introduction to Information "
                        + "Se 4MCs in NUS, C3150 Software Engineering 5MCs in Aalto University | CS2103 Software "
                        + "Engineering 4MCs in NUS, CS-C3130 Information Security 5MCs in Aalto University | CS2107 "
                        + "Introduction to Information Se 4MCs in NUS, CS--E4300 Network Security 5MCs in Aalto "
                        + "University | CS2107 Introduction to Information Se 4MCs in NUS, CS--E4320 Cryptography and "
                        + "Data Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in "
                        + "NUS, ELEC-E7120 Wireless Systems 5MCs in Aalto University | CS4222 Wireless Networking 4MCs "
                        + "in NUS, C3130 Information Security 5MCs in Aalto University | CS2107 Introduction to "
                        + "Information Se 4MCs in NUS, C3150 Software Engineering 5MCs in Aalto University | CS2103 "
                        + "Software Engineering 4MCs in NUS, CS-C3130 Information Security 5MCs in Aalto University | "
                        + "CS2107 Introduction to Information Se 4MCs in NUS, CS--E4300 Network Security 5MCs in Aalto "
                        + "University | CS2107 Introduction to Information Se 4MCs in NUS, CS--E4320 Cryptography and "
                        + "Data Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in NUS, "
                        + "ELEC-E7120 Wireless Systems 5MCs in Aalto University | CS4222 Wireless Networking 4MCs in "
                        + "NUS, C3130 Information Security 5MCs in Aalto University | CS2107 Introduction to "
                        + "Information Se 4MCs in NUS, C3150 Software Engineering 5MCs in Aalto University | CS2103 "
                        + "Software Engineering 4MCs in NUS, CS-C3130 Information Security 5MCs in Aalto University | "
                        + "CS2107 Introduction to Information Se 4MCs in NUS, CS--E4300 Network Security 5MCs in Aalto "
                        + "University | CS2107 Introduction to Information Se 4MCs in NUS, CS--E4320 Cryptography and "
                        + "Data Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in NUS, "
                        + "ELEC-E7120 Wireless Systems 5MCs in Aalto University | CS4222 Wireless Networking 4MCs in "
                        + "NUS, C3130 Information Security 5MCs in Aalto University | CS2107 Introduction to "
                        + "Information Se 4MCs in NUS, C3150 Software Engineering 5MCs in Aalto University | CS2103 "
                        + "Software Engineering 4MCs in NUS, CS-C3130 Information Security 5MCs in Aalto University | "
                        + "CS2107 Introduction to Information Se 4MCs in NUS, CS--E4300 Network Security 5MCs in "
                        + "Aalto University | CS2107 Introduction to Information Se 4MCs in NUS, CS--E4320 "
                        + "Cryptography and Data Security 5MCs in Aalto University | CS2107 Introduction to "
                        + "Information Se 4MCs in NUS, ELEC-E7120 Wireless Systems 5MCs in Aalto University | CS4222 "
                        + "Wireless Networking 4MCs in NUS, C3130 Information Security 5MCs in Aalto University | "
                        + "CS2107 Introduction to Information Se 4MCs in NUS, C3150 Software Engineering 5MCs in Aalto "
                        + "University | CS2103 Software Engineering 4MCs in NUS, CS-C3130 Information Security 5MCs in "
                        + "Aalto University | CS2107 Introduction to Information Se 4MCs in NUS, CS--E4300 Network "
                        + "Security 5MCs in Aalto University | CS2107 Introduction to Information Se 4MCs in NUS, "
                        + "CS--E4320 Cryptography and Data Security 5MCs in Aalto University | CS2107 Introduction to "
                        + "Information Se 4MCs in NUS, ELEC-E7120 Wireless Systems 5MCs in Aalto University | CS4222 "
                        + "Wireless Networking 4MCs in NUS]",
                Database.findUniversityMapping("Aalto University").toString());
    }

    @Test
    void findUniversityMapping_invalidUniversity_throwsException() throws UniversityNotFoundException {
        DatabaseStorage.loadDatabase();

        assertThrows(UniversityNotFoundException.class,
            () -> Database.findUniversityMapping("ABCD").toString());
    }
}
