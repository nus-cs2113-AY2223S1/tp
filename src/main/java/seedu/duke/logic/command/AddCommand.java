package seedu.duke.logic.command;

import seedu.duke.logic.Parser;
import seedu.duke.logic.Validator;
import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.CardioExercise;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.exercise.StrengthExercise;
import seedu.duke.records.food.Food;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Adds a new record to relevant list.
 */
public class AddCommand extends Command {
    public static final String INVALID_LOADING_STRENGTH_MESSAGE = "Unable to load strength exercise";
    public static final int STRENGTH_EXERCISE_REPETITION_INDEX = 4;
    public static final String INVALID_LOADING_CARDIO_MESSAGE = "Unable to load cardio exercise";
    public static final int MINIMUM_ADD_STRENGTH_SLASHES = 4;
    public static final int MAXIMUM_ADD_STRENGTH_SLASHES = 5;
    public static final int MINIMUM_ADD_CARDIO_SLASHES = 3;
    public static final int MAXIMUM_ADD_CARDIO_SLASHES = 4;
    public static final int STRENGTH_LOADING_INPUT_COUNT = 8;
    public static final int STRENGTH_EXERCISE_DESCRIPTION_INDEX = 1;
    public static final int STRENGTH_EXERCISE_WEIGHT_INDEX = 2;
    public static final int STRENGTH_EXERCISE_SET_INDEX = 3;
    public static final int MONTHS_TO_ADD = 1;
    public static final int STRENGTH_EXERCISE_DATE_INDEX = 5;
    public static final int LOAD_STRENGTH_EXERCISE_DATE_INDEX = 6;
    public static final int MAXIMUM_ADD_STRENGTH_ARGUMENT_LENGTH = 6;
    public static final int LOAD_STRENGTH_EXERCISE_CALORIES_INDEX = 7;
    public static final String ADD_STRENGTH_SUCCESS_MESSAGE = " This strength exercise is added "
            + "to the exercise list successfully";
    public static final String ADD_STRENGTH_ERROR_MESSAGE = "Weight, set and repetition must be integer";

    public static final String ADD_FOOD_ERROR_MESSAGE = "calories must be integer";
    public static final String ASSERT_ADD_STRENGTH_FAILURE_MESSAGE = "Exercise not added properly";
    public static final String FOOD = "food";
    public static final String STRENGTH = "strength";
    public static final String CARDIO = "cardio";
    public static final String WEIGHT = "weight";
    public static final int CARDIO_LOADING_INPUT_COUNT = 7;
    public static final String ADD_FOOD_SUCCESS_MESSAGE = "This food is added to the food list successfully";
    public static final String INVALID_FOOD_DESCRIPTION_INPUTS = "Please provide valid food description inputs!";
    public static final String NEGATIVE_CALORIES_ERROR_MESSAGE = "Calories inputs need to be positive integer values!";
    public static final String CALORIES_LIMIT_EXCEEDED = "It is impossible to have consumed more than 10000 kcal in a "
            + "day!";
    public static final String INVALID_DATE_FORMAT = "Date should be in the format dd-mm-yyyy";
    public static final String INVALID_ADD_COMMAND = "Invalid add command";
    private final boolean isMarkDone;
    private Ui ui;
    private String arguments;
    private boolean toDisplay;
    private Food food;
    public static final String INVALID_FOOD_INPUT = "Invalid add food command";
    public static final String INVALID_STRENGTH_INPUT_MESSAGE = "Invalid add strength exercise command";
    public static final String INVALID_CARDIO_INPUT_MESSAGE = "Invalid add cardio exercise command";
    final String[] invalidFoodNames = {"", " ", "[]\\[;]"};

    private ExerciseList exerciseList;

    private FoodList foodList;

    private Biometrics biometrics;
    private RecordList recordList;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public AddCommand(String arguments, boolean toDisplay, boolean isMarkDone) {
        this.arguments = arguments;
        this.toDisplay = toDisplay;
        this.isMarkDone = isMarkDone;
    }

    /**
     * Determines and invoke the correct type of add function by checking user's input.
     *
     * @throws IllegalValueException if the user input for add type does not exist.
     */
    @Override
    public void execute() throws IllegalValueException {
        int slashesCount = Parser.getArgumentsCount(arguments);
        String[] argumentList = Parser.getArgumentList(arguments);
        String addType = Parser.getClassType(argumentList);
        switch (addType) {
        case FOOD:
            addFood(argumentList, slashesCount);
            break;
        case STRENGTH:
            addStrengthExercise(argumentList, slashesCount);
            break;
        case CARDIO:
            addCardioExercise(argumentList, slashesCount);
            break;
        case WEIGHT:
            addWeightAndFat(argumentList, slashesCount);
            break;
        default:
            handleInvalidAddType();
            break;
        }
    }

