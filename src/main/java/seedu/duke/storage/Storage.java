package seedu.duke.storage;


import seedu.duke.Ui;
import seedu.duke.records.Record;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents operations with save file.
 */
public class Storage {

    private final Path dataFile = Paths.get("./data.txt");

    public Storage() {
    }

    /**
     * Creates new save file.
     *
     * @throws IOException if save file creation fails
     */
    public void createDataFile() throws IOException {
        assert !Files.exists(dataFile);
        Files.createFile(dataFile);
    }

    /**
     * Loads data from save file.
     *
     * @throws FileNotFoundException if save file does not exist
     * @throws IllegalValueException if any data fails to load
     */
    public void loadData(Ui ui, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                         RecordList recordList)
            throws FileNotFoundException, IllegalValueException {
        LoadData.loadData(dataFile, ui, this, biometrics, exerciseList, foodList, recordList);
    }

    /**
     * Saves data to save file.
     *
     * @throws IOException   if save file does not exist
     * @throws DukeException if any data fails to save
     */
    public void saveData(Ui ui, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList)
            throws IOException, IllegalValueException {
        SaveData.saveData(dataFile, ui, biometrics, exerciseList, foodList);
    }

}
