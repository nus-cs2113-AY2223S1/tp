package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.food.Food;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;

import java.util.ArrayList;
import java.util.Arrays;

public class AddCommand extends Command {
    private Ui ui;
    private String arguments;

    private Food food;
    public static final String INVALID_FOOD_INPUT = "Invalid food input";
    final String[] invalidFoodNames = { "", " ", "[]\\[;]" };

    private Exercise exercise;
    private ExerciseList exerciseList;

    private FoodList foodList;

    public AddCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalValueException {
        String[] argumentList = Parser.getArgumentList(arguments);
        String addType = argumentList[0];
        switch (addType) {
        case ("food"):
            addFood(argumentList);
            break;
        case("exercise"):
            addExercise(argumentList);
            break;
        default:
            handleInvalidAddType();
        }
    }

    private void handleInvalidAddType() throws IllegalValueException {
        throw new IllegalValueException("Invalid add command");
    }


    private void addFood(String[] argumentList) throws IllegalValueException{
        try {
            if (argumentList.length < 3) {
                throw new IllegalValueException(INVALID_FOOD_INPUT);
            }
            String description = extractFoodName(argumentList[1]);
            int calories = extractCalories(argumentList[2]);

            food = new Food(description, calories);
            foodList.addFood(food);
            ui.output(food.toString());
            ui.output(" This food is added to the food list successfully");
        } catch (NumberFormatException e) {
            throw new IllegalValueException(INVALID_FOOD_INPUT);
        }
    }

    private void addExercise(String[] argumentList) {
        try {
            if (argumentList.length != 4) {
                throw new IllegalValueException("INVALID_EXERCISE_INPUT");
            }
            String description = argumentList[1];
            int repetitions = Integer.parseInt(argumentList[2]);
            int calories = Integer.parseInt(argumentList[3]);
            exercise = new Exercise(description, repetitions, calories);
            exerciseList.addExercise(exercise);
            ui.output(exercise.toString());
            ui.output(" This exercise is added to the exercise list successfully");
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        }
    }

    private String extractFoodName(String input) throws IllegalValueException {
        String food_name = input;
        if (Arrays.asList(invalidFoodNames).contains(input)) {
            throw new IllegalValueException("Please provide valid food description inputs!");
        }
        return food_name;
    }

    private int extractCalories(String input) throws IllegalValueException {
        int calories = Integer.parseInt(input);
        if (calories <= 0) {
            throw new IllegalValueException("Calories inputs need to be positive integer values!");
        }
        return calories;
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList) {
        this.ui = ui;
        this.exerciseList = exerciseList;
        this.foodList = foodList;
    }
}