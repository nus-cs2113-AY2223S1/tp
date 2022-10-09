package seedu.duke.command;

import seedu.duke.*;
import seedu.duke.exception.IllegalValueException;

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

        String exerciseStatus = argumentList[0];
        int exerciseIndex = Integer.parseInt(argumentList[1]) - 1;

        switch (exerciseStatus) {
        case "done":
            if (exerciseIndex >= exerciseList.getExerciseListSize() || exerciseIndex < 0) {
                throw new IllegalValueException("Exercise not found");
            }
            Exercise exercise = exerciseList.getExercise(exerciseIndex);
            exerciseList.markDone(exerciseIndex);
            ui.output(String.format("%s is marked as done successfully", exercise.getExerciseName()));
            break;
        case "undone":
            if (exerciseIndex >= exerciseList.getCompletedExerciseListSize() || exerciseIndex < 0) {
                throw new IllegalValueException("Exercise not found");
            }
            exercise = exerciseList.getCompletedExercise(exerciseIndex);
            exerciseList.markUndone(exerciseIndex);
            ui.output(String.format("%s is marked as undone successfully", exercise.getExerciseName()));
            break;
        default:
            throw new IllegalValueException("Invalid mark command");
        }
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList) {
        this.ui = ui;
        this.exerciseList = exerciseList;
    }
}
