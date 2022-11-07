package recipeditor;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

class RecipeditorTest {

    @Test
    void runAndExit() {
        String simulatedInput = "/exit";
        ByteArrayInputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(input);
        String[] args = {""};
        Recipeditor.run();
        Assertions.assertTrue(true);
    }
}
