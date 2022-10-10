package seedu.duke;

import seedu.duke.FlightManager;

public class SkyControl {
    private Ui ui;
    private PassengerList passengers;

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public SkyControl() {
        ui = new Ui();
        passengers = new PassengerList();
    }

    public void run() {
        ui.showWelcomeMessage();
        String lineInput = ui.nextLineInput();
        while (Parser.isRunning(lineInput)) {
            Parser.parse(lineInput);
            lineInput = ui.nextLineInput();
        }
        ui.goodbyeMessage();
    }

    public static void main(String[] args) {
        new SkyControl().run();
    }
}

