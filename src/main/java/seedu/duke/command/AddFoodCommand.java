package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.Food;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;

import java.util.ArrayList;

public class AddFoodCommand extends Command {
    private Ui ui;
    private String arguments;

    private Food food;
    public static ArrayList<Food> foodList = new ArrayList<>();
    public static final String INVALID_FOOD_INPUT = "Invalid food input";

    @Override
    public void execute() {
        //String[] argumentList = Parser.getArgumentList(arguments);
        String[] argumentList = arguments.split(" ");
        addFood(argumentList);
    }


    public AddFoodCommand(String arguments) {
        this.arguments = arguments;
    }


    private void addFood(String[] argumentList) {
        try {
            if (argumentList.length < 3) {
                throw  new IllegalValueException(INVALID_FOOD_INPUT);
            }
            String description = argumentList[1];
            int calories = Integer.parseInt(argumentList[2]);

            if (description.equals("") || calories <= 0) {
                throw new IllegalValueException(INVALID_FOOD_INPUT);
            }
            food = new Food(description, calories);
            foodList.add(food);
            ui.output(food.toString());
            ui.output(" This food is added to the food list successfully");
        } catch (NumberFormatException e) {
            ui.output(INVALID_FOOD_INPUT);
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        }
    }


    @Override
    public void setData(Ui ui, Biometrics biometrics) {
        this.ui = ui;
    }
}
