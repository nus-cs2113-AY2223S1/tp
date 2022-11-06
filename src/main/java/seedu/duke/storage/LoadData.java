package seedu.duke.storage;


import seedu.duke.exception.IllegalValueException;
import seedu.duke.logic.command.AddCommand;
import seedu.duke.logic.command.SetCommand;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Represents operation of loading tasks from save file.
 */
public class LoadData {

    public static final int EXERCISE_ARRAY_LENGTH = 2;
    public static final String DONE = "1";
    public static final int MARK_STATUS = 1;
    public static final String MARK_STATUS_SEPARATOR = "\\|";
    public static final int ARGUMENTS_INDEX = 0;

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
                    String[] saveDataArray = line.split(MARK_STATUS_SEPARATOR);
                    boolean isMarkDone = false;
                    if (saveDataArray.length == EXERCISE_ARRAY_LENGTH) {
                        isMarkDone = saveDataArray[MARK_STATUS].trim().equals(DONE);
                    }
                    AddCommand addCommand = new AddCommand(saveDataArray[ARGUMENTS_INDEX]
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
