package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    static final String expectedGreeting = "Welcome to your Movie Reviews bot,\n";

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream reset = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(out));
    }

    @Test
    void testGreetUser() {
        Ui ui = new Ui();
        ui.greetUser();
        assertEquals(expectedGreeting, out.toString());
        System.setOut(reset);
    }

    @Test
    void testPrint() {
        Ui ui = new Ui();
        ui.print("hello");
        assertEquals("hello\n", out.toString());
        System.setOut(reset);
    }

    @Test
    void testExit() {
        Ui ui = new Ui();
        ui.printExitGreeting();
        assertEquals("\n---------------\nSee you again!\n", out.toString());
        System.setOut(reset);
    }
}