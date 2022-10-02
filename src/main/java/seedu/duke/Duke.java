package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.GreetCommand;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static Ui ui;
    public static boolean isProgramFinished = false;

    public Duke() {
        ui = new Ui();
    }

    private static void startDuke() {
        new Duke();
        Command greetCommand = new GreetCommand();
        greetCommand.execute(ui);
        ui.line();
    }

    public static void main(String[] args) {

        startDuke();

        while (!isProgramFinished) {
                String input = ui.input();
                ui.line();
                Command command = Parser.parse(input);
                command.execute(ui);
                ui.line();
        }
    }
}
