package seedu.duke.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

/**
 * A bunch of regression tests.
 */
public class TimetableTest {
    private static final String NEWLINE_REGEX = "(\\r\\n|\\r|\\n)";
    private static final Module CS1010S = Module.get("CS1010S");
    private static final Module CS2113 = Module.get("CS2113");
    private static final Module CS3216 = Module.get("CS3216");
    private static final Module EE2211 = Module.get("EE2211");
    private static final Module EE4204 = Module.get("EE4204");
    private static final Module IE2141 = Module.get("IE2141");
    private static final Module ST2334 = Module.get("ST2334");
    private static final List<Pair<Module, RawLesson>> SAMPLE_LESSONS = List.of(
        Pair.of(ST2334, ST2334.getSemesterData(1).getLessonsByTypeAndNo(LessonType.LECTURE, "2").get(0)),
        Pair.of(ST2334, ST2334.getSemesterData(1).getLessonsByTypeAndNo(LessonType.TUTORIAL, "9").get(0)),
        Pair.of(IE2141, IE2141.getSemesterData(1).getLessonsByTypeAndNo(LessonType.TUTORIAL, "03").get(0)),
        Pair.of(IE2141, IE2141.getSemesterData(1).getLessonsByTypeAndNo(LessonType.LECTURE, "1").get(0)),
        Pair.of(EE4204, EE4204.getSemesterData(1).getLessonsByTypeAndNo(LessonType.PACKAGED_LECTURE, "01").get(0)),
        Pair.of(EE4204, EE4204.getSemesterData(1).getLessonsByTypeAndNo(LessonType.PACKAGED_TUTORIAL, "01").get(0)),
        Pair.of(EE2211, EE2211.getSemesterData(1).getLessonsByTypeAndNo(LessonType.LECTURE, "01").get(0)),
        Pair.of(EE2211, EE2211.getSemesterData(1).getLessonsByTypeAndNo(LessonType.TUTORIAL, "19").get(0)),
        Pair.of(CS2113, CS2113.getSemesterData(1).getLessonsByTypeAndNo(LessonType.LECTURE, "1").get(0)),
        Pair.of(CS2113, CS2113.getSemesterData(1).getLessonsByTypeAndNo(LessonType.TUTORIAL, "4").get(0)));

    @Test
    @DisabledOnOs(OS.WINDOWS)
    public void timetable_favouriteModuleLessons_formattedCorrectly() throws IOException {
        Timetable t = new Timetable(CS2113.getSemesterData(1).timetable.stream()
                .map(s -> Pair.of(CS2113, s))
                .collect(Collectors.toList()), false, false);
        InputStream stream = TimetableTest.class.getClassLoader().getResourceAsStream("timetableCS2113.txt");
        String expected = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        assertEquals(expected.replaceAll("\\s+", ""), t.toString().replaceAll("\\s+", ""));
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    public void timetable_stackedLessons_formattedCorrectly() throws IOException {
        Timetable t = new Timetable(CS1010S.getSemesterData(1).timetable.stream()
                .map(s -> Pair.of(CS1010S, s))
                .collect(Collectors.toList()), false, false);
        InputStream stream = TimetableTest.class.getClassLoader().getResourceAsStream("timetableCS1010S.txt");
        String expected = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        String actual = t.toString();
        assertTimetableEquals(expected, actual);
    }

    @Test
    public void timetable_lessonStartingAtHalfHour_formattedCorrectly() throws IOException, URISyntaxException {
        Timetable t = new Timetable(List.of(Pair.of(CS3216, 
            CS3216.getSemesterData(1).getLessonsByTypeAndNo(LessonType.LECTURE, "1").get(0))),
            false, true);
        InputStream stream = TimetableTest.class.getClassLoader().getResourceAsStream("timetableCS3216.txt");
        String expected = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        String actual = t.toString();
        assertTimetableEquals(expected, actual);
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    public void timetable_colored_formattedCorrectly() throws IOException, URISyntaxException {
        Timetable t = new Timetable(SAMPLE_LESSONS, true, false);
        InputStream stream = TimetableTest.class.getClassLoader().getResourceAsStream("timetableColor.txt");
        String expected = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        String actual = t.toString();
        assertTimetableEquals(expected, actual);
    }

    @Test
    public void timetable_windows_formattedCorrectly() throws IOException {
        Timetable t = new Timetable(SAMPLE_LESSONS, false, true);
        InputStream stream = TimetableTest.class.getClassLoader().getResourceAsStream("timetableWindows.txt");
        String expected = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        String actual = t.toString();
        assertTimetableEquals(expected, actual);
    }

    private void assertTimetableEquals(String expected, String actual) {
        String[] expectedArr = expected.split(NEWLINE_REGEX);
        String[] actualArr = actual.split(NEWLINE_REGEX);
        assertEquals(expectedArr.length, actualArr.length);
        for (int i = 0; i < expectedArr.length; i++) {
            assertEquals(expectedArr[i], actualArr[i]);
        }
    }
}
