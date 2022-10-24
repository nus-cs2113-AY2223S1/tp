package seedu.duke.userstorage;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidUserStorageFileException;
import seedu.duke.parser.UserStorageParser;
import seedu.duke.timetable.Lesson;
import seedu.duke.timetable.Timetable;
import seedu.duke.timetable.TimetableManager;
import seedu.duke.university.University;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTimetableStorageTest {
    private static final String filePath = "data/timetable_info.txt";
    TimetableManager testManager = new TimetableManager();
    University testBU = new University("Boston University", "nil");
    Lesson testLesson1 = new Lesson("MET CS 248", "Discrete Mathematics", "3",
            testBU, "monday", "08:00", "12:00");
    Lesson testLesson2 = new Lesson("CS103", "Introduction to Internet Technologies and Web Programming",
            "3", testBU, "tuesday", "09:00", "12:00");
    University testAU = new University("Arizona State University", "nil");
    Lesson testLesson3 = new Lesson("CSE412", "Database Management", "3",
            testAU, "wednesday", "10:00", "12:00");
    Lesson testLesson4 = new Lesson("CSE450", "Design and Analysis of Algorithms",
            "3", testAU, "thursday", "11:00", "12:00");


    public UserTimetableStorageTest() throws InvalidUniversityException, InvalidModuleException {
    }

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
    public void testInvalidUserStorageFileExceptionThrown() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%\n"
                + "MET CS 248;Discrete Mathematics;3;nil;monday;08:00%";
        assertThrows(InvalidUserStorageFileException.class,
            () -> UserStorageParser.convertFileContentIntoTimetable(fileContent));
    }

    @Test
    public void testSavingFile_OneTimetableWithOneLesson() throws IOException {
        clearStorageFile(filePath);
        testManager.createTimetable("Boston University", false);
        testManager.addLesson(testLesson1, false);
        UserStorageParser.storeTimetable(testManager);
        String fileContent = getFileContents(filePath);
        assertEquals(fileContent, "Boston University%\n"
                + "MET CS 248;Discrete Mathematics;3;nil;monday;08:00;12:00%\n");
    }

    @Test
    public void testSavingFile_TwoTimetablesWithTwoLesson() throws IOException {
        clearStorageFile(filePath);
        testManager.createTimetable("Boston University", false);
        testManager.addLesson(testLesson1, false);
        testManager.addLesson(testLesson2, false);
        testManager.createTimetable("Arizona State University", false);
        testManager.addLesson(testLesson3, false);
        testManager.addLesson(testLesson4, false);
        UserStorageParser.storeTimetable(testManager);
        String fileContent = getFileContents(filePath);
        assertEquals(fileContent, "Boston University%\n"
                + "CS103;Introduction to Internet Technologies and Web Programming;3;nil;tuesday;09:00;12:00%\n"
                + "MET CS 248;Discrete Mathematics;3;nil;monday;08:00;12:00%\n"
                + "/Arizona State University%\n"
                + "CSE412;Database Management;3;nil;wednesday;10:00;12:00%\n"
                + "CSE450;Design and Analysis of Algorithms;3;nil;thursday;11:00;12:00%\n");
    }

    @Test
    public void testLoadingFile_OneTimetableWithOneLesson() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%\n"
                + "MET CS 248;Discrete Mathematics;3;nil;monday;08:00;12:00%";
        UserStorage.saveFile(fileContent, false);
        testManager = UserStorageParser.getSavedTimetables();
        for (Map.Entry<String, Timetable> set : testManager.getTimetableManager().entrySet()) {
            assertEquals("Boston University", set.getKey());
            Timetable timetable = testManager.getTimetableByUniversityName("Boston University");
            assertEquals(timetable.getTimetable().get("monday").get(0).getCode(), "MET CS 248");
            assertEquals(timetable.getTimetable().get("monday").get(0).getTitle(), "Discrete Mathematics");
            assertEquals(timetable.getTimetable().get("monday").get(0).getCredit(), "3");
            assertEquals(timetable.getTimetable().get("monday").get(0).getDay(), "monday");
            assertEquals(timetable.getTimetable().get("monday").get(0).getStartTime(), "08:00");
            assertEquals(timetable.getTimetable().get("monday").get(0).getEndTime(), "12:00");
        }
    }

    @Test
    public void testLoadingFile_TwoTimetablesWithTwoLessonsEach() throws IOException {
        clearStorageFile(filePath);
        String fileContent = "Boston University%\n"
                + "MET CS 248;Discrete Mathematics;3;nil;monday;08:00;12:00%\n"
                + "CS103;Introduction to Internet Technologies and Web Programming;3;nil;tuesday;09:00;12:00%\n"
                + "/Arizona State University%\n"
                + "CSE412;Database Management;3;nil;wednesday;10:00;12:00%\n"
                + "CSE450;Design and Analysis of Algorithms;3;nil;thursday;11:00;12:00%";
        UserStorage.saveFile(fileContent, false);
        testManager = UserStorageParser.getSavedTimetables();
        for (Map.Entry<String, Timetable> set : testManager.getTimetableManager().entrySet()) {
            if (set.getKey().equals("Boston University")) {
                Timetable timetable = testManager.getTimetableByUniversityName("Boston University");
                assertEquals(timetable.getTimetable().get("monday").get(0).getCode(), "MET CS 248");
                assertEquals(timetable.getTimetable().get("monday").get(0).getTitle(), "Discrete Mathematics");
                assertEquals(timetable.getTimetable().get("monday").get(0).getCredit(), "3");
                assertEquals(timetable.getTimetable().get("monday").get(0).getDay(), "monday");
                assertEquals(timetable.getTimetable().get("monday").get(0).getStartTime(), "08:00");
                assertEquals(timetable.getTimetable().get("monday").get(0).getEndTime(), "12:00");

                assertEquals(timetable.getTimetable().get("tuesday").get(0).getCode(), "CS103");
                assertEquals(timetable.getTimetable().get("tuesday").get(0).getTitle(), "Introduction "
                        + "to Internet Technologies and Web Programming");
                assertEquals(timetable.getTimetable().get("tuesday").get(0).getCredit(), "3");
                assertEquals(timetable.getTimetable().get("tuesday").get(0).getDay(), "tuesday");
                assertEquals(timetable.getTimetable().get("tuesday").get(0).getStartTime(), "09:00");
                assertEquals(timetable.getTimetable().get("tuesday").get(0).getEndTime(), "12:00");
            } else if (set.getKey().equals("Arizona State University")) {
                Timetable timetable = testManager.getTimetableByUniversityName("Arizona State University");
                assertEquals(timetable.getTimetable().get("wednesday").get(0).getCode(), "CSE412");
                assertEquals(timetable.getTimetable().get("wednesday").get(0).getTitle(), "Database Management");
                assertEquals(timetable.getTimetable().get("wednesday").get(0).getCredit(), "3");
                assertEquals(timetable.getTimetable().get("wednesday").get(0).getDay(), "wednesday");
                assertEquals(timetable.getTimetable().get("wednesday").get(0).getStartTime(), "10:00");
                assertEquals(timetable.getTimetable().get("wednesday").get(0).getEndTime(), "12:00");

                assertEquals(timetable.getTimetable().get("thursday").get(0).getCode(), "CSE450");
                assertEquals(timetable.getTimetable().get("thursday").get(0).getTitle(), "Design"
                        + " and Analysis of Algorithms");
                assertEquals(timetable.getTimetable().get("thursday").get(0).getCredit(), "3");
                assertEquals(timetable.getTimetable().get("thursday").get(0).getDay(), "thursday");
                assertEquals(timetable.getTimetable().get("thursday").get(0).getStartTime(), "11:00");
                assertEquals(timetable.getTimetable().get("thursday").get(0).getEndTime(), "12:00");
            }
        }
    }
}
