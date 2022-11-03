package seedu.duke;

import org.junit.jupiter.api.Test;

import seedu.duke.module.lessons.Lesson;
import seedu.duke.timetable.Timetable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    String invalidCommand = "abcdefg";
    String exitCommand = "quit";
    String listCommand = "list";
    String allocateCommand = "allocate";
    String currentSemester = "1";

    @Test
    void parseCommand_randomInvalidString_parsedCorrectly() {
        assertEquals("Invalid Command!", Parser.parseCommand(invalidCommand, currentSemester));
    }

    @Test
    void parseCommand_exitCommand_parsedCorrectly() {
        assertEquals("quit", Parser.parseCommand(exitCommand, currentSemester));
    }

    @Test
    void parseCommand_listCommand_parsedCorrectly() {
        Lesson tempLesson = new Lesson("Monday", "0800", "2000", "Lecture",
                "1", "[1,2,3,4,5,6]", "cx0000");
        List<Lesson> tempLessonList = new ArrayList<>();
        tempLessonList.add(tempLesson);
        Timetable.addNewModule("cx0000", "Some non-existent module", tempLessonList);
        assertEquals("Here are your modules:\n"
                        +  "1. cx0000: Some non-existent module\n"
                        +  "     [Lecture] Monday   08:00 - 20:00   [1,2,3,4,5,6]\n\n",
                Parser.parseCommand(listCommand, currentSemester));
        Timetable.deleteModule(1);
    }

    @Test
    void parseCommand_allocateCommandEmpty_parsedCorrectly() {
        assertEquals("Sorry you have no modules to allocate!",
                Parser.parseCommand(allocateCommand, currentSemester));
    }

    @Test
    void parseCommand_allocateCommandNonEmpty_parsedCorrectly() {
        Lesson tempLesson = new Lesson("Monday", "0800", "2000", "Lecture",
                "1", "[1,2,3,4,5,6]", "cx0000");
        List<Lesson> tempLessonList = new ArrayList<>();
        tempLessonList.add(tempLesson);
        Timetable.addNewModule("cx0000", "Some non-existent module", tempLessonList);

        assertEquals("All your mods have been successfully allocated!",
                Parser.parseCommand(allocateCommand, currentSemester));
        Timetable.deleteModule(1);
    }
}