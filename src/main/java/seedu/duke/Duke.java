package seedu.duke;

import java.util.Scanner;


public class Duke {
    private Parser parser;
    private Ui ui;


    public void run() {
        this.parser = new Parser();
        String command;

        do {
            command = Ui.readCommand();
        } while (!command.equals("bye"));
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
