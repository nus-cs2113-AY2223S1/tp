package seedu.duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.duke.model.LessonType;

public class LessonTypeParserTest {
    @Test
    void parser_test_returnsCorrectly() {
        assertEquals(LessonTypeParser.parse("tut"), LessonType.TUTORIAL);
        assertEquals(LessonTypeParser.parse("tutorial"), LessonType.TUTORIAL);
        assertEquals(LessonTypeParser.parse("tut2"), LessonType.TUTORIAL_TYPE_2);
        assertEquals(LessonTypeParser.parse("tutorial2"), LessonType.TUTORIAL_TYPE_2);
        assertEquals(LessonTypeParser.parse("tutorial_type_2"), LessonType.TUTORIAL_TYPE_2);
        assertEquals(LessonTypeParser.parse("lec"), LessonType.LECTURE);
        assertEquals(LessonTypeParser.parse("lecture"), LessonType.LECTURE);
        assertEquals(LessonTypeParser.parse("rec"), LessonType.RECITATION);
        assertEquals(LessonTypeParser.parse("recitation"), LessonType.RECITATION);
        assertEquals(LessonTypeParser.parse("dlec"), LessonType.DESIGN_LECTURE);
        assertEquals(LessonTypeParser.parse("design"), LessonType.DESIGN_LECTURE);
        assertEquals(LessonTypeParser.parse("design_lecture"), LessonType.DESIGN_LECTURE);
        assertEquals(LessonTypeParser.parse("plec"), LessonType.PACKAGED_LECTURE);
        assertEquals(LessonTypeParser.parse("packaged_lecture"), LessonType.PACKAGED_LECTURE);
        assertEquals(LessonTypeParser.parse("ptut"), LessonType.PACKAGED_TUTORIAL);
        assertEquals(LessonTypeParser.parse("sec"), LessonType.SECTIONAL_TEACHING);
        assertEquals(LessonTypeParser.parse("sectional"), LessonType.SECTIONAL_TEACHING);
        assertEquals(LessonTypeParser.parse("sectional_teaching"), LessonType.SECTIONAL_TEACHING);
        assertEquals(LessonTypeParser.parse("wksh"), LessonType.WORKSHOP);
        assertEquals(LessonTypeParser.parse("workshop"), LessonType.WORKSHOP);
        assertEquals(LessonTypeParser.parse("lab"), LessonType.LABORATORY);
        assertEquals(LessonTypeParser.parse("laboratory"), LessonType.LABORATORY);
        assertEquals(LessonTypeParser.parse("proj"), LessonType.MINI_PROJECT);
        assertEquals(LessonTypeParser.parse("mini_project"), LessonType.MINI_PROJECT);
        assertEquals(LessonTypeParser.parse("sem"), LessonType.SEMINAR_STYLE_MODULE_CLASS);
        assertEquals(LessonTypeParser.parse("seminar"), LessonType.SEMINAR_STYLE_MODULE_CLASS);
        assertEquals(LessonTypeParser.parse("seminar_style_module"), LessonType.SEMINAR_STYLE_MODULE_CLASS);
    }

    @Test
    void parser_ambiguousCases_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> LessonTypeParser.parse(""));
        assertThrows(IllegalArgumentException.class, () -> LessonTypeParser.parse("l"));
        assertThrows(IllegalArgumentException.class, () -> LessonTypeParser.parse("t"));
        assertThrows(IllegalArgumentException.class, () -> LessonTypeParser.parse("d"));
        assertThrows(IllegalArgumentException.class, () -> LessonTypeParser.parse("pac"));
        assertThrows(IllegalArgumentException.class, () -> LessonTypeParser.parse("packaged"));
    }
}
