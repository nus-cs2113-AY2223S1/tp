package seedu.duke.userstorage;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Database;
import seedu.duke.command.DatabaseStorage;
import seedu.duke.exceptions.InvalidUserCommandException;
import seedu.duke.exceptions.InvalidUserStorageFileException;
import seedu.duke.parser.UserStorageParser;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserUniversityList;
import seedu.duke.user.UserUniversityListManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserUniStorageTest {
    private static final String filePath = "data/uni_info.txt";
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

    private static void clearStorageFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }

    private static String getFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String fileContent = "";
        while (s.hasNext()) {
            fileContent += s.nextLine() + "\n";
        }
        s.close();
        return fileContent;
    }

    @Test
    public void testInvalidUserStorageFileExceptionThrown_InvalidUniFormat() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%" + "null%";
        assertThrows(InvalidUserStorageFileException.class,
            () -> UserStorageParser.convertFileContentIntoUniversityList(fileContent));
    }

    @Test
    public void testInvalidUserStorageFileExceptionThrown_InvalidFavouritesFormat() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%" + "null%" + "S%"
                +  "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4%";
        assertThrows(InvalidUserStorageFileException.class,
            () -> UserStorageParser.convertFileContentIntoUniversityList(fileContent));
    }

    @Test
    public void testInvalidUserStorageFileExceptionThrown_InvalidModulesFormat() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%" + "null%" + "T%"
                +  "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures%";
        assertThrows(InvalidUserStorageFileException.class,
            () -> UserStorageParser.convertFileContentIntoUniversityList(fileContent));
    }

    @Test
    public void testLoadingFile_OneUniWithOneModule_NotFavourite() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%" + "null%" + "F%"
                +  "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4;default%";
        UserStorage.saveFile(fileContent, true);
        DatabaseStorage.loadDatabase();
        testManager = UserStorageParser.getSavedLists();
        UserUniversityList testUniversityList = testManager.getMyManager().get("Boston University");
        assertEquals("MET CS 248", testUniversityList.getMyModules().getModules().get(0).getPuCode());
        assertEquals("Discrete Mathematics", testUniversityList.getMyModules().getModules().get(0).getPuTitle());
        assertEquals("3", testUniversityList.getMyModules().getModules().get(0).getPuCredit());
        assertEquals("CS1231", testUniversityList.getMyModules().getModules().get(0).getNusCode());
        assertEquals("Discrete Structures", testUniversityList.getMyModules().getModules().get(0).getNusTitle());
        assertEquals("4", testUniversityList.getMyModules().getModules().get(0).getNusCredit());
        assertEquals("default", testUniversityList.getMyModules().getModules().get(0).getComment());
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
        Database.clearDatabase();
    }

    @Test
    public void testLoadingFile_OneUniWithOneModule_IsFavourite() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%" + "null%" + "T%"
                +  "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4;default%";
        UserStorage.saveFile(fileContent, true);
        DatabaseStorage.loadDatabase();
        testManager = UserStorageParser.getSavedLists();
        UserUniversityList testUniversityList = testManager.getMyManager().get("Boston University");
        assertEquals("MET CS 248", testUniversityList.getMyModules().getModules().get(0).getPuCode());
        assertEquals("Discrete Mathematics", testUniversityList.getMyModules().getModules().get(0).getPuTitle());
        assertEquals("3", testUniversityList.getMyModules().getModules().get(0).getPuCredit());
        assertEquals("CS1231", testUniversityList.getMyModules().getModules().get(0).getNusCode());
        assertEquals("Discrete Structures", testUniversityList.getMyModules().getModules().get(0).getNusTitle());
        assertEquals("4", testUniversityList.getMyModules().getModules().get(0).getNusCredit());
        assertEquals("default", testUniversityList.getMyModules().getModules().get(0).getComment());
        assertTrue(testManager.getMyManager().get("Boston University").isFavourite());
        Database.clearDatabase();
    }

    @Test
    public void testLoadingFile_TwoUnisWithTwoModulesEach() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%" + "null%" + "F%"
                + "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4;default%"
                + "CS103;Introduction to Internet Technologies and Web Programming;3;"
                + "IT1001;Introduction to Computing;4;default%"
                + "/Arizona State University%" + "null%" + "F%"
                + "CSE412;Database Management;3;CS2102;Database Systems;4;default%"
                + "CSE450;Design and Analysis of Algorithms;3;CS3230;Design & Analysis of Algorithm;4;default%";
        DatabaseStorage.loadDatabase();
        UserStorage.saveFile(fileContent, true);
        testManager = UserStorageParser.getSavedLists();
        UserUniversityList testUniversityListBoston = testManager.getMyManager().get("Boston University");
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
        assertEquals("MET CS 248", testUniversityListBoston.getMyModules().getModules().get(0).getPuCode());
        assertEquals("Discrete Mathematics", testUniversityListBoston.getMyModules().getModules().get(0).getPuTitle());
        assertEquals("3", testUniversityListBoston.getMyModules().getModules().get(0).getPuCredit());
        assertEquals("CS1231", testUniversityListBoston.getMyModules().getModules().get(0).getNusCode());
        assertEquals("Discrete Structures", testUniversityListBoston.getMyModules().getModules().get(0).getNusTitle());
        assertEquals("4", testUniversityListBoston.getMyModules().getModules().get(0).getNusCredit());
        assertEquals("default", testUniversityListBoston.getMyModules().getModules().get(0).getComment());
        assertEquals("CS103", testUniversityListBoston.getMyModules().getModules().get(1).getPuCode());
        assertEquals("Introduction to Internet Technologies and Web Programming",
                testUniversityListBoston.getMyModules().getModules().get(1).getPuTitle());
        assertEquals("3", testUniversityListBoston.getMyModules().getModules().get(1).getPuCredit());
        assertEquals("IT1001", testUniversityListBoston.getMyModules().getModules().get(1).getNusCode());
        assertEquals("Introduction to Computing",
                testUniversityListBoston.getMyModules().getModules().get(1).getNusTitle());
        assertEquals("4", testUniversityListBoston.getMyModules().getModules().get(1).getNusCredit());
        assertEquals("default", testUniversityListBoston.getMyModules().getModules().get(1).getComment());
        UserUniversityList testUniversityListArizona = testManager.getMyManager().get("Arizona State University");
        assertFalse(testManager.getMyManager().get("Arizona State University").isFavourite());
        assertEquals("CSE412", testUniversityListArizona.getMyModules().getModules().get(0).getPuCode());
        assertEquals("Database Management", testUniversityListArizona.getMyModules().getModules().get(0).getPuTitle());
        assertEquals("3", testUniversityListArizona.getMyModules().getModules().get(0).getPuCredit());
        assertEquals("CS2102", testUniversityListArizona.getMyModules().getModules().get(0).getNusCode());
        assertEquals("Database Systems", testUniversityListArizona.getMyModules().getModules().get(0).getNusTitle());
        assertEquals("4", testUniversityListArizona.getMyModules().getModules().get(0).getNusCredit());
        assertEquals("default", testUniversityListArizona.getMyModules().getModules().get(0).getComment());
        assertEquals("CSE450", testUniversityListArizona.getMyModules().getModules().get(1).getPuCode());
        assertEquals("Design and Analysis of Algorithms",
                testUniversityListArizona.getMyModules().getModules().get(1).getPuTitle());
        assertEquals("3", testUniversityListArizona.getMyModules().getModules().get(1).getPuCredit());
        assertEquals("CS3230", testUniversityListArizona.getMyModules().getModules().get(1).getNusCode());
        assertEquals("Design & Analysis of Algorithm",
                testUniversityListArizona.getMyModules().getModules().get(1).getNusTitle());
        assertEquals("4", testUniversityListArizona.getMyModules().getModules().get(1).getNusCredit());
        assertEquals("default", testUniversityListArizona.getMyModules().getModules().get(1).getComment());
        Database.clearDatabase();
    }

    @Test
    public void testLoadingFile_TwoEmptyUnis_OneFavouriteOneNotFavourite() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%" + "null%" + "T%"
                            + "/Arizona State University%" + "null%" + "F%";
        UserStorage.saveFile(fileContent, true);
        DatabaseStorage.loadDatabase();
        testManager = UserStorageParser.getSavedLists();
        UserUniversityList testUniversityList1 = testManager.getMyManager().get("Boston University");
        UserUniversityList testUniversityList2 = testManager.getMyManager().get("Arizona State University");
        assertTrue(testUniversityList1.getMyModules().getModules().size() == 0);
        assertTrue(testUniversityList2.getMyModules().getModules().size() == 0);
        assertTrue(testManager.getMyManager().get("Boston University").isFavourite());
        assertFalse(testManager.getMyManager().get("Arizona State University").isFavourite());
        Database.clearDatabase();
    }

    @Test
    public void testSavingFile_OneUniWithOneModule() throws InvalidUserCommandException, IOException {
        clearStorageFile(filePath);
        testManager.createList("Boston University");
        testManager.addModule("Boston University", testModule1);
        UserStorageParser.storeCreatedLists(testManager);
        String fileContent = getFileContents(filePath);
        assertEquals("Boston University%\n" + "null%\n" + "F%\n"
                + "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4;default%\n", fileContent);
    }

    @Test
    public void testSavingFile_TwoUniWithTwoModulesEach() throws InvalidUserCommandException, IOException {
        clearStorageFile(filePath);
        testManager.createList("Boston University");
        testManager.addModule("Boston University", testModule1);
        testManager.addModule("Boston University", testModule2);
        testManager.createList("Arizona State University");
        testManager.addModule("Arizona State University", testModule3);
        testManager.addModule("Arizona State University", testModule4);
        UserStorageParser.storeCreatedLists(testManager);
        String fileContent = getFileContents(filePath);
        assertEquals("Boston University%\n" + "null%\n" + "F%\n"
                + "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4;default%\n"
                + "CS103;Introduction to Internet Technologies and Web Programming;3;"
                + "IT1001;Introduction to Computing;4;default%\n"
                + "/Arizona State University%\n" + "null%\n" + "F%\n"
                + "CSE412;Database Management;3;CS2102;Database Systems;4;default%\n"
                + "CSE450;Design and Analysis of Algorithms;3;CS3230;Design & Analysis of Algorithm;4;default%\n",
                fileContent);
    }

    @Test
    public void testSavingFile_OneEmptyUni_WithFavourite() throws IOException {
        clearStorageFile(filePath);
        testManager.createList("Boston University");
        testManager.getMyManager().get("Boston University").setFavourite(true);
        UserStorageParser.storeCreatedLists(testManager);
        String fileContent = getFileContents(filePath);
        assertEquals("Boston University%\n" + "null%\n" + "T%\n", fileContent);
    }
}