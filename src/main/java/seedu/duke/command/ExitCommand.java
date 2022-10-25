package seedu.duke.command;

import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

public class ExitCommand extends Command {
    private Ui ui;

    @Override
    public void execute() {
        ui.output("Farewell!");
        Duke.isProgramFinished = true;
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
    }
}
