package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

class UiTest {
    final Ui ui = new Ui();
    final String expectedGreeting =  "Welcome to your Movie Reviews bot,\n"
            .replaceAll("\\n", System.getProperty("line.separator"));
    final String expectedPrint = "hello\n"
            .replaceAll("\\n", System.getProperty("line.separator"));
    final String userInput = "test";

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
    void testGetInput() {
        byte[] inString = userInput.getBytes();
        ByteArrayInputStream input = new ByteArrayInputStream(inString);
        System.setIn(input);
        Scanner scan = new Scanner(System.in);
        assertEquals(userInput, ui.getInput(scan));
    }

    @Test
    void testExit() {
        //ensure no error is thrown
        ui.printExitGreeting();
    }
}