package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ParserTest {
    ReviewList rv = new ReviewList();
    Parser ps = new Parser(rv);

    @Test
    void commandWordTest() {
        String testCommand = "Test command";
        String[] expected = new String[]{"Test", "command"};

        ps.getCommandWord(testCommand);

        assertArrayEquals(expected, ps.getCommandWord(testCommand));
    }
}