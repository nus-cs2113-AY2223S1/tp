package seedu.duke.userstorage;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Database;
import seedu.duke.command.DatabaseStorage;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.exceptions.InvalidUserCommandException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.UniversityNotFoundException;
import seedu.duke.exceptions.TimetableNotFoundException;
import seedu.duke.parser.UserStorageParser;
import seedu.duke.timetable.Lesson;
import seedu.duke.timetable.Timetable;
import seedu.duke.university.University;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserUniversityList;
import seedu.duke.user.UserUniversityListManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStorageTest {
    UserUniversityListManager testManager = new UserUniversityListManager();

    UserModuleMapping testModule1 = new UserModuleMapping("MET CS 248", "Discrete Mathematics",
            "CS1231", "Discrete Structures", "4", "3", "Boston University",
            "USA");
    UserModuleMapping testModule2 = new UserModuleMapping("CS4472",
            "Software Specification Testing and Quality Assurance", "0.5", "CS4218",
            "Software Testing", "4", "Western University", "Canada");
    University testBU = new University("Boston University", "nil");
    Lesson testLesson1 = new Lesson("MET CS 248", "Discrete Mathematics", "3",
        testBU, "monday", "10:00", "12:00");
    University testWU = new University("Western University", "nil");
    Lesson testLesson2 = new Lesson("CS4472", "Software Specification Testing and Quality Assurance",
            "0.5", testWU, "tuesday", "10:00", "12:00");

    public UserStorageTest() throws InvalidUniversityException, InvalidModuleException {
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
    public void testLoadingOneFile_NotFavourite_NoModules_NoLessons() throws IOException {
        String fileContent = "F%\n" + "#\n";
        UserStorage.saveFile("Boston University", fileContent);
        DatabaseStorage.loadDatabase();
        UserStorage.setFilePathsAtStartUp();
        testManager = UserStorageParser.getSavedLists();
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
        UserStorageParser.deleteUserStorageByUni("Boston University", false);
        Database.clearDatabase();
    }

    @Test
    public void testLoadingOneFile_IsFavourite_NoModules_NoLessons() throws IOException {
        String fileContent = "T%\n" + "#\n";
        UserStorage.saveFile("Boston University", fileContent);
        DatabaseStorage.loadDatabase();
        UserStorage.setFilePathsAtStartUp();
        testManager = UserStorageParser.getSavedLists();
        assertTrue(testManager.getMyManager().get("Boston University").isFavourite());
        UserStorageParser.deleteUserStorageByUni("Boston University", false);
        Database.clearDatabase();
    }

    @Test
    public void testLoadingOneFile_NotFavourite_OneModule_NoLessons() throws IOException {
        String fileContent = "F%\n" + "MET CS 248;%\n" + "#\n";
        UserStorage.saveFile("Boston University", fileContent);
        DatabaseStorage.loadDatabase();
        UserStorage.setFilePathsAtStartUp();
        testManager = UserStorageParser.getSavedLists();
        UserUniversityList testUniversityList = testManager.getMyManager().get("Boston University");
        assertEquals("MET CS 248", testUniversityList.getMyModules().getModules().get(0).getPuCode());
        assertEquals("Discrete Mathematics", testUniversityList.getMyModules().getModules().get(0).getPuTitle());
        assertEquals("3", testUniversityList.getMyModules().getModules().get(0).getPuCredit());
        assertEquals("CS1231", testUniversityList.getMyModules().getModules().get(0).getNusCode());
        assertEquals("Discrete Structures", testUniversityList.getMyModules().getModules().get(0).getNusTitle());
        assertEquals("4", testUniversityList.getMyModules().getModules().get(0).getNusCredit());
        //assertEquals(null, testUniversityList.getMyModules().getModules().get(0).getComment());
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
        UserStorageParser.deleteUserStorageByUni("Boston University", false);
        Database.clearDatabase();
    }

    @Test
    public void testLoadingTwoFiles_NotFavourite_OneModule_OneLesson_WithComment() throws IOException {
        String fileContent = "F%\n" + "MET CS 248;testing%\n" + "#MET CS 248;Monday;10:00;12:00%\n";
        UserStorage.saveFile("Boston University", fileContent);
        fileContent = "F%\n" + "CS4472;%\n" + "#CS4472;Tuesday;10:00;12:00%\n";
        UserStorage.saveFile("Western University", fileContent);
        DatabaseStorage.loadDatabase();
        UserStorage.setFilePathsAtStartUp();
        testManager = UserStorageParser.getSavedLists();
        UserUniversityList testUniversityList = testManager.getMyManager().get("Boston University");
        assertEquals("MET CS 248", testUniversityList.getMyModules().getModules().get(0).getPuCode());
        assertEquals("Discrete Mathematics", testUniversityList.getMyModules().getModules().get(0).getPuTitle());
        assertEquals("3", testUniversityList.getMyModules().getModules().get(0).getPuCredit());
        assertEquals("CS1231", testUniversityList.getMyModules().getModules().get(0).getNusCode());
        assertEquals("Discrete Structures", testUniversityList.getMyModules().getModules().get(0).getNusTitle());
        assertEquals("4", testUniversityList.getMyModules().getModules().get(0).getNusCredit());
        assertEquals("testing", testUniversityList.getMyModules().getModules().get(0).getComment());
        testUniversityList = testManager.getMyManager().get("Western University");
        assertEquals("CS4472", testUniversityList.getMyModules().getModules().get(0).getPuCode());
        assertEquals("Software Specification Testing and Quality Assurance", testUniversityList.getMyModules()
                .getModules().get(0).getPuTitle());
        assertEquals("0.5", testUniversityList.getMyModules().getModules().get(0).getPuCredit());
        assertEquals("CS4218", testUniversityList.getMyModules().getModules().get(0).getNusCode());
        assertEquals("Software Testing", testUniversityList.getMyModules().getModules().get(0).getNusTitle());
        assertEquals("4", testUniversityList.getMyModules().getModules().get(0).getNusCredit());
        Timetable timetable = testManager.getTtManager().getTimetableByUniversityName("Boston University");
        assertEquals(timetable.getTimetable().get("monday").get(0).getCode(), "MET CS 248");
        assertEquals(timetable.getTimetable().get("monday").get(0).getTitle(), "Discrete Mathematics");
        assertEquals(timetable.getTimetable().get("monday").get(0).getCredit(), "3");
        assertEquals(timetable.getTimetable().get("monday").get(0).getDay(), "monday");
        assertEquals(timetable.getTimetable().get("monday").get(0).getStartTime(), "10:00");
        assertEquals(timetable.getTimetable().get("monday").get(0).getEndTime(), "12:00");
        timetable = testManager.getTtManager().getTimetableByUniversityName("Western University");
        assertEquals(timetable.getTimetable().get("tuesday").get(0).getCode(), "CS4472");
        assertEquals(timetable.getTimetable().get("tuesday").get(0).getTitle(),
                "Software Specification Testing and Quality Assurance");
        assertEquals(timetable.getTimetable().get("tuesday").get(0).getCredit(), "0.5");
        assertEquals(timetable.getTimetable().get("tuesday").get(0).getDay(), "tuesday");
        assertEquals(timetable.getTimetable().get("tuesday").get(0).getStartTime(), "10:00");
        assertEquals(timetable.getTimetable().get("tuesday").get(0).getEndTime(), "12:00");
        assertFalse(testManager.getMyManager().get("Boston University").isFavourite());
        assertFalse(testManager.getMyManager().get("Western University").isFavourite());
        UserStorageParser.deleteUserStorageByUni("Boston University", false);
        UserStorageParser.deleteUserStorageByUni("Western University", false);
        Database.clearDatabase();
    }

    @Test void testSavingOneFile_NotFavourite_NoModules_NoLessons() throws FileNotFoundException {
        testManager.createList("Boston University");
        UserStorageParser.storeInfoToUserStorageByUni("Boston University", testManager);
        String fileContent = getFileContents("data/Boston University.txt");
        assertEquals("F%\n" + "#\n", fileContent);
        UserStorageParser.deleteUserStorageByUni("Boston University", false);
    }

    @Test void testSavingOneFile_IsFavourite_NoModules_NoLessons() throws FileNotFoundException {
        testManager.createList("Boston University");
        testManager.getMyManager().get("Boston University").setFavourite(true);
        UserStorageParser.storeInfoToUserStorageByUni("Boston University", testManager);
        String fileContent = getFileContents("data/Boston University.txt");
        assertEquals("T%\n" + "#\n", fileContent);
        UserStorageParser.deleteUserStorageByUni("Boston University", false);
    }

    @Test
    public void testSavingOneFile_NotFavourite_OneModule_NoLessons() throws InvalidUserCommandException,
            FileNotFoundException {
        testManager.createList("Boston University");
        testManager.addModule("Boston University", testModule1);
        UserStorageParser.storeInfoToUserStorageByUni("Boston University", testManager);
        String fileContent = getFileContents("data/Boston University.txt");
        assertEquals("F%\n" + "MET CS 248;%\n" + "#\n", fileContent);
        UserStorageParser.deleteUserStorageByUni("Boston University", false);
    }

    @Test
    public void testSavingTwoFiles_NotFavourite_OneModule_OneLesson_WithComment() throws InvalidUserCommandException,
            FileNotFoundException, InvalidUniversityException, UniversityNotFoundException {
        testManager.createList("Boston University");
        testManager.addModule("Boston University", testModule1);
        testManager.createList("Western University");
        testManager.addModule("Western University", testModule2);
        testManager.getTtManager().addLesson(testLesson1, true);
        testManager.getTtManager().addLesson(testLesson2, true);
        testManager.updateComment("Boston University", "MET CS 248", "testing");
        UserStorageParser.storeInfoToUserStorageByUni("Boston University", testManager);
        String fileContent = getFileContents("data/Boston University.txt");
        assertEquals("F%\n" + "MET CS 248;testing%\n" + "#MET CS 248;monday;10:00;12:00%\n", fileContent);
        testManager.deleteComment("Boston University", "MET CS 248");
        UserStorageParser.storeInfoToUserStorageByUni("Boston University", testManager);
        fileContent = getFileContents("data/Boston University.txt");
        assertEquals("F%\n" + "MET CS 248;%\n" + "#MET CS 248;monday;10:00;12:00%\n", fileContent);
        UserStorageParser.storeInfoToUserStorageByUni("Western University", testManager);
        fileContent = getFileContents("data/Western University.txt");
        assertEquals("F%\n" + "CS4472;%\n" + "#CS4472;tuesday;10:00;12:00%\n", fileContent);
        UserStorageParser.deleteUserStorageByUni("Boston University", false);
        UserStorageParser.deleteUserStorageByUni("Western University", false);
    }

    @Test
    public void testDeleteOneFile() throws TimetableNotFoundException, InvalidUserCommandException {
        testManager.createList("Boston University");
        UserStorageParser.storeInfoToUserStorageByUni("Boston University", testManager);
        testManager.createList("Western University");
        UserStorageParser.storeInfoToUserStorageByUni("Western University", testManager);
        assertEquals("data/Boston University.txt", UserStorage.getFilePaths().get("Boston University"));
        assertEquals("data/Western University.txt", UserStorage.getFilePaths().get("Western University"));
        testManager.deleteList("Boston University");
        UserStorageParser.deleteUserStorageByUni("Boston University", false);
        assertEquals(1, UserStorage.getFilePaths().size());
    }
}
