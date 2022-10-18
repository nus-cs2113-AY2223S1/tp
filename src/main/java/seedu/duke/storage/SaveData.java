package seedu.duke.storage;


import seedu.duke.Ui;
import seedu.duke.biometrics.Biometrics;
import seedu.duke.biometrics.WeightAndFat;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Represents operation of saving data to save file.
 */
public class SaveData {

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
        //        dataType = "food ";
        //        for (Food food : foodList.getFoodList()){
        //            output.write(String.format("%s %s",
        //                    dataType, foodList.saveFood()) + System.lineSeparator());
        //        }
        output.close();
    }
}
