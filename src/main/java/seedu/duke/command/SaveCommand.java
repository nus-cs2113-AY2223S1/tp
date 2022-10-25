package seedu.duke.command;


import seedu.duke.Ui;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

import java.io.IOException;

/**
 * Represents command for saving data into save file.
 */
public class SaveCommand extends Command {

    private Ui ui;
    private Storage storage;
    private Biometrics biometrics;
    private ExerciseList exerciseList;
    private FoodList foodList;


    public SaveCommand() {
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
        this.storage = storage;
        this.biometrics = biometrics;
        this.exerciseList = exerciseList;
        this.foodList = foodList;
    }

    @Override
    public void execute() throws IllegalValueException {
        try {
            storage.saveData(ui, biometrics, exerciseList, foodList);
            ui.output("Slaving data......");
        } catch (IOException e) {
            throw new IllegalValueException("unable to open save file");
        }
    }
}
