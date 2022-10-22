package seedu.duke.userstorage;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidUserCommandException;
import seedu.duke.parser.UserStorageParser;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserUniversityList;
import seedu.duke.user.UserUniversityListManager;

import static org.junit.jupiter.api.Assertions.*;

public class UserStorageTest {
    UserUniversityListManager testManager = new UserUniversityListManager();
    UserModuleMapping testModule1 = new UserModuleMapping("MET CS 248", "Discrete Mathematics", "CS1231",
            "Discrete Structures", "4", "3", "Boston University", "USA");
    UserModuleMapping testModule2 = new UserModuleMapping("CS103",
            "Introduction to Internet Technologies and Web Programming",
            "IT1001", "Introduction to Computing", "4", "3", "Boston University", "USA");
    UserModuleMapping testModule3 = new UserModuleMapping("CSE412", "Database Management",
            "CS2102", "Database Systems", "4", "3","Arizona State University",
            "USA");
    UserModuleMapping testModule4 = new UserModuleMapping("CSE450", "Design and Analysis of Algorithms",
            "CS3230", "Design & Analysis of Algorithm", "4", "3",
            "Arizona State University", "USA");

    @Test
    public void testFileContentStringConversion_OneUniWithOneModule() throws InvalidUserCommandException {
        testManager.createList("Boston University");
        testManager.addModule("Boston University", testModule1);
        assertEquals("Boston University%\n" + "null%\n" + "F%\n"
                        + "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4%\n",
                UserStorageParser.convertUniversityListIntoFileContent(testManager));
    }

    @Test
    public void testFileContentStringConversion_OneUniWithTwoModules() throws InvalidUserCommandException {
        testManager.createList("Boston University");
        testManager.addModule("Boston University", testModule1);
        testManager.addModule("Boston University", testModule2);
        assertEquals("Boston University%\n" + "null%\n" + "F%\n"
                        + "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4%\n"
                        + "CS103;Introduction to Internet Technologies and Web Programming;3;"
                        + "IT1001;Introduction to Computing;4%\n",
                UserStorageParser.convertUniversityListIntoFileContent(testManager));
    }

    @Test
    public void testFileContentStringConversion_TwoUniWithTwoModulesEach() throws InvalidUserCommandException {
        testManager.createList("Boston University");
        testManager.createList("Arizona State University");
        testManager.addModule("Boston University", testModule1);
        testManager.addModule("Boston University", testModule2);
        testManager.addModule("Arizona State University", testModule3);
        testManager.addModule("Arizona State University", testModule4);
        assertEquals("Boston University%\n" + "null%\n" + "F%\n"
                        + "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4%\n"
                        + "CS103;Introduction to Internet Technologies and Web Programming;3;"
                        + "IT1001;Introduction to Computing;4%\n"
                        + "/Arizona State University%\n" + "null%\n" + "F%\n"
                        + "CSE412;Database Management;3;CS2102;Database Systems;4%\n"
                        + "CSE450;Design and Analysis of Algorithms;3;CS3230;Design & Analysis of Algorithm;4%\n",
                UserStorageParser.convertUniversityListIntoFileContent(testManager));
    }

    @Test
    public void testFileContentStringConversion_OneEmptyUni() throws InvalidUserCommandException {
        testManager.createList("Boston University");
        assertEquals("Boston University%\n" + "null%\n" + "F%\n",
                UserStorageParser.convertUniversityListIntoFileContent(testManager));
    }

    @Test
    public void testFileContentStringConversion_TwoEmptyUnis() throws InvalidUserCommandException {
        testManager.createList("Boston University");
        testManager.createList("Arizona State University");
        assertEquals("Boston University%\n" + "null%\n" + "F%\n"
                        + "/Arizona State University%\n" + "null%\n" + "F%\n",
                UserStorageParser.convertUniversityListIntoFileContent(testManager));
    }
    
