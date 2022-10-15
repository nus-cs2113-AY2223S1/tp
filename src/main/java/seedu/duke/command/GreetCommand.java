package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.Ui;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;
import seedu.duke.storage.Storage;

public class GreetCommand extends Command {

    String logo
            = " _____   ___     ___        _____  _   __            _____   _____   _____\n"
            + "|_   _| |    \\  | |\\ \\     /  __/ | | / /   _____   |  ___| |_   _| |_   _|\n"
            + "  | |   |    /  | |_\\ \\   /  /    | |/ /   / ___ \\  | |___    | |     | |\n"
            + "  |_|   |_|\\ \\  |  __  \\  \\  \\__  | |\\ \\  | /   \\ | |  ___|  _| |_    | |\n"
            + "  |_|   |_| \\_\\ |_|   \\ \\  \\____\\ |_| \\_\\ |_|   |_| |_|     |_____|   |_|";

    @Override
    public void execute() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Howdy!");
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList) {

    }

}
