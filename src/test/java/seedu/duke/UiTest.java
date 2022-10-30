package seedu.duke;

import org.junit.jupiter.api.Test;

class UiTest {

    @Test
    void testGreetUser() {
        Ui ui = new Ui();
        //ensure no error
        ui.greetUser();
    }

    @Test
    void testPrint() {
        Ui ui = new Ui();
        //ensure no error
        ui.print("hello");
    }

    @Test
    void testExit() {
        Ui ui = new Ui();
        //ensure no error
        ui.printExitGreeting();
    }

}