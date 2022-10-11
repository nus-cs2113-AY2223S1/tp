package seedu.duke.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class UserUniversityListTest {
    UserUniversityListManager testManager = new UserUniversityListManager();

    @Test
    void exists() {
        testManager.createList("UCLA");
        testManager.createList("UCB");
        assertEquals(true, testManager.foundKey("UCLA"));
    }


    @Test
    void createList_Ucla_correctDetails() {
        testManager.createList("UCLA");
        testManager.createList("UCB");
        assertEquals(2, testManager.getMyManager().size());
    }

    @Test
    void addModule_UclaModule_correctDetails() {
        testManager.createList("UCLA");
        UserModuleMapping mod = new UserModuleMapping("CS101", "Programming Intro",
                "CS1010", "Programming Methodology", "4", "4");
        testManager.addModule("UCLA", mod);
        assertEquals(1, testManager.getMyManager().get("UCLA").getMyModules().getModules().size());
        assertEquals("CS101", testManager.getMyManager().get("UCLA").getMyModules()
                .getModules().get(0).getPuCode());
        assertEquals("Programming Intro", testManager.getMyManager().get("UCLA").getMyModules()
                .getModules().get(0).getPuTitle());
        assertEquals("CS1010", testManager.getMyManager().get("UCLA").getMyModules()
                .getModules().get(0).getNusCode());
        assertEquals("Programming Methodology", testManager.getMyManager()
                .get("UCLA").getMyModules().getModules().get(0).getNusTitle());
        assertEquals("4", testManager.getMyManager().get("UCLA").getMyModules()
                .getModules().get(0).getNusCredit());
    }


    @Test
    void deleteModule_Ucla_correctDetails() {
        testManager.createList("UCLA");
        UserModuleMapping mod = new UserModuleMapping("CS101", "Programming Intro",
                "CS1010", "Programming Methodology", "4", "8");
        testManager.addModule("UCLA", mod);
        testManager.deleteModule("UCLA", "CS101");
        assertEquals(0, testManager.getMyManager().get("UCLA").getMyModules().getModules().size());
    }

    @Test
    void deleteModule_UclaWrongDetail_throwException() {
        testManager.createList("UCLA");
        UserModuleMapping mod = new UserModuleMapping("CS101", "Programming Intro",
                "CS1010", "Programming Methodology", "4", "8");
        testManager.addModule("UCLA", mod);
        assertThrows(NoSuchElementException.class,() -> testManager.deleteModule("UCLA", "CS00"));
        assertEquals(1, testManager.getMyManager().get("UCLA").getMyModules().getModules().size());
    }

    @Test
    void deleteList_Ucla_correctDetails() {
        testManager.createList("UCLA");
        assertEquals(1, testManager.getMyManager().size());
        testManager.deleteList("UCLA");
        assertEquals(0, testManager.getMyManager().size());
    }

    @Test
    void createList_duplicateInput_notUpdated() {
        testManager.createList("UCLA");
        testManager.createList("UCB");
        testManager.createList("UCB");
        assertEquals(2, testManager.getMyManager().size());
    }
    
    @Test
    void getList_NonexistentList_throwException() {
        assertThrows(NoSuchElementException.class,() -> testManager.getList("NUS"));
    }

    @Test
    void deleteList_NonexistentList_throwException() {
        assertThrows(NoSuchElementException.class,() -> testManager.deleteList("NTU"));
    }

    @Test
    void displayAll_NoInput_ProperOutput() {
        testManager.createList("UCLA");
        testManager.createList("UCB");
        UserModuleMapping mod = new UserModuleMapping("CS101", "Programming Intro",
                "CS1010", "Programming Methodology", "4", "8");
        testManager.addModule("UCLA", mod);
        UserModuleMapping mod2 = new UserModuleMapping("CS201", "Programming Intro II",
                "CS2030", "Programming Methodology II", "4", "8");
        testManager.addModule("UCB", mod2);
        testManager.displayAll();
    }
}
