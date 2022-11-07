package seedu.duke.logic.command;

import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.logic.Parser;
import seedu.duke.logic.Validator;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class RemoveCommand extends Command {
    public static final String REMOVE_FOOD_SUCCESS_MSG = " This food has been deleted from the food list successfully";
    public static final String FOOD = "food";
    public static final String EXERCISE = "exercise";
    public static final String WEIGHT = "weight";
    private Ui ui;
    private FoodList foodList;
    private String arguments;

    public static final String INVALID_REMOVE_EXERCISE_COMMAND = "Invalid remove exercise command";
    public static final String INVALID_REMOVE_FOOD_COMMAND = "Invalid remove food command";

    public static final String INDEX_OUT_OF_BOUNDS = "Invalid index put!";

    public static final String INVALID_REMOVE_WEIGHT_COMMAND = "Invalid remove weight command";

    public static final int REQUIRED_COUNT = 1;
    private ExerciseList exerciseList;
    private Biometrics biometrics;

    public RemoveCommand(String arguments) {
        this.arguments = arguments;
    }


    /**
     * Determines and invoke the correct type of remove function by checking user's input.
     *
     * @throws IllegalValueException if the number of arguments are not as expected.
     */
    @Override
    public void execute() throws IllegalValueException {
        String[] argumentList = Parser.getArgumentList(arguments);
        int slashesCount = Parser.getArgumentsCount(arguments);
        if (argumentList.length != 2) {
            throw new IllegalValueException("Invalid number input");
        }
        try {
            int index = Integer.parseInt(argumentList[1]);
            String removeType = argumentList[0];
            switch (removeType) {
            case FOOD:
                removeFood(argumentList, slashesCount);
                break;
            case EXERCISE:
                removeExercise(argumentList, slashesCount);
                break;
            case WEIGHT:
                removeWeight(index);
                break;
            default:
                handleInvalidRemoveType();
            }
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Index should be an integer");
        }
    }

    private void removeWeight(int index) throws IllegalValueException {
        WeightAndFat deletedWeightAndFat = biometrics.weightAndFatList.removeWeightAndFat(index - 1);
        ui.output("This weight and fat record has been deleted successfully!");
        ui.output(deletedWeightAndFat.toString());
    }

    private void removeExercise(String[] argumentList, int slashesCount) throws IllegalValueException {
        Validator.validateCommandInput(slashesCount, REQUIRED_COUNT, INVALID_REMOVE_EXERCISE_COMMAND,
                arguments.charAt(arguments.length() - 1));
        try {
            int index = Integer.parseInt(argumentList[1]);
            if (index > exerciseList.getCurrentExerciseListSize() || index < 1) {
                throw new IllegalValueException("Invalid index input");
            }
            ui.output("This exercise has been deleted from the exercise list successfully!");
            ui.output(exerciseList.getCurrentExercise(index - 1).toString());
            exerciseList.removeCurrentExercise(index - 1);
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        }
    }

    private void handleInvalidRemoveType() {
        ui.output("Invalid remove command");
    }


    /**
     * Deletes the food record at the specified index from the foodList.
     *
     * @param argumentList a string array storing the user's input
     * @param slashesCount the number of arguments that the user has parsed in based on '/'.
     *
     * @throws IllegalValueException if removing food fails.
     */
    private void removeFood(String[] argumentList, int slashesCount) throws IllegalValueException {
        Validator.validateCommandInput(slashesCount, REQUIRED_COUNT, INVALID_REMOVE_FOOD_COMMAND,
                arguments.charAt(arguments.length() - 1));
        try {
            assert argumentList.length == 2 : "Valid remove food command";
            int index = Integer.parseInt(argumentList[1]);
            if (index <= 0 || index > foodList.getFoodListSize()) {
                throw new IllegalValueException(INDEX_OUT_OF_BOUNDS);
            }
            ui.output(REMOVE_FOOD_SUCCESS_MSG);
            ui.output(foodList.getFood(index - 1).toString());
            int initialFoodListSize = foodList.getFoodListSize();
            foodList.removeFood(index - 1);
            assert foodList.getFoodListSize() == initialFoodListSize - 1 : "Food removed properly";
        } catch (NumberFormatException e) {
            throw new IllegalValueException(INVALID_REMOVE_FOOD_COMMAND);
        }
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
        this.foodList = foodList;
        this.exerciseList = exerciseList;
        this.biometrics = biometrics;
    }
}
