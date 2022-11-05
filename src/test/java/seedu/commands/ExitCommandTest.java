package seedu.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ExitCommandTest {

    @Test
    void testExit() {
        String result = new ExitCommand().execute().showToUser;
        Assertions.assertEquals("Exiting programme.", result);
    }
}
