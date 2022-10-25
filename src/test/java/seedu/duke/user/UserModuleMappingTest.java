package seedu.duke.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidUserCommandException;

public class UserModuleMappingTest {

    UserModuleMappingList myModules = new UserModuleMappingList();

    @Test
    void toString_cs2040_correctDetails() {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "UCB", "USA");
        myModules.addModule(mod);
        assertEquals("CS2040 Data Structures & Algorithms | CS24 Data Structures 4 MCs",
                myModules.getModules().get(0).toString());
    }

    @Test
    void addModule_cs2040withUpdates_correctDetails() {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "UCB", "USA");
        myModules.addModule(mod);
        mod.setComment("new comment");
        mod.setNusCode("CS2040C");
        mod.setNusCredit("8");
        mod.setNusTitle("Data Structures by Halim");
        mod.setPuCode("CS2442");
        mod.setPuTitle("Data Structures Advanced");
        assertEquals(1, myModules.getModules().size());
        assertEquals(true, myModules.findModuleByCode(mod));
        assertEquals("CS2442", myModules.getModules().get(0).getPuCode());
        assertEquals("CS2040C", myModules.getModules().get(0).getNusCode());
        assertEquals("Data Structures Advanced", myModules.getModules().get(0).getPuTitle());
        assertEquals("Data Structures by Halim", myModules.getModules().get(0).getNusTitle());
        assertEquals("8", myModules.getModules().get(0).getNusCredit());
        assertEquals("new comment", myModules.getModules().get(0).getComment());
    }

    @Test
    void addModuleFromDatabase_cs2040_correctDetails() {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "UCB", "USA");
        myModules.addModule(mod, false);
        assertEquals(1, myModules.getModules().size());
        assertEquals(true, myModules.findModuleByCode(mod));
        assertEquals("CS24", myModules.getModules().get(0).getPuCode());
        assertEquals("CS2040", myModules.getModules().get(0).getNusCode());
        assertEquals("Data Structures", myModules.getModules().get(0).getPuTitle());
        assertEquals("Data Structures & Algorithms", myModules.getModules().get(0).getNusTitle());
        assertEquals("4", myModules.getModules().get(0).getNusCredit());
    }

    @Test
    void addModule_null_expectException() {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "UCB", "");
        assertThrows(NullPointerException.class, () -> myModules.addModule(mod));
    }

    @Test
    void addModule_duplicateEntry_notAdded() {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "LSA","Africa");
        myModules.addModule(mod);
        myModules.addModule(mod);
        myModules.addModule(mod);
        assertEquals(1, myModules.getModules().size());
    }

    @Test
    void getModuleByPuCode_correctEntry_correctValueReturned() throws InvalidUserCommandException {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "LSA","Africa");
        myModules.addModule(mod);
        assertEquals(mod, myModules.getModuleByPuCode("CS24"));
        assertThrows(InvalidUserCommandException.class, () -> myModules.getModuleByPuCode("0"));
    }

    @Test
    void findModule_Modules_correctFoundResult() {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "UCB", "USA");
        myModules.addModule(mod);
        assertEquals(true, myModules.findModuleByCode(mod));
        mod.setNusCode("000");
        UserModuleMapping mod2 = new UserModuleMapping("0", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "UCB", "USA");
        assertEquals(false, myModules.findModuleByCode(mod2));
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

    @Test
    void updateModule_Ucla_correctDetails() throws InvalidUserCommandException {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "UCB", "USA");
        myModules.addModule(mod);
        myModules.updateModule("CS24", "CS2040", "new_update");
        assertEquals(myModules.getModuleByPuCode("CS24").getComment(), "new_update");
    }

    @Test
    void updateModule_NoSuchModule_noUpdates() throws InvalidUserCommandException {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "UCB", "USA");
        myModules.addModule(mod);
        myModules.updateModule("-", "-", "-");
        assertEquals(myModules.getModuleByPuCode("CS24").getComment(), "default");
    }

    @Test
    void findModuleByTitle_Ucla_true() {
        UserModuleMapping mod = new UserModuleMapping("CS24", "Data Structures", "CS2040",
                "Data Structures & Algorithms", "4", "4", "UCB", "USA");
        myModules.addModule(mod);
        assertEquals(true, myModules.findModuleByTitle("Data Structures"));
    }

    @Test
    void findModuleByTitle_NoSuchModule_false() {
        assertEquals(false, myModules.findModuleByTitle("testing"));
    }
}
