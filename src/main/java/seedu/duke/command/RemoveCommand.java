package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.food.Food;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.ExerciseList;

import java.util.ArrayList;


public class RemoveCommand extends Command{

    private Ui ui;
    private String arguments;

    private Food food;
    public static ArrayList<Food> foodList = AddCommand.foodList;
    public static final String INVALID_REMOVE_FOOD_INPUT = "Invalid remove food input";
    public static final String INVALID_INDEX = "Please provide a valid index to delete";

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

    private void removeFood(String[] argumentList) {
        try {
            if (argumentList.length < 2) {
                throw new IllegalValueException(INVALID_REMOVE_FOOD_INPUT);
            }
            int index = Integer.parseInt(argumentList[1]);
            if (index <= 0 || index > foodList.size()) {
                throw new IllegalValueException(INVALID_REMOVE_FOOD_INPUT);
            }
            ui.output(" This food has been deleted from the food list successfully");
            ui.output(foodList.get(index -1).toString());
            foodList.remove(index-1);
        } catch (NumberFormatException e) {
            ui.output(INVALID_REMOVE_FOOD_INPUT);
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        }
    }


    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList) {
        this.ui = ui;
    }
}
