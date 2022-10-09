package seedu.duke.command;

import seedu.duke.*;
import seedu.duke.exception.IllegalValueException;

public class AddExerciseCommand extends Command {
    private Ui ui;
    private String arguments;
    private Exercise exercise;
    private ExerciseList exerciseList;

    @Override
    public void execute() {
        String[] argumentList = Parser.getArgumentList(arguments);
        addExercise(argumentList);
    }

    public AddExerciseCommand(String arguments) {
        this.arguments = arguments;
    }


    private void addExercise(String[] argumentList) {
        try {
            if (argumentList.length != 4) {
                throw new IllegalValueException("INVALID_EXERCISE_INPUT");
            }
            String description = argumentList[1];
            int repetitions = Integer.parseInt(argumentList[2]);
            int calories = Integer.parseInt(argumentList[3]);
            exercise = new Exercise(description, repetitions, calories);
            exerciseList.addExercise(exercise);
            ui.output(exercise.toString());
            ui.output(" This exercise is added to the exercise list successfully");
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        }
    }


    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList) {
        this.ui = ui;
        this.exerciseList = exerciseList;
    }
}

