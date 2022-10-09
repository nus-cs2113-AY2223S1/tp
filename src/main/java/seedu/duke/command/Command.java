package seedu.duke.command;

import seedu.duke.Biometrics;
import seedu.duke.ExerciseList;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;

public abstract class Command {
    public Command() {
    }

    public abstract void execute() throws IllegalValueException;

    public abstract void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList);
}
