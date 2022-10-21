package seedu.duke;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.command.Command;
import seedu.duke.command.GreetCommand;
import seedu.duke.command.LoadCommand;
import seedu.duke.command.SaveCommand;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;
import seedu.duke.storage.Storage;

import java.io.IOException;
import java.util.logging.Logger;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static Ui ui;
    private static Storage storage;
    private static Biometrics biometrics;

    private static ExerciseList exerciseList;

    private static FoodList foodList;
    public static boolean isProgramFinished = false;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        biometrics = new Biometrics();
        exerciseList = new ExerciseList();
        foodList = new FoodList();
    }

    private static void startDuke() {
        new Duke();
        Command greetCommand = new GreetCommand();
        try {
            greetCommand.execute();
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        }
        ui.line();
        try {
            TrackNFitLogger.setUp();
            Command loadCommand = new LoadCommand();
            loadCommand.setData(ui, storage, biometrics, exerciseList, foodList);
            loadCommand.execute();
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        } catch (IOException e) {
            ui.output(e.getMessage());
        }
        ui.line();
    }

    private static void stopDuke() {
        try {
            Command saveCommand = new SaveCommand();
            saveCommand.setData(ui, storage, biometrics, exerciseList, foodList);
            saveCommand.execute();
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        } finally {
            ui.line();
            LOGGER.info("Stopping duke");
        }
    }

    public static void main(String[] args) {
        //Solution below adapted from  https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java
        startDuke();

        while (!isProgramFinished) {
            try {
                String input = ui.input();
                ui.line();
                Command command = Parser.parse(input);
                command.setData(ui, storage, biometrics, exerciseList, foodList);
                command.execute();
            } catch (IllegalValueException e) {
                ui.output(e.getMessage());
            } finally {
                ui.line();
            }
        }

        stopDuke();
    }
}
