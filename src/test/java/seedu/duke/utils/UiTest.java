package seedu.duke.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private static final String EXPECTED_GREET_MESSAGE = "Hello from\n"
        + "__ __ _____ _____ _____ _____\n"
        + "|  |  |  _  |     |     |     |\n"
        + "|_   _|     | | | |  |  | | | |\n"
        + "|_| |__|__|_|_|_|_____|_|_|_|\n\n"
        + "What is your name?";

    @Test
    public void ui_greetMessage_success() {
        Ui ui = new Ui();
    }

}
