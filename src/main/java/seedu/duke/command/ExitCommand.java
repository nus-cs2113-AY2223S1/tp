package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.exercise.ExerciseList;

public class ExitCommand extends Command {
    private Ui ui;

    @Override
    public void execute() {
        ui.output("Farewell!");
        Duke.isProgramFinished = true;
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList) {
        this.ui = ui;
    }
}
