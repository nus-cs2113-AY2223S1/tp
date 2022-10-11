package seedu.duke.userstorage;

import org.junit.jupiter.api.Test;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserUniversityList;
import seedu.duke.user.UserUniversityListManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStorageTest {
    UserUniversityListManager testManager = new UserUniversityListManager();

    @Test
    public void testFileContentStringConversion() {
        testManager.createList("Boston University");
        UserModuleMapping testModule = new UserModuleMapping("MET CS 248", "Discrete Mathematics", "CS1231",
                "Discrete Structures", "4", "4", "BU", "USA");
        testManager.addModule("Boston University", testModule);
        assertEquals("Boston University%\n"
                        + "MET CS 248;Discrete Mathematics;CS1231;Discrete Structures;4%\n",
                UserStorageParser.convertUniversityListIntoFileContent(testManager));
    }
    
    @Test
    public void testUserUniversityListManagerConversion() {
        String fileContent = "Boston University%" + "MET CS 248;Discrete Mathematics;CS1231;Discrete Structures;4%";
        testManager = new UserUniversityListManager(fileContent);
        UserUniversityList testUniversityList = testManager.getMyManager().get("Boston University");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuCode(), "MET CS 248");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuTitle(), "Discrete Mathematics");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCode(), "CS1231");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusTitle(), "Discrete Structures");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCredit(), "4");
    }
}