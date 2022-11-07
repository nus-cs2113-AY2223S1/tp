package seedu.duke.logic.command;

import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class GreetCommand extends Command {

    String logo
            = " _____   ___     ___        _____  _   __            _____   _____   _____\n"
            + "|_   _| | __ \\  | |\\ \\     /  __/ | | / /   _____   |  ___| |_   _| |_   _|\n"
            + "  | |   | '' /  | |_\\ \\   /  /    | |/ /   / ___ \\  | |___    | |     | |\n"
            + "  | |   | |\\ \\  |  __  \\  \\  \\__  | |\\ \\  | /   \\ | |  ___|  _| |_    | |\n"
            + "  |_|   |_| \\_\\ |_|   \\_\\  \\____\\ |_| \\_\\ |_|   |_| |_|     |_____|   |_|";

    @Override
    public void execute() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Howdy!");
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {

    }

}
