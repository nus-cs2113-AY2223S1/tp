package seedu.duke.storage;


import seedu.duke.Ui;
import seedu.duke.command.AddCommand;
import seedu.duke.command.SetCommand;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Represents operation of loading tasks from save file.
 */
public class LoadData {

    /**
     * Loads tasks from save file into taskList.
     *
     * @param dataFile Path of save file
     */
    public static void loadData(Path dataFile, Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList,
                                FoodList foodList, RecordList recordList) throws IllegalValueException,
            FileNotFoundException {
        Scanner input = new Scanner(new File(dataFile.toUri()));
        while (input.hasNext()) {
            String line = input.nextLine();
            try {
                if (line.split(" ")[0].equals("biometrics")) {
                    SetCommand setCommand = new SetCommand(line, false);
                    setCommand.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
                    setCommand.execute();
                } else {
                    String[] saveDataArray = line.split("\\|");
                    boolean isMarkDone = saveDataArray[1].trim().equals("1");
                    AddCommand addCommand = new AddCommand(saveDataArray[0]
                            .trim(), false, isMarkDone);
                    addCommand.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
                    addCommand.execute();
                }
            } catch (IllegalValueException e) {
                throw new IllegalValueException("error with loading : " + line);
            }
        }
        input.close();
    }
}
