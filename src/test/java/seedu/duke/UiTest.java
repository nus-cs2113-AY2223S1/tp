package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

class UiTest {
    final Ui ui = new Ui();
    static final String EXPECTED_GREETING =  "Welcome to your myReviews, your personal Movie Reviews bot!\n"
            .replaceAll("\\n", System.getProperty("line.separator"));
    static final String EXPECTED_PRINT = "hello\n"
            .replaceAll("\\n", System.getProperty("line.separator"));
    static final String USER_INPUT = "test";

    @Test
    void greetUserTest() {
        // sets outContent to capture any would-be console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.greetUser();
        assertEquals(EXPECTED_GREETING, outContent.toString());
    }

    @Test
    void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.print("hello");
        assertEquals(EXPECTED_PRINT, outContent.toString());
    }

    @Test
    void testGetInput() {
        byte[] inString = USER_INPUT.getBytes();
        ByteArrayInputStream input = new ByteArrayInputStream(inString);
        System.setIn(input);
        Scanner scan = new Scanner(System.in);
        assertEquals(USER_INPUT, ui.getInput(scan));
    }

    @Test
    void testExit() {
        //ensure no error is thrown
        ui.printExitGreeting();
    }
}