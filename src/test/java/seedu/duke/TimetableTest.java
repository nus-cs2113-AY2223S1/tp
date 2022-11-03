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
    static List<Lesson> lesson = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        Timetable.addNewModule("CS2113", "SE and OOP", lesson);
        Timetable.addNewModule("CS2040", "DSA", lesson);
    }

    @AfterAll
    public static void tearDown() {
        Timetable.deleteModule(1);
        Timetable.deleteModule(1);
    }

    @Test
    void listModules() {
        assertEquals("Here are your modules:\n"
                + "1. CS2113: SE and OOP\n\n2. CS2040: DSA\n\n", Timetable.listModules());
    }

    @Test
    void getListLength() {
        assertEquals(2, Timetable.getListLength());
    }

    @Test
    void getShortenedList() {
        assertEquals("1. CS2113 : SE and OOP\n2. CS2040 : DSA\n", Timetable.getShortenedList());
    }

    @Test
    void getLessonTypeLength() {
        assertEquals(0, Timetable.getLessonTypeLength(0));
    }

    @Test
    void getLessonTypes() {
        assertEquals("", Timetable.getLessonTypes(0));
    }

}