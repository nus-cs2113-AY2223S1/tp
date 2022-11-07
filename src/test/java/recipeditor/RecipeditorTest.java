package recipeditor;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

class RecipeditorTest {

    @Test
    void runAndExit_correctExitCommand_programRunsThenExit() {
        String simulatedInput = "/exit";
        ByteArrayInputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(input);
        Recipeditor.run();
        Assertions.assertTrue(true);
    }
}
