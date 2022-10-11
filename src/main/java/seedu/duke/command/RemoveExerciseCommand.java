package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;

public class RemoveExerciseCommand extends Command {

    private Ui ui;
    private String arguments;
    private Exercise exercise;
    private ExerciseList exerciseList;


    public RemoveExerciseCommand(String arguments) {
        this.arguments = arguments;
    }

    private void removeExercise(String[] argumentList) {
        try {
            if (argumentList.length != 2) {
                throw new IllegalValueException("INVALID_NUMBER_INPUT");
            }
            int index = Integer.parseInt(argumentList[1]);
            exerciseList.getCurrentExerciseList().remove(index - 1);
            ui.output("Exercise has been removed successfully!");
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        }
    }

    @Override
    public void execute() {
        String[] argumentList = Parser.getArgumentList(arguments);
        removeExercise(argumentList);
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList, FoodList foodlist) {
        this.ui = ui;
        this.exerciseList = exerciseList;
    }
}
