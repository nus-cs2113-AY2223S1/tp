package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import command.EndCommand;
import exception.DukeException;
import parser.Parser;

import org.junit.jupiter.api.Test;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    public void testBye() throws DukeException {
        Parser parser = new Parser();
        assertEquals(parser.parseCommand("bye"), new EndCommand());
    }
}
