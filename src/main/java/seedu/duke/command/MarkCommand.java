package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.Validator;
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

    public static final String INVALID_MARK_COMMAND = "Invalid mark command";
    public static final String INVALID_MARK_UNDONE_COMMAND = "Invalid mark undone command";
    public static final int REQUIRED_MARK_UNDONE_SLASHES = 1;
    public static final int REQUIRED_MARK_DONE_SLASHES = 3;
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
        int slashesCount = Parser.getArgumentsCount(arguments);
        markExercise(argumentList, slashesCount);
        LOGGER.exiting(getClass().getName(), "execute");
    }

    private void markExercise(String[] argumentList, int slashesCount) throws IllegalValueException {

        String exerciseStatus = Parser.getClassType(argumentList);
        switch (exerciseStatus) {
        case "done":
            markDone(argumentList, slashesCount);
            break;
        case "undone":
            markUndone(argumentList, slashesCount);
            break;
        default:
            handleInvalidInput("Invalid mark command", "Invalid mark command");
        }
    }


    private void markDone(String[] argumentList, int slashesCount) throws IllegalValueException {
        Validator.validateCommandInput(slashesCount, REQUIRED_MARK_DONE_SLASHES, INVALID_MARK_COMMAND,
                arguments.charAt(arguments.length() - 1));
        int exerciseIndex = Validator.getIndexWithValidation(argumentList[1], exerciseList.getCurrentExerciseListSize());
        try {
            Exercise exercise = exerciseList.getCurrentExercise(exerciseIndex);
            double time = Validator.getTimeWithValidation(argumentList[2]);
            double metabolicEquivalent = Validator.getMetabolicEquivalentWithValidation(argumentList[3]);
            int calories = Calculator.calculateExerciseCalories(biometrics, time, metabolicEquivalent);
            exerciseList.markDone(exerciseIndex, time, calories);
            assert exercise.getDone() : "exercise should be done";
            ui.output(exercise.getExerciseName() + " is marked as done successfully",
                    "calories:" + exercise.getCaloriesBurnt());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Error converting string to int or double", e);
            throw new IllegalValueException("index must be integer, time and met must be decimal: 1 d.p");
        }
    }

    private void markUndone(String[] argumentList, int slashesCount) throws IllegalValueException {
        try {
            Validator.validateCommandInput(slashesCount, REQUIRED_MARK_UNDONE_SLASHES, INVALID_MARK_UNDONE_COMMAND,
                    arguments.charAt(arguments.length() - 1));
            int exerciseIndex = Validator.getIndexWithValidation(argumentList[1], exerciseList.getCompletedExerciseListSize());
            Exercise exercise = exerciseList.getCompletedExercise(exerciseIndex);
            exerciseList.markUndone(exerciseIndex);
            assert !exercise.getDone() : "exercise should be undone";
            ui.output(String.format("%s is marked as undone successfully", exercise.getExerciseName()));
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Error converting strings to int or double", e);
            throw new IllegalValueException("index must be integer");
        }
    }


    private static void handleInvalidInput(String Invalid_mark_command, String Invalid_mark_command1) throws IllegalValueException {
        LOGGER.log(Level.WARNING, Invalid_mark_command);
        throw new IllegalValueException(Invalid_mark_command1);
    }


    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
        this.biometrics = biometrics;
        this.exerciseList = exerciseList;
    }
}
