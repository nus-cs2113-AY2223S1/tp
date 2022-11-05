package seedu.duke;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.timetable.Timetable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimetableTest {
    static Lesson testLesson1 = new Lesson("Monday", "0800", "0900", "Lecture",
            "L01", "1", "CS2113");
    static Lesson testLesson2 = new Lesson("Monday", "0900", "1000", "Lecture",
            "L02", "1", "CS2113");
    static List<Lesson> lessonWithEntry = new ArrayList<>();
    static List<Lesson> lessonNoEntry = new ArrayList<>();


    @BeforeAll
    public static void setup() {
        lessonWithEntry.add(testLesson1);
        lessonWithEntry.add(testLesson2);
        Timetable.addNewModule("CS2113", "SE and OOP", lessonWithEntry);
        Timetable.addNewModule("CS2040", "DSA", lessonNoEntry);
    }

    @AfterAll
    public static void tearDown() {
        Timetable.deleteModule(1);
        Timetable.deleteModule(1);
    }

    @Test
    void listModules() {
        assertEquals("Here are your modules:\n"
                + "1. CS2113: SE and OOP\n"
                + "     [Lecture] Undetermined Day   Undetermined Time - Undetermined Time   Weeks: NA\n"
                + "\n"
                + "2. CS2040: DSA\n\n", Timetable.listModules());
    }

    @Test
    void getListLength() {
        assertEquals(2, Timetable.getListLength());
    }

    @Test
    void getShortenedList() {
        assertEquals("1. CS2113 : SE and OOP\n", Timetable.getShortenedList());
    }

    @Test
    void getLessonTypeLength() {
        assertEquals(1, Timetable.getSettableLessonTypeLength(0));
    }

    @Test
    void getLessonTypes() {
        assertEquals("1. Lecture\n", Timetable.getSettableLessonTypes(0));
    }

}