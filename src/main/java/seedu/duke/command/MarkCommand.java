package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseCaloriesCalculator;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;
import seedu.duke.storage.Storage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MarkCommand extends Command {
    private Ui ui;
    private Biometrics biometrics;
    private ExerciseList exerciseList;
    private final String arguments;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalValueException {
        LOGGER.entering(getClass().getName(), "execute");
        String[] argumentList = Parser.getArgumentList(arguments);
        markExercise(argumentList);
        LOGGER.exiting(getClass().getName(), "execute");
    }

    private void markExercise(String[] argumentList) throws IllegalValueException {
        if (argumentList.length < 2) {
            LOGGER.log(Level.WARNING, "Invalid arguments length");
            throw new IllegalValueException("Invalid mark command");
        }
        assert argumentList.length >= 2 : "Invalid mark command";
        String exerciseStatus = argumentList[0];
        int exerciseIndex;
        try {
            exerciseIndex = Integer.parseInt(argumentList[1]) - 1;

        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Error converting string to integer", e);
            throw new IllegalValueException("Index must be an integer");
        }

        switch (exerciseStatus) {
        case "done":
            if (argumentList.length != 4) {
                LOGGER.warning("Invalid mark done command");
                throw new IllegalValueException("Invalid mark done command");
            }
            if (exerciseIndex >= exerciseList.getCurrentExerciseListSize() || exerciseIndex < 0) {
                LOGGER.log(Level.WARNING, "Invalid exercise index for mark done");
                throw new IllegalValueException("Exercise not found");
            }
            try {
                Exercise exercise = exerciseList.getCurrentExercise(exerciseIndex);
                double time = Double.parseDouble(argumentList[2]);
                double metabolicEquivalent = Double.parseDouble(argumentList[3]);
                int calories = ExerciseCaloriesCalculator.calculateCalories(biometrics, time, metabolicEquivalent);
                exerciseList.markDone(exerciseIndex, time, calories);
                assert exercise.getDone() : "exercise should be done";
                ui.output(exercise.getExerciseName() + " is marked as done successfully",
                        "calories:" + exercise.getCaloriesBurnt());
                break;
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Error converting string to double", e);
            }
        case "undone":
            if (argumentList.length != 2) {
                LOGGER.warning("Invalid mark undone command");
                throw new IllegalValueException("Invalid mark undone command");
            }
            if (exerciseIndex >= exerciseList.getCompletedExerciseListSize() || exerciseIndex < 0) {
                LOGGER.log(Level.WARNING, "Invalid exercise index for mark undone");
                throw new IllegalValueException("Exercise not found");
            }
            Exercise exercise = exerciseList.getCompletedExercise(exerciseIndex);
            exerciseList.markUndone(exerciseIndex);
            assert !exercise.getDone() : "exercise should be undone";
            ui.output(String.format("%s is marked as undone successfully", exercise.getExerciseName()));
            break;
        default:
            LOGGER.log(Level.WARNING, "Invalid mark command");
            throw new IllegalValueException("Invalid mark command");
        }
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList) {
        this.ui = ui;
        this.biometrics = biometrics;
        this.exerciseList = exerciseList;
    }
}
