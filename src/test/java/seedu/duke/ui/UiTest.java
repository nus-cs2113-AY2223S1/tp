package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import seedu.duke.Duke;
//import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UiTest {
    @Test
    public void testGreeting() {
        String expectedGreeting = "                        _____ ______ _____  \n"
                + "                       / ____|  ____|  __ \\ \n"
                + "   ___  __ _ ___ _   _| (___ | |__  | |__) |\n"
                + "  / _ \\/ _` / __| | | |\\___ \\|  __| |  ___/ \n"
                + " |  __/ (_| \\__ \\ |_| |____) | |____| |     \n"
                + "  \\___|\\__,_|___/\\__, |_____/|______|_|     \n"
                + "                  __/ |                     \n"
                + "                 |___/                      \n"
                + "Hello! Welcome to easySEP, your personal companion for planning your student exchange :-)\n"
                + "How may I help you today?\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expectedGreeting, Ui.greetUser());
    }

    @Test
    public void testGoodbye() {
        String expectedGoodbye = "_____________________________________________________________________________\n"
                + "Goodbye. Hope to see you again soon!\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expectedGoodbye, Ui.sayByeToUser());
    }
}
