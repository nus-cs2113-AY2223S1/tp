package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.ExerciseList;

public abstract class Command {
    public Command() {
    }

    public abstract void execute() throws IllegalValueException;

    public abstract void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList);
}
