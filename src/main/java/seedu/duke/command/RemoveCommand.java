package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;

public class RemoveCommand extends Command {
    private Ui ui;
    private FoodList foodList;
    private String arguments;
    public static final String INVALID_REMOVE_FOOD_INPUT = "Invalid remove food input";

    public RemoveCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalValueException {
        String[] argumentList = Parser.getArgumentList(arguments);
        String removeType = argumentList[0];
        switch (removeType) {
        case ("food"):
            removeFood(argumentList);
            break;
        default:
            handleInvalidRemoveType();
        }
    }

    private void handleInvalidRemoveType() throws IllegalValueException {
        throw new IllegalValueException("Invalid remove command");
    }

    private void removeFood(String[] argumentList) throws IllegalValueException {
        try {
            if (argumentList.length != 2) {
                throw new IllegalValueException(INVALID_REMOVE_FOOD_INPUT);
            }
            assert argumentList.length == 2 : "Invalid remove food command";
            int index = Integer.parseInt(argumentList[1]);
            if (index <= 0 || index > foodList.getFoodListSize()) {
                throw new IllegalValueException(INVALID_REMOVE_FOOD_INPUT);
            }
            ui.output(" This food has been deleted from the food list successfully");
            ui.output(foodList.getFood(index - 1).toString());
            int initialFoodListSize = foodList.getFoodListSize();
            foodList.removeFood(index - 1);
            assert foodList.getFoodListSize() == initialFoodListSize -1 : "Food not removed properly";
        } catch (NumberFormatException e) {
            throw new IllegalValueException(INVALID_REMOVE_FOOD_INPUT);
        }
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList) {
        this.ui = ui;
        this.foodList = foodList;
    }
}
