package seedu.duke.userstorage;

import org.junit.jupiter.api.Test;
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
        testManager = UserStorageParser.getSavedLists();
        UserUniversityList testUniversityList = testManager.getMyManager().get("Boston University");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuCode(), "MET CS 248");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuTitle(), "Discrete Mathematics");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuCredit(), "3");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCode(), "CS1231");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusTitle(), "Discrete Structures");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCredit(), "4");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getComment(), "default");
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
    }

    @Test
    public void testLoadingFile_OneUniWithOneModule_IsFavourite() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%" + "null%" + "T%"
                +  "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4;default%";
        UserStorage.saveFile(fileContent, true);
        testManager = UserStorageParser.getSavedLists();
        UserUniversityList testUniversityList = testManager.getMyManager().get("Boston University");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuCode(), "MET CS 248");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuTitle(), "Discrete Mathematics");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getPuCredit(), "3");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCode(), "CS1231");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusTitle(), "Discrete Structures");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getNusCredit(), "4");
        assertEquals(testUniversityList.getMyModules().getModules().get(0).getComment(), "default");
        assertTrue(testManager.getMyManager().get("Boston University").isFavourite());
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
        UserStorage.saveFile(fileContent, true);
        testManager = UserStorageParser.getSavedLists();
        UserUniversityList testUniversityListBoston = testManager.getMyManager().get("Boston University");
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getPuCode(), "MET CS 248");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getPuTitle(), "Discrete Mathematics");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getPuCredit(), "3");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getNusCode(), "CS1231");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getNusTitle(), "Discrete Structures");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getNusCredit(), "4");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(0).getComment(), "default");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getPuCode(), "CS103");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getPuTitle(),
                "Introduction to Internet Technologies and Web Programming");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getPuCredit(), "3");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getNusCode(), "IT1001");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getNusTitle(),
                "Introduction to Computing");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getNusCredit(), "4");
        assertEquals(testUniversityListBoston.getMyModules().getModules().get(1).getComment(), "default");
        UserUniversityList testUniversityListArizona = testManager.getMyManager().get("Arizona State University");
        assertFalse(testManager.getMyManager().get("Arizona State University").isFavourite());
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getPuCode(), "CSE412");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getPuTitle(), "Database Management");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getPuCredit(), "3");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getNusCode(), "CS2102");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getNusTitle(), "Database Systems");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getNusCredit(), "4");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(0).getComment(), "default");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getPuCode(), "CSE450");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getPuTitle(),
                "Design and Analysis of Algorithms");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getPuCredit(), "3");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getNusCode(), "CS3230");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getNusTitle(),
                "Design & Analysis of Algorithm");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getNusCredit(), "4");
        assertEquals(testUniversityListArizona.getMyModules().getModules().get(1).getComment(), "default");
    }

    @Test
    public void testLoadingFile_TwoEmptyUnis_OneFavouriteOneNotFavourite() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%" + "null%" + "T%"
                            + "/Arizona State University%" + "null%" + "F%";
        UserStorage.saveFile(fileContent, true);
        testManager = UserStorageParser.getSavedLists();
        UserUniversityList testUniversityList1 = testManager.getMyManager().get("Boston University");
        UserUniversityList testUniversityList2 = testManager.getMyManager().get("Arizona State University");
        assertTrue(testUniversityList1.getMyModules().getModules().size() == 0);
        assertTrue(testUniversityList2.getMyModules().getModules().size() == 0);
        assertTrue(testManager.getMyManager().get("Boston University").isFavourite());
        assertFalse(testManager.getMyManager().get("Arizona State University").isFavourite());
    }

    @Test
    public void testSavingFile_OneUniWithOneModule() throws InvalidUserCommandException, IOException {
        clearStorageFile(filePath);
        testManager.createList("Boston University");
        testManager.addModule("Boston University", testModule1);
        UserStorageParser.storeCreatedLists(testManager);
        String fileContent = getFileContents(filePath);
        assertEquals(fileContent, "Boston University%\n" + "null%\n" + "F%\n"
                + "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4;default%\n");
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
        assertEquals(fileContent, "Boston University%\n" + "null%\n" + "F%\n"
                + "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4;default%\n"
                + "CS103;Introduction to Internet Technologies and Web Programming;3;"
                + "IT1001;Introduction to Computing;4;default%\n"
                + "/Arizona State University%\n" + "null%\n" + "F%\n"
                + "CSE412;Database Management;3;CS2102;Database Systems;4;default%\n"
                + "CSE450;Design and Analysis of Algorithms;3;CS3230;Design & Analysis of Algorithm;4;default%\n");
    }

    @Test
    public void testSavingFile_OneEmptyUni_WithFavourite() throws IOException {
        clearStorageFile(filePath);
        testManager.createList("Boston University");
        testManager.getMyManager().get("Boston University").setFavourite(true);
        UserStorageParser.storeCreatedLists(testManager);
        String fileContent = getFileContents(filePath);
        assertEquals(fileContent, "Boston University%\n" + "null%\n" + "T%\n");
    }

    @Test
    public void testLoadingFile_OneUniWithOneModule() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%" + "null%" + "F%"
                +  "MET CS 248;Discrete Mathematics;3;CS1231;Discrete Structures;4;default%";
        UserStorage.saveFile(fileContent, true);
        UserUniversityListManager testManager = UserStorageParser.getSavedLists();

    }
}