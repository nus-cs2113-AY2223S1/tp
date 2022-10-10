package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.food.Food;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;

import java.util.ArrayList;

public class AddCommand extends Command {
    private Ui ui;
    private String arguments;

    private Food food;
    public static ArrayList<Food> foodList = new ArrayList<>();
    public static final String INVALID_FOOD_INPUT = "Invalid food input";

    private Exercise exercise;
    private ExerciseList exerciseList;

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


    private void addFood(String[] argumentList) {
        try {
            if (argumentList.length < 3) {
                throw new IllegalValueException(INVALID_FOOD_INPUT);
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

    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList) {
        this.ui = ui;
        this.exerciseList = exerciseList;
    }
}