    /**
     * Adds strength exercise.
     *
     * @param argumentList Input for the command
     * @param slashesCount Number of slash in the input
     * @throws IllegalValueException if the command is invalid
     */
    private void addStrengthExercise(String[] argumentList, int slashesCount) throws IllegalValueException {
        validateAddStrengthExerciseCommand(argumentList, slashesCount);
        executeAddStrengthExercise(argumentList);
    }

    /**
     * Executes add strength command.
     *
     * @param argumentList Input for the command
     * @throws IllegalValueException If the command is invalid
     */
    private void executeAddStrengthExercise(String[] argumentList) throws IllegalValueException {
        try {
            Exercise exercise = createStrengthExercise(argumentList);
            exerciseList.addExercise(exercise);
            assert (exerciseList.getCurrentExercise(exerciseList.getCurrentExerciseListSize() - 1)
                    .equals(exercise)) : ASSERT_ADD_STRENGTH_FAILURE_MESSAGE;
            markDoneForLoading(argumentList);
            outputAddingMessage(exercise);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, ADD_STRENGTH_ERROR_MESSAGE, e);
            throw new IllegalValueException(ADD_STRENGTH_ERROR_MESSAGE);
        }
    }

    /**
     * Outputs success adding message.
     *
     * @param exercise Exercise to be converted to string
     */
    private void outputAddingMessage(Exercise exercise) {
        if (toDisplay) {
            ui.output(exercise.toString());
            ui.output(ADD_STRENGTH_SUCCESS_MESSAGE);
        }
    }


    /**
     * Checks and executes mark done for exercise already done.
     *
     * @param argumentList Inputs for the command
     * @throws IllegalValueException If there is error in data from loading txt
     */
    private void markDoneForLoading(String[] argumentList) throws IllegalValueException {
        if (isMarkDone) {
            double time = Validator.getTimeWithValidation(argumentList[LOAD_STRENGTH_EXERCISE_DATE_INDEX]);
            int calories = Integer.parseInt(argumentList[LOAD_STRENGTH_EXERCISE_CALORIES_INDEX]);
            exerciseList.markDone(exerciseList.getCurrentExerciseListSize() - 1, time, calories);
        }
    }

    /**
     * Creates strength exercise.
     *
     * @param argumentList Input for the command
     * @return Strength exercise
     * @throws IllegalValueException If there is invalid input
     */
    private Exercise createStrengthExercise(String[] argumentList) throws IllegalValueException {
        String description = Validator
                .getDescriptionWithValidation(argumentList[STRENGTH_EXERCISE_DESCRIPTION_INDEX]);
        int weight = Validator.getWeightWithValidation(argumentList[STRENGTH_EXERCISE_WEIGHT_INDEX]);
        int set = Validator.getSetWithValidation(argumentList[STRENGTH_EXERCISE_SET_INDEX]);
        int repetition = Validator
                .getRepetitionWithValidation(argumentList[STRENGTH_EXERCISE_REPETITION_INDEX]);

        LocalDate date;
        date = getLocalDate(argumentList, MAXIMUM_ADD_STRENGTH_ARGUMENT_LENGTH,
                STRENGTH_EXERCISE_DATE_INDEX);
        return new StrengthExercise(description, weight, set, repetition, date);
    }


    /**
     * Gets the date for the add command.
     *
     * @param argumentList  Input for the command
     * @param maximumLength Maximum length for the input
     * @param dateIndex     Index of the date parameter
     * @return LocalDate for the add command
     * @throws IllegalValueException If there is error converting input date to LocalDate
     */
    private LocalDate getLocalDate(String[] argumentList, int maximumLength, int dateIndex)
            throws IllegalValueException {
        LocalDate date = LocalDate.now();
        if (!toDisplay || argumentList.length == maximumLength) {
            date = Parser.parseDate(argumentList[dateIndex], MONTHS_TO_ADD);
        }
        return date;
    }

    /**
     * Validates the add strength command.
     *
     * @param argumentList Input from the user
     * @param slashesCount Number of slashes in the input
     * @throws IllegalValueException If the input for the command is invalid
     */
    private void validateAddStrengthExerciseCommand(String[] argumentList, int slashesCount)
            throws IllegalValueException {
        if (toDisplay) {
            Validator.validateCommandInput(slashesCount, MINIMUM_ADD_STRENGTH_SLASHES,
                    MAXIMUM_ADD_STRENGTH_SLASHES, INVALID_STRENGTH_INPUT_MESSAGE,
                    arguments.charAt(arguments.length() - 1));
        }
        Validator.validateLoadingForExercise(STRENGTH_LOADING_INPUT_COUNT,
                INVALID_LOADING_STRENGTH_MESSAGE, toDisplay, argumentList.length);
    }

    /**
     * To add a Cardio exercise to the exercise list.
     *
     * @param argumentList an array of inputs from the user
     * @param slashesCount to check if the number of slashes correspond with the required number of slashes
     * @throws IllegalValueException if the user does not input distance in numbers or repetitions in integers
     */
    private void addCardioExercise(String[] argumentList, int slashesCount) throws IllegalValueException {
        if (toDisplay) {
            Validator.validateCommandInput(slashesCount, MINIMUM_ADD_CARDIO_SLASHES,
                    MAXIMUM_ADD_CARDIO_SLASHES, INVALID_CARDIO_INPUT_MESSAGE,
                    arguments.charAt(arguments.length() - 1));
        }
        Validator.validateLoadingForExercise(CARDIO_LOADING_INPUT_COUNT,
                INVALID_LOADING_CARDIO_MESSAGE, toDisplay, argumentList.length);
        String description = Validator.getDescriptionWithValidation(argumentList[1]);
        try {
            double distance = Validator.getDistanceWithValidation(argumentList[2]);
            int repetition = Validator.getRepetitionWithValidation(argumentList[3]);
            LocalDate date = LocalDate.now();
            if (argumentList.length == 5) {
                date = Parser.parseDate(argumentList[4], 1);
            }
            Exercise exercise = new CardioExercise(description, distance, repetition, date);
            exerciseList.addExercise(exercise);
            assert (exerciseList.getCurrentExercise(exerciseList.getCurrentExerciseListSize() - 1)
                    .equals(exercise)) : "Exercise not added properly";
            if (isMarkDone) {
                double time = Double.parseDouble(argumentList[5]);
                int calories = Integer.parseInt(argumentList[6]);
                exerciseList.markDone(exerciseList.getCurrentExerciseListSize() - 1, time, calories);
            }
            if (toDisplay) {
                ui.output(exercise.toString());
                ui.output(" This cardio exercise is added to the exercise list successfully");
            }
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Distance must be in numbers and repetitions must be integers");
        }
    }


    private void handleInvalidAddType() throws IllegalValueException {
        throw new IllegalValueException(INVALID_ADD_COMMAND);
    }

    /**
     * Adds a new food record to the foodList.
     *
     * @param argumentList   a string array storing the user's input
     * @param argumentsCount the number of arguments that the user has parsed in based on '/'.
     * @throws IllegalValueException if adding food fails.
     */

    private void addFood(String[] argumentList, int argumentsCount) throws IllegalValueException {
        try {
            Validator.validateCommandInput(argumentsCount, 2, 3, INVALID_FOOD_INPUT,
                    arguments.charAt(arguments.length() - 1));
            LocalDate date = LocalDate.now();
            if (argumentList.length == 4) {
                date = Parser.parseDate(argumentList[3], 0);
            }
            String description = validateFoodName(argumentList[1]);
            int calories = validateCalories(argumentList[2]);
            food = new Food(description, calories, date);
            foodList.addFood(food);
            assert foodList.getFood(foodList.getFoodListSize() - 1).equals(food) : "Food added properly";
            if (toDisplay) {
                ui.output(food.toString());
                ui.output(ADD_FOOD_SUCCESS_MESSAGE);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, ADD_FOOD_ERROR_MESSAGE, e);
            throw new IllegalValueException(INVALID_FOOD_INPUT);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(INVALID_DATE_FORMAT);
        }
    }


    private void addWeightAndFat(String[] argumentList, int argumentsCount) throws IllegalValueException {
        Validator.validateCommandInput(argumentsCount, 2, 3, "Invalid add weight command",
                arguments.charAt(arguments.length() - 1));
        try {
            int weight = Integer.parseInt(argumentList[1]);
            int fat = Integer.parseInt(argumentList[2]);
            LocalDate date = LocalDate.now();
            if (argumentList.length == 4) {
                date = Parser.parseDate(argumentList[3], 0);
            }
            WeightAndFat weightAndFat = new WeightAndFat(weight, fat, date);
            biometrics.weightAndFatList.addWeightAndFat(weightAndFat);
            if (toDisplay) {
                ui.output(weightAndFat.toString());
                ui.output(" Weight and fat percentage are recorded successfully");
            }
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Weight and fat percentage should be numerical");
        }
    }

    /**
     * Checks if the food description is valid.
     *
     * @param input the food description based on user's input
     */
    private String validateFoodName(String input) throws IllegalValueException {
        if (Arrays.asList(invalidFoodNames).contains(input)) {
            throw new IllegalValueException(INVALID_FOOD_DESCRIPTION_INPUTS);
        }
        return input;
    }

    /**
     * Checks if the calories is valid.
     *
     * @param input the calories inputted by user
     */
    private int validateCalories(String input) throws IllegalValueException {
        int calories = Integer.parseInt(input);
        if (calories <= 0) {
            throw new IllegalValueException(NEGATIVE_CALORIES_ERROR_MESSAGE);
        }
        if (calories > 10000) {
            throw new IllegalValueException(CALORIES_LIMIT_EXCEEDED);
        }
        return calories;
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
        this.exerciseList = exerciseList;
        this.foodList = foodList;
        this.biometrics = biometrics;
        this.recordList = recordList;
    }
}