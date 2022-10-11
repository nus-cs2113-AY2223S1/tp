package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.Ui;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;

public class GreetCommand extends Command {

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    @Override
    public void execute() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Howdy!");
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList) {

    }

}
