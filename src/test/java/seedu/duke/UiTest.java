package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
class UiTest {
    Ui ui = new Ui();

    @Test
    void greetUserTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        ui.greetUser();
        String greeting = "Welcome to your Movie Reviews bot,\n"
            .replaceAll("\\n", System.getProperty("line.separator"));

        assertEquals(greeting, outContent.toString());
    }
    
}