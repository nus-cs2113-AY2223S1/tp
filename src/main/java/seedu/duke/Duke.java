package seedu.duke;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui.showWelcomeMessage();
        boolean isProgramEnd = false;
        while (!isProgramEnd) {
            isProgramEnd = Authentication.handleAuthenticationRequest();
        }
    }
}
