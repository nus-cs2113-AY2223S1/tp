package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.module.lessons.Lesson;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimetableTest {
    static Timetable timetable = new Timetable();
    static List<Lesson> lesson = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        timetable.addNewModule("CS2113", "SE and OOP", lesson);
        timetable.addNewModule("CS2040", "DSA", lesson);
    }

    @Test
    void listModules() {
        assertEquals("Here are your modules:\n"
                + "1. CS2113: SE and OOP\n\n2. CS2040: DSA\n\n", timetable.listModules());
        lesson.clear();
    }

    @Test
    void getListLength() {
        assertEquals(2, timetable.getListLength());
        lesson.clear();
    }

    @Test
    void getShortenedList() {
        assertEquals("1. CS2113 : SE and OOP\n2. CS2040 : DSA\n", timetable.getShortenedList());
        lesson.clear();
    }
}