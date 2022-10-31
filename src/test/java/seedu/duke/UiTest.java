package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class UiTest {
    Ui ui = new Ui();
    final String expectedGreeting =  "Welcome to your Movie Reviews bot,\n"
            .replaceAll("\\n", System.getProperty("line.separator"));
    final String expectedPrint = "hello\n"
            .replaceAll("\\n", System.getProperty("line.separator"));
    final String expectedExitGreeting = "\n---------------\nSee you again!\n"
            .replaceAll("\\n", System.getProperty("line.separator"));

    @Test
    void greetUserTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.greetUser();
        assertEquals(expectedGreeting, outContent.toString());
    }

    @Test
    void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.print("hello");
        assertEquals(expectedPrint, outContent.toString());
    }

    @Test
    void testExit() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.printExitGreeting();
        assertEquals(expectedExitGreeting, outContent.toString());
    }
}