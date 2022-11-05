package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Validator;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.biometrics.Calculator;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MarkCommand extends Command {

    public static final String INVALID_MARK_COMMAND = "Invalid mark command";
    public static final String INVALID_MARK_UNDONE_COMMAND = "Invalid mark undone command";
    public static final int INDEX_OF_EXERCISE_INDEX = 1;
    public static final int REQUIRED_MARK_UNDONE_SLASHES = 1;
    public static final int REQUIRED_MARK_DONE_SLASHES = 3;
    public static final String LOGGING_EXECUTION = "execute";
    public static final String DONE = "done";
    public static final String UNDONE = "undone";
    public static final String MARK_UNDONE_INVALID_PARAMETER_TYPE_MESSAGE = "Index must be integer";
    public static final String MARK_UNDONE_SUCCESS_MESSAGE = " is marked as undone successfully";
    public static final String ASSERT_UNDONE_FAILURE_MESSAGE = "exercise should be undone";
    public static final String MARK_DONE_INVALID_PARAMETERS_TYPE_MESSAGE = "Index must be integer,"
            + " time and met must be in 1 decimal place";
    public static final String ASSERT_DONE_FAILURE_MESSAGE = "exercise should be done";
    public static final String MARK_DONE_SUCCESS_MESSAGE = " is marked as done successfully";
    public static final String CALORIES_STRING = "calories:";
    public static final int TIME_INDEX = 2;
    public static final int METABOLIC_EQUIVALENT_INDEX = 3;
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
        LOGGER.entering(getClass().getName(), LOGGING_EXECUTION);
        String[] argumentList = Parser.getArgumentList(arguments);
        int slashesCount = Parser.getArgumentsCount(arguments);
        markExercise(argumentList, slashesCount);
        LOGGER.exiting(getClass().getName(), LOGGING_EXECUTION);
    }

    private void markExercise(String[] argumentList, int slashesCount) throws IllegalValueException {

        String exerciseStatus = Parser.getClassType(argumentList);
        switch (exerciseStatus) {
        case DONE:
            markDone(argumentList, slashesCount);
            break;
        case UNDONE:
            markUndone(argumentList, slashesCount);
            break;
        default:
            handleInvalidInput();
            break;
        }
    }


    private void markDone(String[] argumentList, int slashesCount) throws IllegalValueException {
        try {
            Validator.validateCommandInput(slashesCount, REQUIRED_MARK_DONE_SLASHES, INVALID_MARK_COMMAND,
                    arguments.charAt(arguments.length() - 1));
            int exerciseIndex = Validator.getIndexWithValidation(argumentList[INDEX_OF_EXERCISE_INDEX],
                    exerciseList.getCurrentExerciseListSize());
            Exercise exercise = exerciseList.getCurrentExercise(exerciseIndex);
            double time = Validator.getTimeWithValidation(argumentList[TIME_INDEX]);
            double metabolicEquivalent = Validator
                    .getMetabolicEquivalentWithValidation(argumentList[METABOLIC_EQUIVALENT_INDEX]);
            int calories = Calculator.calculateExerciseCalories(biometrics, time, metabolicEquivalent);
            exerciseList.markDone(exerciseIndex, time, calories);
            assert exercise.getDone() : ASSERT_DONE_FAILURE_MESSAGE;
            ui.output(exercise.getExerciseName() + MARK_DONE_SUCCESS_MESSAGE,
                    CALORIES_STRING + exercise.getCaloriesBurnt());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, MARK_DONE_INVALID_PARAMETERS_TYPE_MESSAGE, e);
            throw new IllegalValueException(MARK_DONE_INVALID_PARAMETERS_TYPE_MESSAGE);
        }
    }

    private void markUndone(String[] argumentList, int slashesCount) throws IllegalValueException {
        try {
            Validator.validateCommandInput(slashesCount, REQUIRED_MARK_UNDONE_SLASHES,
                    INVALID_MARK_UNDONE_COMMAND, arguments.charAt(arguments.length() - 1));
            int exerciseIndex = Validator.getIndexWithValidation(argumentList[INDEX_OF_EXERCISE_INDEX],
                    exerciseList.getCompletedExerciseListSize());
            Exercise exercise = exerciseList.getCompletedExercise(exerciseIndex);
            exerciseList.markUndone(exerciseIndex);
            assert !exercise.getDone() : ASSERT_UNDONE_FAILURE_MESSAGE;
            ui.output(exercise.getExerciseName() + MARK_UNDONE_SUCCESS_MESSAGE);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, MARK_UNDONE_INVALID_PARAMETER_TYPE_MESSAGE, e);
            throw new IllegalValueException(MARK_UNDONE_INVALID_PARAMETER_TYPE_MESSAGE);
        }
    }


    private static void handleInvalidInput() throws IllegalValueException {
        LOGGER.log(Level.WARNING, INVALID_MARK_COMMAND);
        throw new IllegalValueException(INVALID_MARK_COMMAND);
    }


    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
        this.biometrics = biometrics;
        this.exerciseList = exerciseList;
    }
}
