package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.GreetCommand;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static Ui ui;
    private static Biometrics biometrics;
    public static boolean isProgramFinished = false;

    public Duke() {
        ui = new Ui();
        biometrics = new Biometrics();
    }

    private static void startDuke() {
        new Duke();
        Command greetCommand = new GreetCommand();
        greetCommand.execute();
        ui.line();
    }

    public static void main(String[] args) {
        //Solution below adapted from  https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java
        startDuke();

        while (!isProgramFinished) {
            String input = ui.input();
            ui.line();
            Command command = Parser.parse(input);
            command.setData(ui, biometrics);
            command.execute();
            ui.line();
        }
    }
}
