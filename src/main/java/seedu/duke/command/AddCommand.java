package seedu.duke.command;

import seedu.duke.Biometrics;
import seedu.duke.Food;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;

import java.util.ArrayList;
import java.util.Arrays;

public class AddCommand extends Command{
    private Ui ui;
    private String arguments;

    private Food food;

    @Override
    public void execute() {
        String[] argumentList = Parser.getArgumentList(arguments);
        addFood(argumentList);
    }


    public AddCommand(String arguments) {
        this.arguments = arguments;
    }
    public static ArrayList<Food> foodList = new ArrayList<>();

    public static final String INVALID_FOOD_INPUT = "Invalid food input";

    private void addFood(String[] argumentList) {
        try {
            if (argumentList.length < 3) {
                throw  new IllegalValueException(INVALID_FOOD_INPUT);
            }
            String description = argumentList[1];
            int calories = Integer.parseInt(argumentList[2]);

            if ( description.equals("") || calories <= 0) {
                throw new IllegalValueException(INVALID_FOOD_INPUT);
            }
            food = new Food (description, calories);
            foodList.add(food);
            ui.output(food.toString());
            ui.output (" This food is added to the food list successfully");
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
