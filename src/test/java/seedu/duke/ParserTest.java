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
    String printCommand = "print";
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

    @Test
    void parseCommand_printCommandEmpty_parsedCorrectly() {
        String output = "              :              :              :              :              "
                + ":               "
                + System.lineSeparator()
                + "              : MON          : TUE          : WED          : THU          : FRI           "
                + System.lineSeparator()
                + "==============:==============:==============:==============:==============:==============="
                + System.lineSeparator()
                + "   0800       :              :              :              :              :               "
                + System.lineSeparator()
                + "   0830       :              :              :              :              :               "
                + System.lineSeparator()
                + "   0900       :              :              :              :              :               "
                + System.lineSeparator()
                + "   0930       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1000       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1030       :              :              :              :              :              "
                + System.lineSeparator()
                + "   1100       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1130       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1200       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1230       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1300       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1330       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1400       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1430       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1500       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1530       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1600       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1630       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1700       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1730       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1800       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1830       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1900       :              :              :              :              :               "
                + System.lineSeparator()
                + "   1930       :              :              :              :              :               "
                + System.lineSeparator()
                + "   2000       :              :              :              :              :               "
                + System.lineSeparator()
                + "   2030       :              :              :              :              :               "
                + System.lineSeparator()
                + "   2100       :              :              :              :              :               "
                + System.lineSeparator()
                + "   2130       :              :              :              :              :               "
                + System.lineSeparator()
                + "   2200       :              :              :              :              :               "
                + System.lineSeparator()
                + " "
                + System.lineSeparator()
                + " * Note that timings indicated refers to the start of the corresponding 30 minutes timeslot."
                + System.lineSeparator()
                + " * Slots with XXXXXX indicates that there is a clash between two or more lessons."
                + System.lineSeparator()
                + " * Modules, if any, that start before 8am or ends after 10pm timings are excluded."
                + System.lineSeparator()
                + " * Timings are approximated to 30 minutes block with valid assumption that NUS mods are "
                + "typically designed in such blocks." + System.lineSeparator();


        assertEquals(output, Parser.parseCommand(printCommand, currentSemester));
    }
}