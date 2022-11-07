package seedu.duke.storage;


import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.ui.Ui;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.Food;
import seedu.duke.records.food.FoodList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Represents operation of saving data to save file.
 */
public class DataSaver {

    /**
     * Saves tasks from taskList to save file.
     *
     * @param dataFile Path of save file
     * @throws IOException           if save file does not exist
     * @throws IllegalValueException if any task fails to save
     */
    public static void saveData(Path dataFile, Ui ui, Biometrics biometrics, ExerciseList exerciseList,
                                FoodList foodList) throws IOException, IllegalValueException {
        FileWriter output = new FileWriter(dataFile.toFile());
        String dataType;
        if (biometrics.isSet) {
            dataType = "biometrics ";
            output.write(dataType + biometrics.saveBiometrics() + System.lineSeparator());
        }
        dataType = "weight ";
        for (WeightAndFat weightAndFat : biometrics.weightAndFatList.getWeightAndFatList()) {
            output.write(dataType + weightAndFat.saveWeightAndFat() + System.lineSeparator());
        }
        for (Exercise exercise : exerciseList.getCompletedExerciseList()) {
            output.write(exercise.saveExercise() + System.lineSeparator());
        }
        for (Exercise exercise : exerciseList.getCurrentExerciseList()) {
            output.write(exercise.saveExercise() + System.lineSeparator());
        }
        dataType = "food ";
        for (Food food : foodList.getFoodList()) {
            output.write(dataType + food.saveFood() + System.lineSeparator());
        }
        output.close();
    }
}
