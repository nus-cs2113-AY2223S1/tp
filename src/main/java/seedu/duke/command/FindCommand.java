package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.Validator;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.Calories;
import seedu.duke.records.CaloriesList;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.biometrics.Calculator;
import seedu.duke.records.exercise.CardioExercise;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.exercise.StrengthExercise;
import seedu.duke.records.food.Food;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public static final String STRENGTH_EXERCISE_NOT_FOUND = "No matching strength exercise found";
    public static final String STRENGTH_EXERCISE_FOUND = "Here are the matching strength exercises in your list:";
    public static final String CARDIO_EXERCISE_FOUND = "Here are the matching cardio exercises in your list:";
    public static final String CARDIO_EXERCISE_NOT_FOUND = "No matching cardio exercise found";
    public static final String INVALID_FIND_STRENGTH_COMMAND = "Invalid find strength command";
    public static final int REQUIRED_COUNT = 1;
    private String arguments;
    private ExerciseList exerciseList;
    private FoodList foodList;
    private Ui ui;
    private Biometrics biometrics;

    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalValueException {
        int slashesCount = Parser.getArgumentsCount(arguments);
        String[] argumentList = Parser.getArgumentList(arguments);
        String findType = Parser.getClassType(argumentList);
        switch (findType) {
        case "strength":
            findStrength(argumentList, slashesCount);
            break;
        case "cardio":
            findCardio(argumentList, slashesCount);
            break;
        case "food":
            findFood(argumentList, slashesCount);
            break;
        case "date_e":
            findDateExercise(argumentList, slashesCount);
            break;
        case "date_f":
            findDateFood(argumentList, slashesCount);
            break;
        case "calories":
            findCalories(argumentList);
            break;
        default:
            handleInvalidFindType();
        }
    }

    private void findCalories(String[] argumentList) throws IllegalValueException {
        handleInvalidFindDateCommand(argumentList);
        String filterDate = argumentList[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate.parse(filterDate, formatter);
        } catch (Exception e) {
            ui.output("Invalid date has been entered.");
            ui.output("Please enter date in dd-MM-yyyy format!");
            return;
        }
        int caloriesConsumedEntry = getFilteredCaloriesConsumedList(argumentList);
        int caloriesBurntEntry = getFilteredCaloriesBurntList(argumentList);
        if (caloriesConsumedEntry == 0 && caloriesBurntEntry == 0) {
            throw new IllegalValueException("Input date does not have entries yet!");
        }
        int netCaloriesEntry = caloriesConsumedEntry - caloriesBurntEntry;
        String message;
        Calculator calculator = new Calculator(biometrics.getGender(), biometrics.getWeight(),
                biometrics.getHeight(), biometrics.getAge(), biometrics.getActivityLevel());
        calculator.setHealthyCalorieDeficit();
        calculator.setHealthyCalorieSurplus();
        if (netCaloriesEntry < 0) {
            if (netCaloriesEntry < calculator.getHealthyCalorieDeficit()) {
                message = "Your calorie deficit is too high! ";
            } else {
                message = "Your calorie deficit is acceptable! ";
            }
        } else {
            if (netCaloriesEntry > calculator.getHealthyCalorieSurplus()) {
                message = "Your calorie surplus is too much! ";
            } else {
                message = "Your calorie deficit is acceptable! ";
            }
        }
        LocalDate localdate = LocalDate.parse(filterDate, formatter);
        Calories caloriesinput = new Calories(caloriesConsumedEntry,
                caloriesBurntEntry, netCaloriesEntry, localdate, message);
        CaloriesList caloriesList = new CaloriesList();
        caloriesList.addCalories(caloriesinput);
        ArrayList<Calories> clist = caloriesList.getCaloriesList();
        ui.outputCalories(clist);
    }

    private void findDateExercise(String[] argumentList, int slashesCount) throws IllegalValueException {
        handleInvalidFindDateCommand(argumentList);
        ArrayList<Exercise> filteredDateList = getFilteredDateList(argumentList);
        ui.output("", "Here are the exercises in your list matching this date:");
        ui.outputExerciseList(filteredDateList);
    }

    private void findDateFood(String[] argumentList, int slashesCount) throws IllegalValueException {
        handleInvalidFindDateCommand(argumentList);
        ArrayList<Food> filteredFoodDateList = getFilteredFoodDateList(argumentList);
        ui.output("", "Here are the food records in your list matching this date:");
        ui.outputFoodList(filteredFoodDateList);
    }

    private void handleInvalidFindDateCommand(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 2) {
            throw new IllegalValueException("Invalid find date command");
        }
        try {
            LocalDate.parse(argumentList[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeException e) {
            throw new IllegalValueException("Date is in the wrong format. Please follow the dd-MM-yyyy format");
        }
    }


    private void findCardio(String[] argumentList, int slashesCount) throws IllegalValueException {
        handleInvalidFindCardioCommand(argumentList);
        ArrayList<Exercise> filteredExerciseList = getFilteredCardioExerciseList(argumentList);
        outputFilteredExerciseList(filteredExerciseList, CARDIO_EXERCISE_NOT_FOUND, CARDIO_EXERCISE_FOUND);
    }

    private void handleInvalidFindCardioCommand(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 2) {
            throw new IllegalValueException("Invalid find cardio command");
        }
    }

    private void findStrength(String[] argumentList, int slashesCount) throws IllegalValueException {
        Validator.validateCommandInput(slashesCount, REQUIRED_COUNT, INVALID_FIND_STRENGTH_COMMAND,
                arguments.charAt(arguments.length() - 1));
        String description = Validator.getDescriptionWithValidation(argumentList[1]);
        ArrayList<Exercise> filteredExerciseList = getFilteredExerciseList(description);
        outputFilteredExerciseList(filteredExerciseList,
                STRENGTH_EXERCISE_NOT_FOUND, STRENGTH_EXERCISE_FOUND);
    }

    private void outputFilteredExerciseList(ArrayList<Exercise> filteredExerciseList,
                                            String failureFeedback, String successFeedback) {
        if (filteredExerciseList.size() == 0) {
            ui.output(failureFeedback);
        } else {
            ui.output("", successFeedback);
            ui.outputExerciseList(filteredExerciseList);
        }
    }

    private void findFood(String[] argumentList, int slashesCount) throws IllegalValueException {
        handleInvalidFindFoodCommand(argumentList);
        ArrayList<Food> filteredFoodList = getFilteredFoodList(argumentList);
        ui.output("Here are the matching food in your food list:");
        ui.outputFoodList(filteredFoodList);
    }


    private static void handleInvalidFindFoodCommand(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 2) {
            throw new IllegalValueException("Invalid find food command");
        }
    }

    private ArrayList<Food> getFilteredFoodList(String[] argumentList) {
        ArrayList<Food> filteredFoodList = (ArrayList<Food>) foodList.getFoodList()
                .stream().filter(Food.class::isInstance)
                .filter(f -> f.getFoodDescription().contains(argumentList[1]))
                .collect(Collectors.toList());
        return filteredFoodList;
    }

    private ArrayList<Food> getFilteredFoodDateList(String[] argumentList) {
        ArrayList<Food> filteredFoodDateList = (ArrayList<Food>) foodList.getFoodList()
                .stream().filter(Food.class::isInstance)
                .filter(f -> f.getDate().contains(argumentList[1]))
                .collect(Collectors.toList());
        return filteredFoodDateList;
    }


    private ArrayList<Exercise> getFilteredCardioExerciseList(String[] argumentList) {
        ArrayList<Exercise> filteredExerciseList = (ArrayList<Exercise>) exerciseList.getCompletedExerciseList()
                .stream().filter(CardioExercise.class::isInstance)
                .filter(e -> e.getExerciseName().contains(argumentList[1]))
                .collect(Collectors.toList());
        filteredExerciseList.addAll(exerciseList.getCurrentExerciseList()
                .stream().filter(CardioExercise.class::isInstance)
                .filter(e -> e.getExerciseName().contains(argumentList[1]))
                .collect(Collectors.toList()));
        return filteredExerciseList;
    }

    private int getFilteredCaloriesConsumedList(String[] argumentList) {
        int totalCaloriesConsumed = 0;
        String filteredDate;
        Calculator calculator = new Calculator(biometrics.getGender(), biometrics.getWeight(),
                biometrics.getHeight(), biometrics.getAge(), biometrics.getActivityLevel());
        filteredDate = argumentList[1];
        totalCaloriesConsumed = calculator.calculateTotalCaloriesConsumed(foodList.getFoodList(), filteredDate);
        return totalCaloriesConsumed;
    }

    private int getFilteredCaloriesBurntList(String[] argumentList) {
        int totalCaloriesBurnt = 0;
        String filteredDate;
        Calculator calculator = new Calculator(biometrics.getGender(), biometrics.getWeight(),
                biometrics.getHeight(), biometrics.getAge(), biometrics.getActivityLevel());
        filteredDate = argumentList[1];
        totalCaloriesBurnt = calculator.calculateTotalCaloriesBurnt(
                exerciseList.getCompletedExerciseList(), filteredDate);
        return totalCaloriesBurnt;
    }

    private ArrayList<Exercise> getFilteredExerciseList(String description) {
        ArrayList<Exercise> filteredExerciseList = (ArrayList<Exercise>) exerciseList.getCompletedExerciseList()
                .stream().filter(StrengthExercise.class::isInstance)
                .filter(e -> e.getExerciseName().contains(description))
                .collect(Collectors.toList());
        filteredExerciseList.addAll(exerciseList.getCurrentExerciseList()
                .stream().filter(StrengthExercise.class::isInstance)
                .filter(e -> e.getExerciseName().contains(description))
                .collect(Collectors.toList()));
        return filteredExerciseList;
    }

    private ArrayList<Exercise> getFilteredDateList(String[] argumentList) {
        ArrayList<Exercise> filteredExerciseList = (ArrayList<Exercise>) exerciseList.getCompletedExerciseList()
                .stream().filter(StrengthExercise.class::isInstance)
                .filter(e -> e.getDate().contains(argumentList[1]))
                .collect(Collectors.toList());
        filteredExerciseList.addAll(exerciseList.getCurrentExerciseList()
                .stream().filter(StrengthExercise.class::isInstance)
                .filter(e -> e.getDate().contains(argumentList[1]))
                .collect(Collectors.toList()));
        return filteredExerciseList;
    }


    private void handleInvalidFindType() throws IllegalValueException {
        throw new IllegalValueException("Invalid find command");
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
        this.biometrics = biometrics;
        this.exerciseList = exerciseList;
        this.foodList = foodList;
    }
}
