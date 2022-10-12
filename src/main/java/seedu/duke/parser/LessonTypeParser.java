package seedu.duke.parser;

import java.lang.IllegalArgumentException;
import seedu.duke.model.LessonType;

public class LessonTypeParser {

    public static LessonType parse(String input) throws IllegalArgumentException {
        input = input.toLowerCase();
        if (input.equals("dlec") || input.startsWith("des")) {
            return LessonType.DESIGN_LECTURE;
        }
        if (input.startsWith("lab")) {
            return LessonType.LABORATORY;
        }
        if (input.startsWith("lec")) {
            return LessonType.LECTURE;
        }
        if (input.startsWith("pro") || input.startsWith("min")) {
            return LessonType.MINI_PROJECT;
        }
        if (input.equals("plec") || (input.startsWith("pac") && input.contains("lec"))) {
            return LessonType.PACKAGED_LECTURE;
        }
        if (input.equals("plec") || (input.startsWith("pac") && input.contains("tut"))) {
            return LessonType.PACKAGED_TUTORIAL;
        }
        if (input.startsWith("rec")) {
            return LessonType.RECITATION;
        }
        if (input.startsWith("sec")) {
            return LessonType.SECTIONAL_TEACHING;
        }
        if (input.startsWith("sem")) {
            return LessonType.SEMINAR_STYLE_MODULE_CLASS;
        }
        if (input.startsWith("tut") && input.endsWith("2")) {
            return LessonType.TUTORIAL_TYPE_2;
        }
        if (input.startsWith("tut")) {
            return LessonType.TUTORIAL;
        }
        if (input.startsWith("w")) {
            return LessonType.WORKSHOP;
        }
        throw new IllegalArgumentException("\"" + input + "\" could not be parsed as a valid lesson type.");
    }    
}