package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.GreetCommand;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.ExerciseList;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static Ui ui;
    private static Biometrics biometrics;

    private static ExerciseList exerciseList;
    public static boolean isProgramFinished = false;

    public Duke() {
        ui = new Ui();
        biometrics = new Biometrics();
        exerciseList = new ExerciseList();
    }

    private static void startDuke() {
        new Duke();
        Command greetCommand = new GreetCommand();
        try {
            greetCommand.execute();
        } catch (IllegalValueException e) {
            e.getMessage();
        }
        ui.line();
    }

    public static void main(String[] args) {
        //Solution below adapted from  https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java
        startDuke();

        while (!isProgramFinished) {
            try {
                String input = ui.input();
                ui.line();
                Command command = Parser.parse(input);
                command.setData(ui, biometrics, exerciseList);
                command.execute();
            } catch (IllegalValueException e) {
                ui.output(e.getMessage());
            } finally {
                ui.line();
            }
        }
    }
}