    @Test
    public void testUserUniversityListManagerConversion_OneUniWithOneModule_NotFavourite() {
        String fileContent = "Boston University%" + "null%" + "F%"
                +  "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4%";
        testManager = new UserUniversityListManager(fileContent);
        UserUniversityList testUniversityList = testManager.getMyManager().get("Boston University");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuCode(), "MET CS 248");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuTitle(), "Discrete Mathematics");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuCredit(), "3");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCode(), "CS1231");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusTitle(), "Discrete Structures");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCredit(), "4");
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
    }

    @Test
    public void testUserUniversityListManagerConversion_OneUniWithOneModule_IsFavourite() {
        String fileContent = "Boston University%" + "null%" + "T%"
                +  "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4%";
        testManager = new UserUniversityListManager(fileContent);
        UserUniversityList testUniversityList = testManager.getMyManager().get("Boston University");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuCode(), "MET CS 248");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuTitle(), "Discrete Mathematics");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuCredit(), "3");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCode(), "CS1231");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusTitle(), "Discrete Structures");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCredit(), "4");
        assertTrue(testManager.getMyManager().get("Boston University").isFavourite());
    }

    @Test
    public void testUserUniversityListManagerConversion_OneUniWithTwoModules() {
        String fileContent = "Boston University%" + "null%" + "F%"
                                + "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4%"
                                + "CS103;Introduction to Internet Technologies and Web Programming;3;"
                                + "IT1001;Introduction to Computing;4%";
        testManager = new UserUniversityListManager(fileContent);
        UserUniversityList testUniversityList = testManager.getMyManager().get("Boston University");
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuCode(), "MET CS 248");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuTitle(), "Discrete Mathematics");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuCredit(), "3");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCode(), "CS1231");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusTitle(), "Discrete Structures");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCredit(), "4");
        assertEquals(testUniversityList.getMyModules().getModules().get(1).getPuCode(), "CS103");
        assertEquals(testUniversityList.getMyModules().getModules().get(1).getPuTitle(),
                "Introduction to Internet Technologies and Web Programming");
        assertEquals(testUniversityList.getMyModules().getModules().get(1).getPuCredit(), "3");
        assertEquals(testUniversityList.getMyModules().getModules().get(1).getNusCode(), "IT1001");
        assertEquals(testUniversityList.getMyModules().getModules().get(1).getNusTitle(), "Introduction to Computing");
        assertEquals(testUniversityList.getMyModules().getModules().get(1).getNusCredit(), "4");
    }

    @Test
    public void testUserUniversityListManagerConversion_TwoUnisWithTwoModulesEach() {
        String fileContent = "Boston University%" + "null%" + "F%"
                + "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4%"
                + "CS103;Introduction to Internet Technologies and Web Programming;3;"
                + "IT1001;Introduction to Computing;4%"
                + "/Arizona State University%" + "null%" + "F%"
                + "CSE412;Database Management;3;CS2102;Database Systems;4%"
                + "CSE450;Design and Analysis of Algorithms;3;CS3230;Design & Analysis of Algorithm;4%";
        testManager = new UserUniversityListManager(fileContent);
        UserUniversityList testUniversityListBoston = testManager.getMyManager().get("Boston University");
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getPuCode(), "MET CS 248");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getPuTitle(), "Discrete Mathematics");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getPuCredit(), "3");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getNusCode(), "CS1231");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getNusTitle(), "Discrete Structures");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getNusCredit(), "4");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getPuCode(), "CS103");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getPuTitle(),
                "Introduction to Internet Technologies and Web Programming");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getPuCredit(), "3");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getNusCode(), "IT1001");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getNusTitle(),
                "Introduction to Computing");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getNusCredit(), "4");
        UserUniversityList testUniversityListArizona = testManager.getMyManager().get("Arizona State University");
        assertFalse(testManager.getMyManager().get("Arizona State University").isFavourite());
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getPuCode(), "CSE412");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getPuTitle(), "Database Management");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getPuCredit(), "3");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getNusCode(), "CS2102");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getNusTitle(), "Database Systems");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getNusCredit(), "4");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getPuCode(), "CSE450");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getPuTitle(),
                "Design and Analysis of Algorithms");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getPuCredit(), "3");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getNusCode(), "CS3230");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getNusTitle(),
                "Design & Analysis of Algorithm");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getNusCredit(), "4");
    }

    @Test
    public void testUserUniversityListManagerConversion_OneEmptyUni() {
        String fileContent = "Boston University%" + "null%" + "F%";
        testManager = new UserUniversityListManager(fileContent);
        UserUniversityList testUniversityList = testManager.getMyManager().get("Boston University");
        assertTrue(testUniversityList.getMyModules().getModules().size() == 0);
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
    }

    @Test
    public void testUserUniversityListManagerConversion_TwoEmptyUnis() {
        String fileContent = "Boston University%" + "null%" + "F%"
                            + "/Arizona State University%" + "null%" + "F%";
        testManager = new UserUniversityListManager(fileContent);
        UserUniversityList testUniversityList1 = testManager.getMyManager().get("Boston University");
        UserUniversityList testUniversityList2 = testManager.getMyManager().get("Arizona State University");
        assertTrue(testUniversityList1.getMyModules().getModules().size() == 0);
        assertTrue(testUniversityList2.getMyModules().getModules().size() == 0);
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
        assertFalse(testManager.getMyManager().get("Arizona State University").isFavourite());
    }
}