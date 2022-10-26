package seedu.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UiTest {

    //@@author eehongchan-reused
    //Reused from https://stackoverflow.com/questions/3228427/redirect-system-out-println
    // with minor modifications
    @Test
    void testStringPrinting() {
        Ui ui = new Ui();

        // Start capturing
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        // Run what is supposed to output something
        ui.println("Example string");

        // Stop capturing
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        // Use captured content
        String content = buffer.toString();
        buffer.reset();

        // Assert
        Assertions.assertEquals("Example string", content.trim());
    }
    //@@author

    @Test
    void testGetInput() {
        Ui ui = new Ui();
        ByteArrayInputStream in = new ByteArrayInputStream("Example user input".getBytes());
        Scanner keyboard = new Scanner(in);
        ui.changeScanner(keyboard);
        String content = ui.getLine();
        Assertions.assertEquals("Example user input", content);
    }
}
