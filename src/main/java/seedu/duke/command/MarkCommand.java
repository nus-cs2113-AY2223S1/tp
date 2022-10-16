package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;
import seedu.duke.storage.Storage;

public class MarkCommand extends Command {
    private Ui ui;
    private ExerciseList exerciseList;
    private String arguments;


    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalValueException {
        String[] argumentList = Parser.getArgumentList(arguments);
        markExercise(argumentList);
    }

    private void markExercise(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 2) {
            throw new IllegalValueException("Invalid mark command");
        }
        assert argumentList.length == 2 : "Invalid mark command";
        String exerciseStatus = argumentList[0];
        int exerciseIndex;
        try {
            exerciseIndex = Integer.parseInt(argumentList[1]) - 1;

        } catch (NumberFormatException e) {
            throw new IllegalValueException("Index must be an integer");
        }

        switch (exerciseStatus) {
        case "done":
            if (exerciseIndex >= exerciseList.getCurrentExerciseListSize() || exerciseIndex < 0) {
                throw new IllegalValueException("Exercise not found");
            }
            Exercise exercise = exerciseList.getCurrentExercise(exerciseIndex);
            exerciseList.markDone(exerciseIndex);
            assert exercise.getDone() == true : "exercise should be done";
            ui.output(String.format("%s is marked as done successfully", exercise.getExerciseName()));
            break;
        case "undone":
            if (exerciseIndex >= exerciseList.getCompletedExerciseListSize() || exerciseIndex < 0) {
                throw new IllegalValueException("Exercise not found");
            }
            exercise = exerciseList.getCompletedExercise(exerciseIndex);
            exerciseList.markUndone(exerciseIndex);
            assert exercise.getDone() == false : "exercise should be undone";
            ui.output(String.format("%s is marked as undone successfully", exercise.getExerciseName()));
            break;
        default:
            throw new IllegalValueException("Invalid mark command");
        }
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList) {
        this.ui = ui;
        this.exerciseList = exerciseList;
    }
}
