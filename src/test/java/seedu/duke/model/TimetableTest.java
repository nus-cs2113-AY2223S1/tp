package seedu.duke.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

/**
 * A bunch of regression tests.
 */
public class TimetableTest {

    @Test
    @DisabledOnOs(OS.WINDOWS)
    public void timetable_favouriteModuleLessons_formattedCorrectly() throws IOException {
        Module mod = Module.get("CS2113");
        Timetable t = new Timetable(mod.getSemesterData(1).timetable.stream()
                .map(s -> Pair.of(mod, s))
                .collect(Collectors.toList()));
        InputStream stream = TimetableTest.class.getClassLoader().getResourceAsStream("timetableCS2113.txt");
        String expected = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        assertEquals(expected, t.toString());
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    public void timetable_stackedLessons_formattedCorrectly() throws IOException {
        Module mod = Module.get("CS1010S");
        Timetable t = new Timetable(mod.getSemesterData(1).timetable.stream()
                .map(s -> Pair.of(mod, s))
                .collect(Collectors.toList()));
        InputStream stream = TimetableTest.class.getClassLoader().getResourceAsStream("timetableCS1010S.txt");
        String expected = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        assertEquals(expected, t.toString());
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    public void timetable_lessonStartingAtHalfHour_formattedCorrectly() throws IOException {
        Module mod = Module.get("CS3216");
        Timetable t = new Timetable(List.of(Pair.of(mod, mod.getSemesterData(1).getLessonByNo("1").get(0))));
        InputStream stream = TimetableTest.class.getClassLoader().getResourceAsStream("timetableCS3216.txt");
        String expected = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        assertEquals(expected, t.toString());
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    public void timetable_colored_formattedCorrectly() throws IOException {
        Module st2334 = Module.get("ST2334");
        Module ie2141 = Module.get("IE2141");
        Module ee4204 = Module.get("EE4204");
        Module ee2211 = Module.get("EE2211");
        Module cs2113 = Module.get("CS2113");
        Timetable t = new Timetable(List.of(
                Pair.of(st2334, st2334.getSemesterData(1).getLessonByNo("2").get(0)),
                Pair.of(st2334, st2334.getSemesterData(1).getLessonByNo("9").get(0)),
                Pair.of(ie2141, ie2141.getSemesterData(1).getLessonByNo("03").get(0)),
                Pair.of(ie2141, ie2141.getSemesterData(1).getLessonByNo("1").get(0)),
                Pair.of(ee4204, ee4204.getSemesterData(1).getLessonByNo("01").get(0)),
                Pair.of(ee4204, ee4204.getSemesterData(1).getLessonByNo("01").get(1)),
                Pair.of(ee2211, ee2211.getSemesterData(1).getLessonByNo("01").get(0)),
                Pair.of(ee2211, ee2211.getSemesterData(1).getLessonByNo("19").get(0)),
                Pair.of(cs2113, cs2113.getSemesterData(1).getLessonByTypeAndNo(LessonType.Lecture, "1").get(0)),
                Pair.of(cs2113, cs2113.getSemesterData(1).getLessonByNo("4").get(0))),
                true);
        InputStream stream = TimetableTest.class.getClassLoader().getResourceAsStream("timetableColor.txt");
        String expected = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        assertEquals(expected, t.toString());
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    public void timetable_windows_formattedCorrectly() throws IOException {
        Module st2334 = Module.get("ST2334");
        Module ie2141 = Module.get("IE2141");
        Module ee4204 = Module.get("EE4204");
        Module ee2211 = Module.get("EE2211");
        Module cs2113 = Module.get("CS2113");
        Timetable t = new Timetable(List.of(
                Pair.of(st2334, st2334.getSemesterData(1).getLessonByNo("2").get(0)),
                Pair.of(st2334, st2334.getSemesterData(1).getLessonByNo("9").get(0)),
                Pair.of(ie2141, ie2141.getSemesterData(1).getLessonByNo("03").get(0)),
                Pair.of(ie2141, ie2141.getSemesterData(1).getLessonByNo("1").get(0)),
                Pair.of(ee4204, ee4204.getSemesterData(1).getLessonByNo("01").get(0)),
                Pair.of(ee4204, ee4204.getSemesterData(1).getLessonByNo("01").get(1)),
                Pair.of(ee2211, ee2211.getSemesterData(1).getLessonByNo("01").get(0)),
                Pair.of(ee2211, ee2211.getSemesterData(1).getLessonByNo("19").get(0)),
                Pair.of(cs2113, cs2113.getSemesterData(1).getLessonByTypeAndNo(LessonType.Lecture, "1").get(0)),
                Pair.of(cs2113, cs2113.getSemesterData(1).getLessonByNo("4").get(0))));
        InputStream stream = TimetableTest.class.getClassLoader().getResourceAsStream("timetableWindows.txt");
        String expected = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        assertEquals(expected, t.toString());
    }
}
