package seedu.duke.logic.command;

import seedu.duke.exception.IllegalValueException;
import seedu.duke.logic.Parser;
import seedu.duke.logic.Validator;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class RemoveCommand extends Command {
    private Ui ui;
    private FoodList foodList;
    private String arguments;

    public static final String INVALID_REMOVE_EXERCISE_COMMAND = "Invalid remove exercise command";
    public static final String INVALID_REMOVE_FOOD_COMMAND = "Invalid remove food command";
    public static final String INVALID_REMOVE_WEIGHT_COMMAND = "Invalid remove weight command";

    public static final int REQUIRED_COUNT = 1;
    private ExerciseList exerciseList;
    private Biometrics biometrics;

    public RemoveCommand(String arguments) {
        this.arguments = arguments;
    }

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
            case ("food"):
                removeFood(argumentList, slashesCount);
                break;
            case ("exercise"):
                removeExercise(argumentList, slashesCount);
                break;
            case ("weight"):
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
        biometrics.weightAndFatList.removeWeightAndFat(index - 1);
        ui.output("Weight and fat record removed successfully");
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

    private void removeFood(String[] argumentList, int slashesCount) throws IllegalValueException {
        Validator.validateCommandInput(slashesCount, REQUIRED_COUNT, INVALID_REMOVE_FOOD_COMMAND,
                arguments.charAt(arguments.length() - 1));
        try {
            assert argumentList.length == 2 : "Valid remove food command";
            int index = Integer.parseInt(argumentList[1]);
            if (index <= 0 || index > foodList.getFoodListSize()) {
                throw new IllegalValueException(INVALID_REMOVE_FOOD_COMMAND);
            }
            ui.output(" This food has been deleted from the food list successfully");
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
