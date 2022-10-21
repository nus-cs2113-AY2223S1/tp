package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    Timetable timetable = new Timetable();
    String invalidCommand = "abcdefg";
    String exitCommand = "quit";
    String currentSemester = "1";

    @Test
    void parseCommand_randomInvalidString_parsedCorrectly() {
        assertEquals("Invalid Command!", Parser.parseCommand(invalidCommand, currentSemester));
    }

    @Test
    void parseCommand_exitCommand_parsedCorrectly() {
        assertEquals("quit", Parser.parseCommand(exitCommand, currentSemester));
    }
}