package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    Timetable timetable = new Timetable();
    String invalidCommand = "abcdefg";
    String exitCommand = "quit";

    @Test
    void parseCommand_randomInvalidString_parsedCorrectly() {
        assertEquals("Invalid Command!", Parser.parseCommand(timetable, invalidCommand));
    }

    @Test
    void parseCommand_exitCommand_parsedCorrectly() {
        assertEquals("quit", Parser.parseCommand(timetable, exitCommand));
    }
}