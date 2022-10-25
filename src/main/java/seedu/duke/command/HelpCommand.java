package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

public class HelpCommand extends Command {

    private Ui ui;

    public HelpCommand() {
    }

    @Override
    public void execute() {
        ui.output("Help will always be given at Hogwarts to those who ask for it\n"
                + "Available commands:\n"
                + "help - to display the help message\n"
                + "exit - to exit the program\n"
                + "set biometrics /{age} /{gender} /height /{weight} /{fat percentage}\n"
                + "mark {done/undone} /{exercise index}\n"
                + "view {biometrics/food/weight/bmi/maintenance/all} {exercise/strength/cardio /{done}}\n"
                + "add strength /{description} /{sets} /{repetitions} /{date}\n"
                + "add cardio /{description} /{distance} /{repetitions} /{date}\n"
                + "add food /{description} /{calories} /{date}\n"
                + "add weight /{weight} /{fat percentage}\n"
                + "remove {food/exercise} /{food index/current exerciseList index}\n"
                + "mark {done/undone} /{exercise index} / {time}, {metabolic equivalent}\n"
                + "find {strength/cardio/food/date_e/date_f} /{description}");
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
    }
}
