package seedu.duke.command;

import seedu.duke.*;
import seedu.duke.exception.IllegalValueException;

import static seedu.duke.command.AddExerciseCommand.exerciseList;

public class MarkCommand extends Command {
    private Ui ui;
    private String arguments;

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        String[] argumentList = Parser.getArgumentList(arguments);
        markExercise(argumentList);
    }

    private void markExercise(String[] argumentList) {
        try {
            if (argumentList.length != 2) {
                throw new IllegalValueException("Invalid mark command");
            }

            String exerciseStatus = argumentList[0];
            int exerciseIndex = Integer.parseInt(argumentList[1]);

            if (exerciseIndex > exerciseList.size()) {
                throw new IllegalValueException("Exercise does not exist");
            }

            switch (exerciseStatus) {
            case "done":
                Exercise exercise = exerciseList.get(exerciseIndex - 1);
                exercise.setDone(true);
                ui.output(String.format("%s is marked as done successfully", exercise.getExerciseName()));
                break;
            case "undone":
                exercise = exerciseList.get(exerciseIndex - 1);
                exercise.setDone(false);
                ui.output(String.format("%s is marked as undone successfully", exercise.getExerciseName()));
                break;
            default:
                throw new IllegalValueException("Invalid mark command");
            }
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        }
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList) {
        this.ui = ui;
    }
}
