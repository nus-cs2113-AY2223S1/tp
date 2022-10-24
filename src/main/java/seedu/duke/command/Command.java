package seedu.duke.command;


import seedu.duke.Ui;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

public abstract class Command {
    public Command() {
    }

    public abstract void execute() throws IllegalValueException;

    public abstract void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList,
                                 FoodList foodList, RecordList recordList);
}
