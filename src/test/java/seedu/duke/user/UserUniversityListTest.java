package seedu.duke.user;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidUserCommandException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserUniversityListTest {
    UserUniversityListManager testManager = new UserUniversityListManager();

    @Test
    void exists() {
        testManager.createList("UCLA");
        testManager.createList("UCB");
        assertEquals(true, testManager.foundKeyAll("UCLA"));
    }


    @Test
    void createList_Ucla_correctDetails() {
        testManager.createList("UCLA");
        testManager.createList("UCB");
        assertEquals(2, testManager.getMyManager().size());
    }

    @Test
    void addModule_UclaModule_correctDetails() throws InvalidUserCommandException {
        testManager.createList("UCLA");
        UserModuleMapping mod = new UserModuleMapping("CS101", "Programming Intro", "CS1010",
                "Programming Methodology", "4", "4", "UCLA", "USA");
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
    void deleteModule_Ucla_correctDetails() throws InvalidUserCommandException {
        testManager.createList("UCLA");
        UserModuleMapping mod = new UserModuleMapping("CS101", "Programming Intro", "CS1010",
                "Programming Methodology", "4", "4", "UCLA", "USA");
        testManager.addModule("UCLA", mod);
        testManager.deleteModule("UCLA", "CS101");
        assertEquals(0, testManager.getMyManager().get("UCLA").getMyModules().getModules().size());
    }

    @Test
    void deleteModule_UclaWrongDetail_throwException() throws InvalidUserCommandException {
        testManager.createList("UCLA");
        UserModuleMapping mod = new UserModuleMapping("CS101", "Programming Intro", "CS1010",
                "Programming Methodology", "4", "4", "UCLA", "USA");
        testManager.addModule("UCLA", mod);
        assertThrows(InvalidUserCommandException.class,() -> testManager.deleteModule("UCLA", "CS00"));
        assertEquals(1, testManager.getMyManager().get("UCLA").getMyModules().getModules().size());
    }

    @Test
    void deleteList_Ucla_correctDetails() throws InvalidUserCommandException {
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
        assertThrows(InvalidUserCommandException.class,() -> testManager.getList("NUS"));
    }

    @Test
    void deleteList_NonexistentList_throwException() {
        assertThrows(InvalidUserCommandException.class,() -> testManager.deleteList("NTU"));
    }

    @Test
    void displayAll_NoInput_ProperOutput() throws InvalidUserCommandException {
        testManager.createList("UCLA");
        testManager.createList("UCB");
        UserModuleMapping mod = new UserModuleMapping("CS101", "Programming Intro", "CS1010",
                "Programming Methodology", "4", "4", "UCLA", "USA");
        testManager.addModule("UCLA", mod);
        UserModuleMapping mod2 = new UserModuleMapping("CS201", "Programming II", "CS2030",
                "Programming Methodology II", "4", "8", "UCB", "USA");
        testManager.addModule("UCB", mod2);
        testManager.displayAll();
    }

    @Test
    void addFavourite_CorrectInput_CorrectOutput() throws InvalidUserCommandException {
        testManager.createList("UCLA");
        testManager.createList("UCB");
        testManager.addFavourite("UCLA");
        testManager.addFavourite("UCB");
        assertEquals(2, testManager.getMyManager().size());
        for (Map.Entry<String, UserUniversityList> entry : testManager.getMyManager().entrySet()) {
            UserUniversityList uni = entry.getValue();
            assertTrue(uni.isFavourite());
        }
    }

    @Test
    void addFavourite_NoLists_throwException() {
        assertThrows(InvalidUserCommandException.class, () -> testManager.addFavourite("UCLA"));
    }

    @Test
    void addFavourite_duplicate_throwException() throws InvalidUserCommandException {
        testManager.createList("UCLA");
        testManager.addFavourite("UCLA");
        assertThrows(InvalidUserCommandException.class, () -> testManager.addFavourite("UCLA"));
    }

    @Test
    void removeFavourite_CorrectInput_CorrectOutput() throws InvalidUserCommandException {
        testManager.createList("UCLA");
        testManager.addFavourite("UCLA");
        assertTrue(testManager.getMyManager().get("UCLA").isFavourite());
        testManager.deleteFavourite("UCLA");
        assertFalse(testManager.getMyManager().get("UCLA").isFavourite());
    }

    @Test
    void removeFavourite_NoSuchLists_throwException() throws InvalidUserCommandException {
        assertThrows(InvalidUserCommandException.class, () -> testManager.deleteFavourite("UCLA"));
        testManager.createList("UCLA");
        assertThrows(InvalidUserCommandException.class, () -> testManager.deleteFavourite("UCLA"));
        testManager.addFavourite("UCLA");
        testManager.deleteFavourite("UCLA");
        assertThrows(InvalidUserCommandException.class, () -> testManager.deleteFavourite("UCLA"));
    }

    @Test
    void addModule_Ucla_correctUpdatesOnFavourites() throws InvalidUserCommandException {
        testManager.createList("UCLA");
        testManager.addFavourite("UCLA");
        HashMap<String, UserUniversityList> myManager = testManager.getMyManager();
        assertEquals(0, testManager.getMyManager().get("UCLA").getMyModules().getModules().size());
        assertEquals(0, testManager.getMyFavourites(myManager).get("UCLA").getMyModules().getModules().size());
        UserModuleMapping mod = new UserModuleMapping("CS101", "Programming Intro", "CS1010",
                "Programming Methodology", "4", "4", "UCLA", "USA");
        testManager.addModule("UCLA", mod);
        assertEquals(1, testManager.getMyManager().get("UCLA").getMyModules().getModules().size());
        assertEquals(1, testManager.getMyFavourites(myManager).get("UCLA").getMyModules().getModules().size());
    }

    @Test
    void deleteModule_Ucla_correctUpdatesOnFavourites() throws InvalidUserCommandException {
        testManager.createList("UCLA");
        testManager.addFavourite("UCLA");
        HashMap<String, UserUniversityList> myManager = testManager.getMyManager();
        UserModuleMapping mod = new UserModuleMapping("CS101", "Programming Intro", "CS1010",
                "Programming Methodology", "4", "4", "UCLA", "USA");
        UserModuleMapping mod2 = new UserModuleMapping("CS201", "Programming Intro II", "CS2030",
                "Programming Methodology II ", "4", "4", "UCLA", "USA");
        testManager.addModule("UCLA", mod);
        testManager.addModule("UCLA", mod2);
        assertEquals(2, testManager.getMyManager().get("UCLA").getMyModules().getModules().size());
        assertEquals(2, testManager.getMyFavourites(myManager).get("UCLA").getMyModules().getModules().size());
        testManager.deleteModule("UCLA", "CS201");
        assertEquals(1, testManager.getMyManager().get("UCLA").getMyModules().getModules().size());
        assertEquals(1, testManager.getMyFavourites(myManager).get("UCLA").getMyModules().getModules().size());
        testManager.deleteFavourite("UCLA");
        testManager.addModule("UCLA", mod2);
        assertEquals(2, testManager.getMyManager().get("UCLA").getMyModules().getModules().size());
    }

    @Test
    void deleteList_Ucla_correctUpdatesOnFavourites() throws InvalidUserCommandException {
        testManager.createList("UCLA");
        testManager.addFavourite("UCLA");
        testManager.deleteList("UCLA");
        HashMap<String, UserUniversityList> myManager = testManager.getMyManager();
        assertEquals(0, testManager.getMyFavourites(myManager).size());
    }
}
