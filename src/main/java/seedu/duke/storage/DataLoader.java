package seedu.duke.storage;

import seedu.duke.logic.command.AddCommand;
import seedu.duke.logic.command.SetCommand;
import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents operation of loading tasks from save file.
 */
public class DataLoader {

    public static final int EXERCISE_ARRAY_LENGTH = 2;
    public static final String DONE = "1";
    public static final int MARK_STATUS = 1;
    public static final String MARK_STATUS_SEPARATOR = "\\|";
    public static final int ARGUMENTS_INDEX = 0;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String LOAD_ERROR_PREPEND = "Error with loading: ";

    /**
     * Loads tasks from save file into taskList.
     *
     * @param dataFile Path of save file
     */
    public static void loadData(Path dataFile, Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList,
                                FoodList foodList, RecordList recordList) throws FileNotFoundException {
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
                LOGGER.log(Level.WARNING, LOAD_ERROR_PREPEND + line);
                ui.output(LOAD_ERROR_PREPEND + line);
            }
        }
        input.close();
    }
}