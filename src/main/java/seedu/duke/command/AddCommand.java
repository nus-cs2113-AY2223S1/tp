package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.biometrics.Biometrics;
import seedu.duke.biometrics.WeightAndFat;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.CardioExercise;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.exercise.StrengthExercise;
import seedu.duke.food.Food;
import seedu.duke.food.FoodList;
import seedu.duke.storage.Storage;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;

public class AddCommand extends Command {
    private final boolean isMarkDone;
    private Ui ui;
    private String arguments;
    private boolean toDisplay;
    private Food food;
    public static final String INVALID_FOOD_INPUT = "Invalid food input";
    final String[] invalidFoodNames = {"", " ", "[]\\[;]"};

    private Exercise exercise;
    private ExerciseList exerciseList;

    private FoodList foodList;

    private Biometrics biometrics;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public AddCommand(String arguments, boolean toDisplay, boolean isMarkDone) {
        this.arguments = arguments;
        this.toDisplay = toDisplay;
        this.isMarkDone = isMarkDone;
    }

    @Override
    public void execute() throws IllegalValueException {
        String[] argumentList = Parser.getArgumentList(arguments);
        String addType = Parser.getClassType(argumentList);
        switch (addType) {
        case ("food"):
            addFood(argumentList);
            break;
        case ("strength"):
            addStrengthExercise(argumentList);
            break;
        case ("cardio"):
            addCardioExercise(argumentList);
            break;
        case ("weight"):
            addWeightAndFat(argumentList);
            break;
        default:
            handleInvalidAddType();
        }
    }

    private void addStrengthExercise(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 6 && argumentList.length != 5) {
            LOGGER.warning("Invalid arguments length for add strength exercise");

            throw new IllegalValueException("Invalid add strength exercise command");
        }
        String description = argumentList[1];
        try {
            int set = Integer.parseInt(argumentList[2]);
            int repetition = Integer.parseInt(argumentList[3]);
            int calories = Integer.parseInt(argumentList[4]);
            String date;
            if (argumentList.length != 6) {
                date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } else {
                date = argumentList[5];
            }
            Exercise exercise = new StrengthExercise(description, set, repetition, calories, date);
            exerciseList.addExercise(exercise);
            assert (exerciseList.getCurrentExercise(exerciseList.getCurrentExerciseListSize() - 1)
                    .equals(exercise)) : "Exercise not added properly";
            if (isMarkDone) {
                exerciseList.markDone(exerciseList.getCurrentExerciseListSize() - 1);
            }
            if (toDisplay) {
                ui.output(exercise.toString());
                ui.output(" This strength exercise is added to the exercise list successfully");
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Error converting string to integer", e);
            throw new IllegalValueException("Set, repetition and calories must be integers");
        }
    }

    private void addCardioExercise(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 6 && argumentList.length != 5) {
            throw new IllegalValueException("Invalid add cardio exercise command");
        }
        String description = argumentList[1];
        try {
            int time = Integer.parseInt(argumentList[2]);
            int repetition = Integer.parseInt(argumentList[3]);
            int calories = Integer.parseInt(argumentList[4]);
            String date;
            if (argumentList.length != 6) {
                date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } else {
                date = argumentList[5];
            }
            Exercise exercise = new CardioExercise(description, time, repetition, calories, date);
            exerciseList.addExercise(exercise);
            assert (exerciseList.getCurrentExercise(exerciseList.getCurrentExerciseListSize() - 1)
                    .equals(exercise)) : "Exercise not added properly";
            if (isMarkDone) {
                exerciseList.markDone(exerciseList.getCurrentExerciseListSize() - 1);
            }
            if (toDisplay) {
                ui.output(exercise.toString());
                ui.output(" This cardio exercise is added to the exercise list successfully");
            }
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Time, repetition and calories must be integers");
        }
    }

    private void handleInvalidAddType() throws IllegalValueException {
        throw new IllegalValueException("Invalid add command");
    }


    private void addFood(String[] argumentList) throws IllegalValueException {
        try {
            if (argumentList.length != 3) {
                throw new IllegalValueException(INVALID_FOOD_INPUT);
            }
            assert argumentList.length == 3 : "Invalid add food command";
            String description = extractFoodName(argumentList[1]);
            int calories = extractCalories(argumentList[2]);
            food = new Food(description, calories);
            foodList.addFood(food);
            assert foodList.getFood(foodList.getFoodListSize() - 1).equals(food) : "Food not added properly";
            if (toDisplay) {
                ui.output(food.toString());
                ui.output(" This food is added to the food list successfully");
            }
        } catch (NumberFormatException e) {
            throw new IllegalValueException(INVALID_FOOD_INPUT);
        }
    }


    private void addWeightAndFat(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 3) {
            throw new IllegalValueException("INVALID_WEIGHT_INPUT");
        }
        try {
            int weight = Integer.parseInt(argumentList[1]);
            int fat = Integer.parseInt(argumentList[2]);
            WeightAndFat weightAndFat = new WeightAndFat(weight, fat);
            biometrics.weightAndFatList.addWeightAndFat(weightAndFat);
            biometrics.setWeight(weight);
            biometrics.setFat(fat);
            if (toDisplay) {
                ui.output(weightAndFat.toString());
                ui.output(" Weight and fat percentage are recorded successfully");
            }
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Weight and fat percentage should be numerical");
        }
    }

    private String extractFoodName(String input) throws IllegalValueException {
        if (Arrays.asList(invalidFoodNames).contains(input)) {
            throw new IllegalValueException("Please provide valid food description inputs!");
        }
        return input;
    }

    private int extractCalories(String input) throws IllegalValueException {
        int calories = Integer.parseInt(input);
        if (calories <= 0) {
            throw new IllegalValueException("Calories inputs need to be positive integer values!");
        }
        return calories;
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList) {
        this.ui = ui;
        this.exerciseList = exerciseList;
        this.foodList = foodList;
        this.biometrics = biometrics;
    }
}