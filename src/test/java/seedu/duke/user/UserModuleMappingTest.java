package seedu.duke.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidUserCommandException;

public class UserModuleMappingTest {

    UserModuleMappingList myModules = new UserModuleMappingList();

    @Test
    void addModule_cs2040_correctDetails() {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "UCB", "USA");
        myModules.addModule(mod);
        assertEquals(1, myModules.getModules().size());
        assertEquals(true, myModules.findModuleByCode(mod));
        assertEquals("CS24", myModules.getModules().get(0).getPuCode());
        assertEquals("CS2040", myModules.getModules().get(0).getNusCode());
        assertEquals("Data Structures", myModules.getModules().get(0).getPuTitle());
        assertEquals("Data Structures & Algorithms", myModules.getModules().get(0).getNusTitle());
        assertEquals("4", myModules.getModules().get(0).getNusCredit());
    }

    @Test
    void addModule_duplicateEntry_notAdded() {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "LSA","Africa");
        myModules.addModule(mod);
        assertEquals(1, myModules.getModules().size());
    }

    @Test
    void deleteModule_IndexOutOfBoundEntry_expectException() {
        assertThrows(IndexOutOfBoundsException.class, () -> myModules.deleteModule(myModules.getModules().size() + 1));
        assertThrows(IndexOutOfBoundsException.class,() -> myModules.deleteModule(-1));
    }

    @Test
    void deleteModuleByPuCode_NoSuchEntry_expectException() {
        assertThrows(InvalidUserCommandException.class, () -> myModules.deleteModuleByPuCode("ABIDE"));
    }
}
