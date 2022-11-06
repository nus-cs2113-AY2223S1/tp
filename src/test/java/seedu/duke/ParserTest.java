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
                + ":               \n" 
                + "              : MON          : TUE          : WED          : THU          : FRI           \n"
                + "==============:==============:==============:==============:==============:===============\n"
                + "   0800       :              :              :              :              :               \n"
                + "   0830       :              :              :              :              :               \n"
                + "   0900       :              :              :              :              :               \n"
                + "   0930       :              :              :              :              :               \n"
                + "   1000       :              :              :              :              :               \n"
                + "   1030       :              :              :              :              :               \n"
                + "   1100       :              :              :              :              :               \n"
                + "   1130       :              :              :              :              :               \n"
                + "   1200       :              :              :              :              :               \n"
                + "   1230       :              :              :              :              :               \n"
                + "   1300       :              :              :              :              :               \n"
                + "   1330       :              :              :              :              :               \n"
                + "   1400       :              :              :              :              :               \n"
                + "   1430       :              :              :              :              :               \n"
                + "   1500       :              :              :              :              :               \n"
                + "   1530       :              :              :              :              :               \n"
                + "   1600       :              :              :              :              :               \n"
                + "   1630       :              :              :              :              :               \n"
                + "   1700       :              :              :              :              :               \n"
                + "   1730       :              :              :              :              :               \n"
                + "   1800       :              :              :              :              :               \n"
                + "   1830       :              :              :              :              :               \n"
                + "   1900       :              :              :              :              :               \n"
                + "   1930       :              :              :              :              :               \n"
                + "   2000       :              :              :              :              :               \n"
                + "   2030       :              :              :              :              :               \n"
                + "   2100       :              :              :              :              :               \n"
                + "   2130       :              :              :              :              :               \n"
                + "   2200       :              :              :              :              :               \n"
                + " \n"
                + " * Note that timings indicated refers to the start of the corresponding 30 minutes timeslot.\n"
                + " * Slots with XXXXXX indicates that there is a clash between two or more lessons.\n"
                + " * Modules, if any, that start before 8am or ends after 10pm timings are excluded.\n"
                + " * Timings are approximated to 30 minutes block with valid assumption that NUS mods are "
                + "typically designed in such blocks.\n";

        assertEquals(output, Parser.parseCommand(printCommand, currentSemester));
    }
}