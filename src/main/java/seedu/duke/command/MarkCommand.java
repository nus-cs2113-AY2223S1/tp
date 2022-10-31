package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.biometrics.Calculator;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MarkCommand extends Command {
    public static final int MAXIMUM_TIME = 1440;
    public static final int ZERO = 0;
    public static final String INVALID_TIME_MESSAGE = "Invalid value for time";
    public static final String INVALID_MET_MESSAGE = "Invalid met value";
    public static final int MAXIMUM_MET = 50;
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
                double time = getTimeWithValidation(argumentList);
                double metabolicEquivalent = getMetabolicEquivalentWithValidation(argumentList);
                int calories = Calculator.calculateExerciseCalories(biometrics, time, metabolicEquivalent);
                exerciseList.markDone(exerciseIndex, time, calories);
                assert exercise.getDone() : "exercise should be done";
                ui.output(exercise.getExerciseName() + " is marked as done successfully",
                        "calories:" + exercise.getCaloriesBurnt());
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Error converting string to double", e);
                throw new IllegalValueException("time and met must be decimal");
            }
            break;
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

    private static double getMetabolicEquivalentWithValidation(String[] argumentList) throws IllegalValueException {
        validateDecimalPlace(argumentList[3]);
        double metabolicEquivalent = Double.parseDouble(argumentList[3]);
        validateDouble(metabolicEquivalent, MAXIMUM_MET, ZERO, INVALID_MET_MESSAGE);
        return metabolicEquivalent;
    }


    private static void validateDecimalPlace(String doubleString) throws IllegalValueException {
        String[] doubleArray = doubleString.split("\\.");
        if (doubleArray.length == 2 && doubleArray[1].length() > 1) {
            throw new IllegalValueException("Double must be 1 decimal place");
        }
    }

    private static double getTimeWithValidation(String[] argumentList) throws IllegalValueException {
        validateDecimalPlace(argumentList[2]);
        double time = Double.parseDouble(argumentList[2]);
        validateDouble(time, MAXIMUM_TIME, ZERO, INVALID_TIME_MESSAGE);
        return time;
    }

    private static void validateDouble(double value, int maximumAcceptableValue, int maximumRejectedValue,
                                       String rejectMessage) throws IllegalValueException {
        if (value <= maximumRejectedValue || value > maximumAcceptableValue) {
            throw new IllegalValueException(rejectMessage);
        }
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
        this.biometrics = biometrics;
        this.exerciseList = exerciseList;
    }
}
