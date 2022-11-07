package seedu.duke.logic.command;


import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents command for loading data from save file.
 */
public class LoadCommand extends Command {

    private Ui ui;
    private Storage storage;
    private Biometrics biometrics;
    private ExerciseList exerciseList;
    private FoodList foodList;

    private RecordList recordList;


    public LoadCommand() {
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
        this.storage = storage;
        this.biometrics = biometrics;
        this.exerciseList = exerciseList;
        this.foodList = foodList;
        this.recordList = recordList;
    }

    @Override
    public void execute() throws IllegalValueException {
        try {
            if (storage.dataFileExists()) {
                ui.output("Remembering existing data......");
            }
            storage.loadData(ui, biometrics, exerciseList, foodList, recordList);
            ui.output("Done");
        } catch (FileNotFoundException e) {
            try {
                storage.createDataFile();
                ui.output("Data file created under data.txt");
            } catch (IOException ex) {
                throw new IllegalValueException("unable to create save file");
            }
        }
    }

}
