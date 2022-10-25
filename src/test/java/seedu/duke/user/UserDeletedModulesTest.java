package seedu.duke.user;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidUserCommandException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDeletedModulesTest {
    UserUniversityListManager testManager = new UserUniversityListManager();

    UserModuleMapping testModule1 = new UserModuleMapping("MET CS 248", "Discrete Mathematics", "CS1231",
            "Discrete Structures", "4", "3", "Boston University", "USA");
    UserModuleMapping testModule2 = new UserModuleMapping("CS103",
            "Introduction to Internet Technologies and Web Programming",
            "IT1001", "Introduction to Computing", "4", "3", "Boston University", "USA");
    UserModuleMapping testModule3 = new UserModuleMapping("CS201",
            "INTRODUCTION TO PROGRAMMING WITH PYTHON",
            "CS1010S", "Programming Methodology", "4", "3", "Boston University", "USA");
    UserModuleMapping testModule4 = new UserModuleMapping("CS412",
            "Full Stack Application Design and Development",
            "BT3103", "Application Systems Development", "4", "3", "Boston University", "USA");
    UserModuleMapping testModule5 = new UserModuleMapping("CS472",
            "Computer Architecture",
            "CS2100", "Computer Organisation", "4", "3", "Boston University", "USA");
    UserModuleMapping testModule6 = new UserModuleMapping("CS473",
            "Introduction to Software Engineering",
            "CS2103", "Software Engineering", "4", "3", "Boston University", "USA");

    @Test
    public void testOneDeletedModule() throws InvalidUserCommandException {
        testManager.createList("Boston University");
        testManager.addModule("Boston University", testModule1);
        testManager.deleteModule("Boston University", "MET CS 248");
        assertEquals(UserDeletedModules.getDeletedModules().size(), 1);
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getPuCode(), "MET CS 248");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getPuTitle(), "Discrete Mathematics");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getNusCode(), "CS1231");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getNusTitle(), "Discrete Structures");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getNusCredit(), "4");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getPuCredit(), "3");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getPuName(), "Boston University");
    }

    @Test
    public void testTwoDeletedModules() throws InvalidUserCommandException {
        testManager.createList("Boston University");
        testManager.addModule("Boston University", testModule1);
        testManager.addModule("Boston University", testModule2);
        testManager.deleteModule("Boston University", "MET CS 248");
        testManager.deleteModule("Boston University", "CS103");
        assertEquals(UserDeletedModules.getDeletedModules().size(), 2);
        assertEquals(UserDeletedModules.getDeletedModules().getLast().getPuCode(), "MET CS 248");
        assertEquals(UserDeletedModules.getDeletedModules().getLast().getPuTitle(), "Discrete Mathematics");
        assertEquals(UserDeletedModules.getDeletedModules().getLast().getNusCode(), "CS1231");
        assertEquals(UserDeletedModules.getDeletedModules().getLast().getNusTitle(), "Discrete Structures");
        assertEquals(UserDeletedModules.getDeletedModules().getLast().getNusCredit(), "4");
        assertEquals(UserDeletedModules.getDeletedModules().getLast().getPuCredit(), "3");
        assertEquals(UserDeletedModules.getDeletedModules().getLast().getPuName(), "Boston University");
    }

    @Test
        public void testMoreThanFiveDeletedModules() throws InvalidUserCommandException {
        testManager.createList("Boston University");
        testManager.addModule("Boston University", testModule1);
        testManager.addModule("Boston University", testModule2);
        testManager.addModule("Boston University", testModule3);
        testManager.addModule("Boston University", testModule4);
        testManager.addModule("Boston University", testModule5);
        testManager.addModule("Boston University", testModule6);
        testManager.deleteModule("Boston University", "MET CS 248");
        testManager.deleteModule("Boston University", "CS103");
        testManager.deleteModule("Boston University", "CS201");
        testManager.deleteModule("Boston University", "CS412");
        testManager.deleteModule("Boston University", "CS472");
        testManager.deleteModule("Boston University", "CS473");
        assertEquals(UserDeletedModules.getDeletedModules().size(), 5);
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getPuCode(), "CS473");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getPuTitle(), "Introduction to Software Engineering");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getNusCode(), "CS2103");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getNusTitle(), "Software Engineering");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getNusCredit(), "4");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getPuCredit(), "3");
        assertEquals(UserDeletedModules.getDeletedModules().getFirst().getPuName(), "Boston University");
    }
}
