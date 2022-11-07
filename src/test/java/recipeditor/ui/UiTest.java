package recipeditor.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recipeditor.command.CommandResult;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UiTest {

    @Test
    void readInput_UserInput_UserInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("User input".getBytes());
        Scanner keyboard = new Scanner(in);
        String input = keyboard.nextLine();
        Assertions.assertEquals("User input", input);
    }

    @Test
    public void printOnConsole_TestOutput_TestOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        System.out.print("test output");
        Assertions.assertEquals("test output", out.toString());
    }

    @Test
    public void showGreeting_success() {
        Ui.showGreeting();
    }

    @Test
    public void showExit_success() {
        Ui.showExit();
    }

    @Test
    public void showMessage_success() {
        Ui.showMessage("test message");
    }

    @Test
    public void showPrompt_success() {
        Ui.showPrompt();
    }

}
